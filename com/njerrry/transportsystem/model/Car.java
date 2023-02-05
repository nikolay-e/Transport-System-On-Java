package com.njerrry.transportsystem.model;

import java.util.List;

public class Car extends Vehicle {

	private static final double DEFAULT_TANK_VOLUME = 551.0;
	private static final double DEFAULT_SPEED_MAX = 0.5;

	private double consumption = 0.0;
	private double tankVolume = 0.0;
	private double tankContent = DEFAULT_TANK_VOLUME;

	public Car(String name, Coordinates coordinates, List<Road> roadsToTakeList) {
		super(name, DEFAULT_SPEED_MAX, coordinates, roadsToTakeList);
	}

	public Car(String name, double speedMax, Coordinates coordinates) {
		super(name, speedMax, coordinates);
	}

	public Car(String name, double speedMax, Coordinates coordinates, double consumption, double tankContent) {
		super(name, speedMax, coordinates);
		this.consumption = consumption;
		this.tankVolume = DEFAULT_TANK_VOLUME;
		this.tankContent = Math.min(tankContent, tankVolume);
	}

	public Car(String name, double speedMax, Coordinates coordinates, double consumption, double tankContent,
			double tankVolume) {
		super(name, speedMax, coordinates);
		this.consumption = consumption;
		this.tankVolume = tankVolume;
		this.tankContent = Math.min(tankContent, tankVolume);
	}

	public double tankUp(double volume) {
		if (volume < 0.0) {
			System.out.println("Error: volume to tank up cannot be negative!");
			return 0.0;
		}
		double freeVolume = tankVolume - tankContent;
		if (volume >= freeVolume) {
			tankContent = tankVolume;
			return freeVolume;
		} else {
			tankContent = tankContent + volume;
			return volume;
		}
	}

	public double tankUp() {
		double freeVolume = tankVolume - tankContent;
		tankContent = tankVolume;
		return freeVolume;
	}

	public double move(double globalTime) throws Exception {
		double distanceDelta = super.move(globalTime);
		tankContent = tankContent - distanceDelta * consumption / 100.0;
		if (tankContent < 0.0) {
			totalDistanceTraveled += tankContent * 100.0 / consumption;
			tankContent = 0.0;
		}
		return 0.0;
	}

	@Override
	public double getCurrentSpeed() {
		return tankContent == 0 ? 0.0 : super.getCurrentSpeed();
	}
}
