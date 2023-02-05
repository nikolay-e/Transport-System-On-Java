package com.njerrry.transportsystem.model;

import java.util.List;

public class Vehicle {

	private Road currentRoad;
	private Coordinates startNodeOnCurrentRoad;

	private String name = "";
	protected double maxSpeed = 0.0;
	private Coordinates coordinates;
	private double distanceOnCurrentRoad = 0.0;
	protected double totalDistanceTraveled = 0.0;
	private double currentTime = 0.0;
	private List<Road> roadsToTake;

	public Vehicle() {
		this.coordinates = new Coordinates();
	}

	public List<Road> getRoadsToTake() {
		return roadsToTake;
	}

	public void setRoadsToTakeList(List<Road> roadsToTake) {
		this.roadsToTake = roadsToTake;
	}

	public Vehicle(String name, double speedMax, Coordinates coordinates) {
		this.name = name;
		this.maxSpeed = speedMax;
		this.coordinates = coordinates.getCopy();
		this.startNodeOnCurrentRoad = coordinates;
	}

	public Vehicle(String name, double speedMax, Coordinates coordinates, List<Road> roadsToTake) {
		this.name = name;
		this.maxSpeed = speedMax;
		this.coordinates = coordinates.getCopy();
		this.roadsToTake = roadsToTake;
		this.startNodeOnCurrentRoad = coordinates;

		for (Road road : roadsToTake) {
			if (road.getFistNode() == coordinates || road.getSecondNode() == coordinates) {
				currentRoad = road;
				break;
			}
		}
	}

	public double move(double newTimeGlobal) throws Exception {
		double currentSpeed = getCurrentSpeed();
		double delta = (newTimeGlobal - currentTime) * currentSpeed;
		double deltaRoad = delta;
		if (Double.compare(distanceOnCurrentRoad + delta, currentRoad.getLength()) > 0) {
			double deltaOldRoad = currentRoad.getLength() - distanceOnCurrentRoad;
			if (getRoadsToTake().size() == 1) {
				delta = deltaOldRoad;
				deltaRoad = deltaOldRoad;
			} else if (getRoadsToTake().size() > 1) {
				double deltaNewRoad = delta - deltaOldRoad;
				getRoadsToTake().remove(0);
				Road tmpRoadCurrent = getRoadsToTake().get(0);
				// TODO precise calculation of distances in respect to different speed
				double distToFistNode = Coordinates.distance(currentRoad.getOppositeNode(startNodeOnCurrentRoad),
						tmpRoadCurrent.getFistNode());
				double distToSecondNode = Coordinates.distance(currentRoad.getOppositeNode(startNodeOnCurrentRoad),
						tmpRoadCurrent.getSecondNode());
				if (Double.compare(Math.min(distToFistNode, distToSecondNode), 0.0) > 0) {
					delta = deltaOldRoad;
					deltaRoad = deltaOldRoad;
				} else {
					double cos = (currentRoad.getOppositeNode(startNodeOnCurrentRoad).getX()
							- startNodeOnCurrentRoad.getX())
							/ currentRoad.getLength();
					double sin = (currentRoad.getOppositeNode(startNodeOnCurrentRoad).getY()
							- startNodeOnCurrentRoad.getY())
							/ currentRoad.getLength();
					coordinates.add(new Coordinates(cos * deltaOldRoad, sin * deltaOldRoad));
					if (distToFistNode > distToSecondNode) {
						startNodeOnCurrentRoad = tmpRoadCurrent.getSecondNode();
					} else {
						startNodeOnCurrentRoad = tmpRoadCurrent.getFistNode();
					}
					currentRoad = tmpRoadCurrent;
					distanceOnCurrentRoad = 0;
					deltaRoad = deltaNewRoad;
				}
			} else {
				System.out.println("ERROR roadsToTake is empty!");
			}
		}

		totalDistanceTraveled += delta;
		distanceOnCurrentRoad += deltaRoad;
		currentTime = newTimeGlobal;
		double cos = (currentRoad.getOppositeNode(startNodeOnCurrentRoad).getX()
				- startNodeOnCurrentRoad.getX())
				/ currentRoad.getLength();
		double sin = (currentRoad.getOppositeNode(startNodeOnCurrentRoad).getY()
				- startNodeOnCurrentRoad.getY())
				/ currentRoad.getLength();
		coordinates.add(new Coordinates(cos * deltaRoad, sin * deltaRoad));
		return delta;
	}

	public double getCurrentSpeed() {
		if (getRoadsToTake().size() == 1 && currentRoad.getLength() == distanceOnCurrentRoad)
			return 0.0;
		else
			return currentRoad.speedLimit < maxSpeed ? currentRoad.speedLimit : maxSpeed;
	}

	public void addRoad(Road road, Coordinates startNodeOnCurrentRoad) {
		this.startNodeOnCurrentRoad = startNodeOnCurrentRoad;
		getRoadsToTake().add(road);
		if (currentRoad == null) {
			currentRoad = getRoadsToTake().get(0);
			coordinates = startNodeOnCurrentRoad;
		}
	}

	public void addRoad(Road road) {
		this.startNodeOnCurrentRoad = road.getFistNode();
		getRoadsToTake().add(road);
		if (currentRoad == null) {
			currentRoad = getRoadsToTake().get(0);
			coordinates = startNodeOnCurrentRoad;
		}
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
}
