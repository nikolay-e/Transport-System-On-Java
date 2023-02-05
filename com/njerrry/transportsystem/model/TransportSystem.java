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
	private List<Way> wayList;

	// Map related data
	private Coordinates mapCenter = new Coordinates();
	private double horizontalDelta;
	private double verticalDelta;

	// Time counter
	private double time = 0.0;

	public TransportSystem(List<Vehicle> listOfVehicles, List<Way> listOfWays) {
		this.vehicleList = listOfVehicles;
		this.wayList = listOfWays;
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

	public List<Way> getWayList() {
		return wayList;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	private void printTS() {
		for (Vehicle vehichle : getVehicleList()) {
			vehichle.print();
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void run() {

		while (/*time < TIME_TO_RUN*/ true) {
			printTS();

			for (Vehicle vehichle : getVehicleList()) {
				try {
					vehichle.run(time);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error while running vehicle", e);
				}
			}

			time += 1000 / 1000.0;
		}
	}

	private void calculateMapSizeValues() {

		double horizontalMin = getWayList().get(0).getFistNode().getX();
		double horizontalMax = getWayList().get(0).getFistNode().getX();

		double verticalMin = getWayList().get(0).getFistNode().getY();
		double verticalMax = getWayList().get(0).getFistNode().getY();

		for (Way ways : getWayList()) {
			horizontalMin = Math.min(horizontalMin, ways.getFistNode().getX());
			horizontalMin = Math.min(horizontalMin, ways.getSecondNode().getX());
			horizontalMax = Math.max(horizontalMax, ways.getFistNode().getX());
			horizontalMax = Math.max(horizontalMax, ways.getSecondNode().getX());
			verticalMin = Math.min(verticalMin, ways.getFistNode().getY());
			verticalMin = Math.min(verticalMin, ways.getSecondNode().getY());
			verticalMax = Math.max(verticalMax, ways.getFistNode().getY());
			verticalMax = Math.max(verticalMax, ways.getSecondNode().getY());
		}

		horizontalDelta = (horizontalMax - horizontalMin);
		verticalDelta = (verticalMax - verticalMin);

		getMapCenter().setX((horizontalMin + horizontalMax) / 2);
		getMapCenter().setY((verticalMin + verticalMax) / 2);

	}
}