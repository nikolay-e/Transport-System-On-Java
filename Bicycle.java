public class Bicycle extends Vehicle {

	public Bicycle() {
	
	}

	public Bicycle(String sName, float fSpeedMax) {
		super(sName, fSpeedMax);
	}

	protected float speed() {

		speedCurrent = super.speed() * (float) Math.pow(0.9, (int) mileage / 20);

		if (speedMax > 12f) {

			if (speedCurrent > 12f) return speedCurrent;
			else if (super.speed() < 12f) return super.speed();
			else return speedCurrent = 12f;

		} else return speedCurrent = super.speed();

	}

	protected float processing() {
		super.processing();
		return 0;
	}

	}
