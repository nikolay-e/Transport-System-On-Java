import java.text.DecimalFormat;
import java.util.ArrayList;

public class Vehicle {

	private String name;
	private static int idMax = 1;
	private int id;

	protected float speedMax;
	protected float speedCurrent;

	protected float mileage;
	private float timeTotal;
	private float timeCurrent;

	protected static DecimalFormat df = new DecimalFormat("0.00");
	public static ArrayList<Vehicle> list = new ArrayList<Vehicle>();

	protected Vehicle() {
		name = "";
		speedMax = 0.0f;
		speedCurrent = speedMax;
		initialize();
	}

	protected Vehicle(String sName, float fSpeedMax) {
		name = sName;
		speedMax = fSpeedMax;
		speedCurrent = speedMax;
		initialize();
	}

	private void initialize() {
		id = idMax;
		idMax = idMax + 1;
		timeTotal = 0.0f;
        timeCurrent = 0.0f;
		mileage = 0.0f;
		list.add(this);
	}
	
	public static void printOut() {
		System.out.println();
		System.out.print("Total Time: ");
		System.out.printf("%s\n", df.format(Traffic.timeGlobal));
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID","Name", "Max Speed", "Current Speed",
			"Mileage","Current Time", "Consumption", "Tank Volume", "Tank Content");
		for (Vehicle veh : list) {
			veh.print();
			System.out.println();
		}
		System.out.println();
	}

	protected void print() {
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s", id, name, df.format(speedMax), df.format(speedCurrent), df.format(mileage), df.format(timeCurrent));
	}

	public static void processingAll() {
		for (Vehicle veh : list) {
			veh.processing();
		}
	}

	protected void processing() {
			timeTotal = Traffic.timeGlobal;
			mileage = mileage + (timeTotal - timeCurrent) * speed();
			timeCurrent = timeTotal;
	}

	protected float speed() {
		return speedMax;
	}
}
