package com.njerrry.transportsystem.model;

public class Bicycle extends Vehicle {

	private static final double MIN_SPEED = 20.0;
	private static final double DROP_SPEED_FACTOR = 0.8;
	private static final double DISTANCE_DROP_SPEED_AFTER = 20;

	public Bicycle(String name, double speedMax, Coordinates coordinates) {
		super(name, speedMax, coordinates);
	}

	@Override
	public double getCurrentSpeed() {
        int pow = (int) (distanceAll / DISTANCE_DROP_SPEED_AFTER);

        double currentSpeed = speedMax * Math.pow(DROP_SPEED_FACTOR, pow);

        return Math.max(currentSpeed, MIN_SPEED);
	}
}
