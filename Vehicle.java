import java.util.ArrayList;

public class Vehicle extends TransportSystem {

	protected Way wayCurrent;
	protected int startPoint = 0;

	private String name = "";
	private int id;
	private static int maxId = 0;

	protected double speedMax = 0.0;
	protected double speedCurrent = 0.0;

	protected double coordinates[] = {0.0, 0.0};

	protected double distanceWay = 0.0;
	protected double mileageAll = 0.0;

	private double timeGlobal = 0.0;
	private double timeCurrent = 0.0;

	protected ArrayList<Way> waysToTakeList = new ArrayList<Way>();

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
		
		double delta = (timeGlobal - timeCurrent) * speedCurrent;
		double deltaWay = delta;
		
		if (Double.compare(distanceWay + delta, wayCurrent.length) > 0) {

			double deltaOldWay = wayCurrent.length - distanceWay;

			
			if (waysToTakeList.size() == 1) {
				delta = deltaOldWay;
				deltaWay = deltaOldWay;
			} else if (waysToTakeList.size() > 1) {
				double deltaNewWay = delta - deltaOldWay;
				waysToTakeList.remove(0);
				Way tmpWayCurrent = waysToTakeList.get(0);

				//TODO precise calculation of distances in respect to different speed
				
				double distToStartPoint0 = distance(wayCurrent.coordinates[1-startPoint], tmpWayCurrent.coordinates[0]);
				double distToStartPoint1 = distance(wayCurrent.coordinates[1-startPoint], tmpWayCurrent.coordinates[1]);

				if (Double.compare(Math.min(distToStartPoint0, distToStartPoint1), 0.0) > 0) {
					delta = deltaOldWay;
					deltaWay = deltaOldWay;
				} else {
					double cos = (wayCurrent.coordinates[1 - startPoint][0] - wayCurrent.coordinates[startPoint][0]) / wayCurrent.length;
					double sin = (wayCurrent.coordinates[1 - startPoint][1] - wayCurrent.coordinates[startPoint][1]) / wayCurrent.length;

					coordinates[0] += cos * deltaOldWay;
					coordinates[1] += sin * deltaOldWay;

					if (distToStartPoint0 > distToStartPoint1) {
						startPoint = 1;
					} else  {
						startPoint = 0;
					}
					wayCurrent = tmpWayCurrent;
					distanceWay = 0;
					deltaWay = deltaNewWay;
				}

			} else {
				System.out.println("ERROR waysToTakeList is empty!");
			}

		}

		mileageAll = mileageAll + delta;
		distanceWay = distanceWay + deltaWay;

		timeCurrent = timeGlobal;

		double cos = (wayCurrent.coordinates[1 - startPoint][0] - wayCurrent.coordinates[startPoint][0]) / wayCurrent.length;
		double sin = (wayCurrent.coordinates[1 - startPoint][1] - wayCurrent.coordinates[startPoint][1]) / wayCurrent.length;

		coordinates[0] += cos * deltaWay;
		coordinates[1] += sin * deltaWay;

		return delta;
	}

	protected double distance(double[] pointA, double[] pointB) {
		return Math.sqrt(Math.pow(pointA[0] - pointB[0], 2) + Math.pow(pointA[1] - pointB[1], 2));
	}
	

	protected double speed() {
		if (waysToTakeList.size() == 1 && wayCurrent.length == distanceWay)
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

		System.out.printf("%-15s", decimalFormat.format(speedMax));
		System.out.printf("%-15s",decimalFormat.format(speedCurrent));
		System.out.printf("%-15s", decimalFormat.format(mileageAll));
		System.out.printf("%-15s", decimalFormat.format(timeCurrent));

	}

	public void addWay(Way way, int startPoint) {
		this.startPoint = startPoint;
		waysToTakeList.add(way);
		if (wayCurrent == null) {
			wayCurrent = waysToTakeList.get(0);
			speedCurrent = wayCurrent.speedLimit;
			coordinates[0] = wayCurrent.coordinates[startPoint][0];
			coordinates[1] = wayCurrent.coordinates[startPoint][1];
		}
	}

	public void addWay(Way way) {
		waysToTakeList.add(way);
	}

}
