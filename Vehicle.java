import java.util.ArrayList;
public class Vehicle extends TransportSystem {

	protected Way wayCurrent;

	private String name = "";
	private int id;
	private static int maxId = 0;

	protected double speedMax = 0.0;
	protected double speedCurrent = 0.0;

	protected double mileageWay = 0.0;
	protected double mileageAll = 0.0;

	private double timeGlobal = 0.0;
	private double timeCurrent = 0.0;

	protected ArrayList<Way> waysList = new ArrayList<Way>();

	protected Vehicle() {
		initialize();
	}

	protected Vehicle(String name, double speedMax) {
		this.name = name;
		this.speedMax = speedMax;
		this.speedCurrent = speedMax;
		initialize();
	}

	private void initialize() {
		this.id = maxId;
		maxId += 1;
		vehiclesList.add(this);
	}

	protected double run(double globalTime) {

		timeGlobal = globalTime;

		speedCurrent = speed();
		
		double mileageAllDelta = (timeGlobal - timeCurrent) * speedCurrent;
		double mileageWayDelta = mileageAllDelta;

		if (mileageWay + mileageAllDelta > wayCurrent.length) {

			double mileageDeltaOldWay = wayCurrent.length - mileageWay;

			if (waysList.size() == 1) {
				mileageAllDelta = mileageDeltaOldWay;
				mileageWayDelta = mileageDeltaOldWay;
			} else if (waysList.size() > 1) {
				double mileageDeltaNewWay = mileageAllDelta - mileageDeltaOldWay;

				//TODO precise calculation of distances

				waysList.remove(0);
				wayCurrent = waysList.get(0);

				mileageWay = 0;
				mileageWayDelta = mileageDeltaNewWay;
			} else {
				System.out.println("ERROR waysList is empty!");
			}

		}

		mileageAll = mileageAll + mileageAllDelta;
		mileageWay = mileageWay + mileageWayDelta;

		timeCurrent = timeGlobal;

		return mileageAllDelta;
	}
	

	protected double speed() {
		if (waysList.size() == 1 && wayCurrent.length == mileageWay)
			return 0.0;
		else if (wayCurrent.speedLimit > speedMax)
			return speedMax;
		else
			return wayCurrent.speedLimit;
	}

	protected void print() {

		System.out.printf("%-5s", id);
		System.out.printf("%-15s", name);

		if (wayCurrent == null)
			System.out.printf("%-15s", "NOWAY!!!");
		else
			System.out.printf("%-15s", wayCurrent.name);

		System.out.printf("%-15s", df.format(speedMax));
		System.out.printf("%-15s",df.format(speedCurrent));
		System.out.printf("%-15s", df.format(mileageAll));
		System.out.printf("%-15s", df.format(timeCurrent));

	}

	public void addWay(Way way) {
		waysList.add(way);
		if (wayCurrent == null) {
			wayCurrent = waysList.get(0);
			speedCurrent = wayCurrent.speedLimit;
		}
	}

}
