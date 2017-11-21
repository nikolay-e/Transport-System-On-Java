import java.util.ArrayList;
import java.text.DecimalFormat;

public class Vehicle {

	private String name;
	private static int idMax = 1;
	private int id;

	private float speedMax;
	private float mileage;
	private float timeTotal;
	private float timeCurrent;

	protected static DecimalFormat df = new DecimalFormat("0.00");
	private static ArrayList<Vehicle> list = new ArrayList<Vehicle>();

	protected Vehicle() {
		name = "";
		speedMax = 0.0f;	
		initialize();
	}

	protected Vehicle(String sName, float fSpeedMax) {
		name = sName;
		speedMax = fSpeedMax;
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
		System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID","Name", "Max Speed", 
			"Mileage","Current Time", "Consumption", "Tank Volume", "Tank Content");

		for (Vehicle veh : list) {
			veh.print();
			System.out.println();
		}

		System.out.println();

	}

	protected void print() {
		System.out.printf("%-5s%-15s%-15s%-15s%-15s", id, name, df.format(speedMax), df.format(mileage), df.format(timeCurrent));
	}

	public static void processing() {
		for (Vehicle veh : list) {
			veh.mileage = Traffic.timeGlobal * veh.speedMax;
			veh.timeTotal = Traffic.timeGlobal;
			veh.timeCurrent = Traffic.timeGlobal;
		}
	} 
}
