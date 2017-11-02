public class Auto extends Vehicle {
		
	private double consumption;
	private double tankVolume;
	private double tankContent;

	public Auto() {

	}

	public Auto(String sName, double dSpeedMax) {
		super(sName, dSpeedMax);
	}	


	public Auto(String sName, double dSpeedMax, double dConsumption, double dTankVolume) {
		super(sName, dSpeedMax);
		consumption = dConsumption;
		tankVolume = dTankVolume;
		tankContent = 551.0;
	}	

	public Auto(String sName, double dSpeedMax, double dConsumption, double dTankVolume, double dTankContent) {
		super(sName, dSpeedMax);
		consumption = dConsumption;
		tankVolume = dTankVolume;
		tankContent = dTankContent;
	}	
}
