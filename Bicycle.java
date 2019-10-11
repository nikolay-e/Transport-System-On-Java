public class Bicycle extends Vehicle {

	public Bicycle(String name, double speedMax) {
		super(name, speedMax);
	}

	protected double speed() {

		speedCurrent = super.speed() * (double) Math.pow(0.9, (int) mileageAll / 20);

		if (speedMax > 12.0) {

			if (speedCurrent > 12.0) return speedCurrent;
			else if (super.speed() < 12.0) return super.speed();
			else return speedCurrent = 12.0;

		} else return speedCurrent = super.speed();

	}

	protected double run(double globalTime) {
		super.run(globalTime);
		return 0;
	}

	}
