public class Bicycle extends Vehicle {

	public Bicycle(String name, double speedMax) {
		super(name, speedMax);
	}

	@Override
	protected double getCurrentSpeed() {

        int pow = (int) (mileageAll / 20.0);

        double currentSpeed = speedMax * Math.pow(0.8, pow);

        if (currentSpeed < 20)
            currentSpeed = 20;

        return currentSpeed;

	}

	protected double run(double globalTime) throws Exception {
		super.run(globalTime);
		return 0;
	}
}
