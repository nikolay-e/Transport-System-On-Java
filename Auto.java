import java.text.DecimalFormat;

public class Auto extends Vehicle {
		
	private float consumption;
	private float tankVolume;
	private float tankContent;

	public Auto() {
		consumption = 0.0f;
		tankVolume = 0.0f;
		tankContent = 0.0f;
	}

	public Auto(String sName, float fSpeedMax) {
		super(sName, fSpeedMax);
		consumption = 0.0f;
		tankVolume = 0.0f;
		tankContent = 0.0f;
	}	

	public Auto(String sName, float fSpeedMax, float fConsumption, float fTankContent) {
		super(sName, fSpeedMax);
		consumption = fConsumption;
		tankVolume = 551.0f;
		tankContent = fTankContent;
	}	

	public Auto(String sName, float fSpeedMax, float fConsumption, float fTankContent, float fTankVolume) {
		super(sName, fSpeedMax);
		consumption = fConsumption;
		tankVolume = fTankVolume;
		tankContent = fTankContent;
	}

	public float tankUp(float volume) {
		if (volume < 0) {
			System.out.println("Error: volume to tank up cannot be negative!");
			return 0f;
		}		

		float freeVolume = tankVolume - tankContent;

		if (volume >= freeVolume) {
			tankContent = tankVolume;
			return freeVolume;
		} else {
			tankContent = tankContent + volume;
			return volume;	
		}
	}

	public float tankUp() {
		float freeVolume = tankVolume - tankContent;
		tankContent = tankVolume;
		return freeVolume;
	}


	protected void print() {
		super.print();
		System.out.printf("%-15s%-15s%-15s", df.format(consumption), df.format(tankVolume), df.format(tankContent));
	}

}
