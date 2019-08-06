import java.text.DecimalFormat;
import java.util.ArrayList;

public class Vehicle extends TransportSystem {

	private String name;
	private static int idMax = 1;
	private int id;

	protected float speedMax;
	protected float speedCurrent;

	protected Way currentWay;
	public static ArrayList<Way> ways = new ArrayList<Way>();

	protected float way_dist;

	protected float mileage;
	private float globalTime;
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
		globalTime = 0.0f;
        timeCurrent = 0.0f;
		mileage = 0.0f;
		list.add(this);
	}
	
	public static void printOut() {
		System.out.println();
		System.out.printf("Total Time:  %s\n", df.format(Traffic.timeGlobal));
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

	protected float processing() {
		globalTime = Traffic.timeGlobal;

		float mileageDelta = (globalTime - timeCurrent) * speed();

		if (way_dist + mileageDelta > currentWay.length) {
			mileageDelta = currentWay.length - way_dist;
			way_dist = 0;
			if (ways.size() > 1)
			ways.remove(0);
			currentWay = ways.get(0);
		}

		mileage = mileage + mileageDelta;
		way_dist = way_dist + mileageDelta;

		timeCurrent = globalTime;
		return mileageDelta;
	}

	protected float speed() {
		if (way_dist == currentWay.length)
			return 0;

		if (currentWay.speedLimit > speedMax)
			return speedMax;
		else
			return currentWay.speedLimit;
	}

	public void add_way(Way way) {
		if (ways.size()==0)
			currentWay = way;
		else
			currentWay = ways.get(0);
		ways.add(way);
		way.add_vehicle(this);
	}
}
