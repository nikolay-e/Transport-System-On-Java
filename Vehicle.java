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
	public Vehicle() {
		name = "";
		speedMax = 0.0;
		initialize();
	}
	
	public Vehicle(String sName, double dSpeedMax) {
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

		System.out.println("\nID\tName\t\tMax Speed\tMileage\t\tTotal Time\tCurrent Time");
		String plus = "++++++++++++++++++++++++++++";
		System.out.println(plus + plus + plus);
		for (Vehicle veh : list) System.out.println(veh.id + "\t" + veh.name + "\t\t"
			+ df.format(veh.speedMax) + "\t\t" + df.format(veh.mileage) + "\t\t" + df.format(veh.timeTotal) + "\t\t" 
			+ df.format(veh.timeCurrent));
	}

	public static void processing() {
		for (Vehicle veh : list) {
			veh.mileage = Traffic.timeGlobal * veh.speedMax;
			veh.timeTotal = Traffic.timeGlobal;
			veh.timeCurrent = Traffic.timeGlobal;
		}
	} 

}
