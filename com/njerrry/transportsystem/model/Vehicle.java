package com.njerrry.transportsystem.model;

import java.text.DecimalFormat;
import java.util.List;

public class Vehicle {

	private Way currentWay;
	private Coordinates startNodeOnCurrentWay;

	private String name = "";

	protected double speedMax = 0.0;

	private Coordinates coordinates;

	private double distanceWay = 0.0;
	protected double distanceAll = 0.0;

	private double timeCurrent = 0.0;

	private List<Way> waysToTakeList;
	public DecimalFormat decimalFormat = new DecimalFormat("0.00");

	public Vehicle() {
		this.coordinates = new Coordinates();
	}

	public List<Way> getWaysToTakeList() {
		return waysToTakeList;
	}

	public void setWaysToTakeList(List<Way> waysToTakeList) {
		this.waysToTakeList = waysToTakeList;
	}

	public Vehicle(String name, double speedMax, Coordinates coordinates) {
		this.name = name;
		this.speedMax = speedMax;
		this.coordinates = coordinates.getCopy();
		this.startNodeOnCurrentWay = coordinates;
	}

	public Vehicle(String name, double speedMax, Coordinates coordinates, List<Way> waysToTakeList) {
		this.name = name;
		this.speedMax = speedMax;
		this.coordinates = coordinates.getCopy();
		this.waysToTakeList = waysToTakeList;
		this.startNodeOnCurrentWay = coordinates;

		for (Way way : waysToTakeList) {
			if (way.getFistNode() == coordinates || way.getSecondNode() == coordinates) {
				currentWay = way;
				break;
			}
		}
	}

	public double run(double newTimeGlobal) throws Exception {
		double currentSpeed = getCurrentSpeed();
		double delta = (newTimeGlobal - timeCurrent) * currentSpeed;
		double deltaWay = delta;
		if (Double.compare(distanceWay + delta, currentWay.getLength()) > 0) {
			double deltaOldWay = currentWay.getLength() - distanceWay;
			if (getWaysToTakeList().size() == 1) {
				delta = deltaOldWay;
				deltaWay = deltaOldWay;
			} else if (getWaysToTakeList().size() > 1) {
				double deltaNewWay = delta - deltaOldWay;
				getWaysToTakeList().remove(0);
				Way tmpWayCurrent = getWaysToTakeList().get(0);
				// TODO precise calculation of distances in respect to different speed
				double distToFistNode = Coordinates.distance(currentWay.getOppositeNode(startNodeOnCurrentWay),
						tmpWayCurrent.getFistNode());
				double distToSecondNode = Coordinates.distance(currentWay.getOppositeNode(startNodeOnCurrentWay),
						tmpWayCurrent.getSecondNode());
				if (Double.compare(Math.min(distToFistNode, distToSecondNode), 0.0) > 0) {
					delta = deltaOldWay;
					deltaWay = deltaOldWay;
				} else {
					double cos = (currentWay.getOppositeNode(startNodeOnCurrentWay).getX()
							- startNodeOnCurrentWay.getX())
							/ currentWay.getLength();
					double sin = (currentWay.getOppositeNode(startNodeOnCurrentWay).getY()
							- startNodeOnCurrentWay.getY())
							/ currentWay.getLength();
					coordinates.add(new Coordinates(cos * deltaOldWay, sin * deltaOldWay));
					if (distToFistNode > distToSecondNode) {
						startNodeOnCurrentWay = tmpWayCurrent.getSecondNode();
					} else {
						startNodeOnCurrentWay = tmpWayCurrent.getFistNode();
					}
					currentWay = tmpWayCurrent;
					distanceWay = 0;
					deltaWay = deltaNewWay;
				}
			} else {
				System.out.println("ERROR waysToTakeList is empty!");
			}
		}

		distanceAll = distanceAll + delta;
		distanceWay = distanceWay + deltaWay;
		timeCurrent = newTimeGlobal;
		double cos = (currentWay.getOppositeNode(startNodeOnCurrentWay).getX()
				- startNodeOnCurrentWay.getX())
				/ currentWay.getLength();
		double sin = (currentWay.getOppositeNode(startNodeOnCurrentWay).getY()
				- startNodeOnCurrentWay.getY())
				/ currentWay.getLength();
		coordinates.add(new Coordinates(cos * deltaWay, sin * deltaWay));
		return delta;
	}

	public double getCurrentSpeed() {
		if (getWaysToTakeList().size() == 1 && currentWay.getLength() == distanceWay)
			return 0.0;
		else
			return currentWay.speedLimit < speedMax ? currentWay.speedLimit : speedMax;
	}

	public void print() {
		System.out.printf("%-15s", name);

		if (currentWay == null)
			System.out.printf("%-15s", "NOWAY!!!");
		else
			System.out.printf("%-15s", currentWay.name);

		System.out.printf("%-15s", decimalFormat.format(speedMax));
		System.out.printf("%-15s", decimalFormat.format(getCurrentSpeed()));
		System.out.printf("%-15s", decimalFormat.format(distanceAll));
		System.out.printf("%-15s", decimalFormat.format(timeCurrent));
	}

	public void addWay(Way way, Coordinates startNodeOnCurrentWay) {
		this.startNodeOnCurrentWay = startNodeOnCurrentWay;
		getWaysToTakeList().add(way);
		if (currentWay == null) {
			currentWay = getWaysToTakeList().get(0);
			coordinates = startNodeOnCurrentWay;
		}
	}

	public void addWay(Way way) {
		this.startNodeOnCurrentWay = way.getFistNode();
		getWaysToTakeList().add(way);
		if (currentWay == null) {
			currentWay = getWaysToTakeList().get(0);
			coordinates = startNodeOnCurrentWay;
		}
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}
}
