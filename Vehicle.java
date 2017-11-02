import java.util.ArrayList;
import java.text.DecimalFormat;

public class Vehicle {

	private String name;
	private static int idMax = 1;
	private int id;

	private double speedMax;
	private double mileage;
	private double timeTotal;
	private double timeCurrent;
	private static ArrayList<Vehicle> list = new ArrayList<Vehicle>();

	protected Vehicle() {
		name = "";
		speedMax = 0.0;	
		initialize();
	}

	protected Vehicle(String sName, double dSpeedMax) {
		name = sName;
		speedMax = dSpeedMax;
		initialize();
	}

	private void initialize() {
		id = idMax;
		idMax = idMax + 1;
        	timeTotal = 0.0;
        	timeCurrent = 0.0;
		mileage = 0.0;
		list.add(this);
	}
	
	public static void printOut() {

		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.printf("\n%-5s%-15s%-15s%-15s%-15s%-15s\n", "ID","Name", "Max Speed", "Mileage", "Total Time","Current Time");

		for (Vehicle veh : list) 
			System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s\n",
				veh.id, veh.name, 
				df.format(veh.speedMax), df.format(veh.mileage), df.format(veh.timeTotal), df.format(veh.timeCurrent));

	}

	public static void processing() {

		for (Vehicle veh : list) {
			veh.mileage = Traffic.timeGlobal * veh.speedMax;
			veh.timeTotal = Traffic.timeGlobal;
			veh.timeCurrent = Traffic.timeGlobal;

		}
	} 
}
