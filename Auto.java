public class Auto extends Vehicle {
		
	private double consumption = 0.0;
	private double tankVolume = 0.0;
	private double tankContent = 0.0;

	public Auto(String name, double speedMax) {
		super(name, speedMax);
	}	

	public Auto(String name, double speedMax, double consumption, double tankContent) {
		super(name, speedMax);
		this.consumption = consumption;
		this.tankVolume = 551.0;
		this.tankContent = tankContent;
	}	

	public Auto(String name, double speedMax, double consumption, double tankContent, double tankVolume) {
		super(name, speedMax);
		this.consumption = consumption;
		this.tankVolume = tankVolume;
		this.tankContent = tankContent;
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

	protected void print() {
		super.print();
		System.out.printf("%-15s%-15s%-15s", df.format(consumption), df.format(tankVolume), df.format(tankContent));
	}

	protected double run(double globalTime) {
		double mileageDelta = super.run(globalTime);

		tankContent = tankContent - mileageDelta * consumption / 100.0;

		if (tankContent < 0.0) {
			mileageAll = mileageAll + tankContent * 100.0 / consumption;
			tankContent = 0.0;
		}
		return 0.0;
	}

	protected double speed() {
		if (tankContent == 0)
			return speedCurrent = 0.0;
		else
			return speedCurrent = super.speed();
	}
}
