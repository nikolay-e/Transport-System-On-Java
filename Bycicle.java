public class Bycicle extends Vehicle {

	public Bycicle() {
	
	}

	public Bycicle(String sName, float fSpeedMax) {
		super(sName, fSpeedMax);
	}

	protected float speed() {

		speedCurrent = speedMax * (float) Math.pow(0.9, (int) mileage / 20);

		if (speedMax > 12f) {

			if (speedCurrent > 12f) return speedCurrent;
			else return speedCurrent = 12f;

		} else return speedCurrent = speedMax;

	}
}
