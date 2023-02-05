package com.njerrry.transportsystem.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransportSystem implements Runnable {

	// Settings
	private static final Logger LOGGER = Logger.getLogger(TransportSystem.class.getName());
	private static final int SLEEP_MILLIS = 1;
	private static final int TIME_TO_RUN = 10000000;

	// Core data
	private List<Vehicle> vehicleList;
	private List<Road> roadList;

	// Map related data
	private Coordinates mapCenter = new Coordinates();
	private double horizontalDelta;
	private double verticalDelta;

	// Time counter
	private double time = 0.0;

	public TransportSystem(List<Vehicle> listOfVehicles, List<Road> listOfRoads) {
		this.vehicleList = listOfVehicles;
		this.roadList = listOfRoads;
		calculateMapSizeValues();
	}

	public Coordinates getMapCenter() {
		return mapCenter;
	}

	public double getVerticalDelta() {
		return verticalDelta;
	}

	public double getHorizontalDelta() {
		return horizontalDelta;
	}

	public List<Road> getRoadList() {
		return roadList;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	@Override
	public void run() {

		while (/* time < TIME_TO_RUN */ true) {

			for (Vehicle vehichle : getVehicleList()) {
				try {
					vehichle.move(time);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error while running vehicle", e);
				}
			}

			time += 1000 / 1000.0;

			try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException occurred while sleeping the thread: " + e.getMessage());
            }
		}
	}

	private void calculateMapSizeValues() {

		double horizontalMin = getRoadList().get(0).getFistNode().getX();
		double horizontalMax = getRoadList().get(0).getFistNode().getX();

		double verticalMin = getRoadList().get(0).getFistNode().getY();
		double verticalMax = getRoadList().get(0).getFistNode().getY();

		for (Road roads : getRoadList()) {
			horizontalMin = Math.min(horizontalMin, roads.getFistNode().getX());
			horizontalMin = Math.min(horizontalMin, roads.getSecondNode().getX());
			horizontalMax = Math.max(horizontalMax, roads.getFistNode().getX());
			horizontalMax = Math.max(horizontalMax, roads.getSecondNode().getX());
			verticalMin = Math.min(verticalMin, roads.getFistNode().getY());
			verticalMin = Math.min(verticalMin, roads.getSecondNode().getY());
			verticalMax = Math.max(verticalMax, roads.getFistNode().getY());
			verticalMax = Math.max(verticalMax, roads.getSecondNode().getY());
		}

		horizontalDelta = (horizontalMax - horizontalMin);
		verticalDelta = (verticalMax - verticalMin);

		getMapCenter().setX((horizontalMin + horizontalMax) / 2);
		getMapCenter().setY((verticalMin + verticalMax) / 2);

	}
}