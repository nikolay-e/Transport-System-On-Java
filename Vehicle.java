import java.util.ArrayList;

public class Vehicle extends TransportSystem {

	protected Way currentWay;
	protected Node startNodeOnCurrentWay;

	private String name = "";
	private int id;
	private static int maxId = 0;

	protected double speedMax = 0.0;

	protected Coordinates coordinates;

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
		initialize();
	}

	private void initialize() {
		this.id = maxId;
		maxId += 1;
		vehiclesList.add(this);
		this.coordinates = new Coordinates(0.0, 0.0);
	}

	protected double run(double globalTime) throws Exception {
		timeGlobal = globalTime;
		double currentSpeed = getCurrentSpeed();
		double delta = (timeGlobal - timeCurrent) * currentSpeed;
		double deltaWay = delta;
		if (Double.compare(distanceWay + delta, currentWay.length) > 0) {
			double deltaOldWay = currentWay.length - distanceWay;
			if (waysToTakeList.size() == 1) {
				delta = deltaOldWay;
				deltaWay = deltaOldWay;
			} else if (waysToTakeList.size() > 1) {
				double deltaNewWay = delta - deltaOldWay;
				waysToTakeList.remove(0);
				Way tmpWayCurrent = waysToTakeList.get(0);
				// TODO precise calculation of distances in respect to different speed
				double distToFistNode = distance(currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates,
						tmpWayCurrent.fistNode.coordinates);
				double distToSecondNode = distance(currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates,
						tmpWayCurrent.secondNode.coordinates);
				if (Double.compare(Math.min(distToFistNode, distToSecondNode), 0.0) > 0) {
					delta = deltaOldWay;
					deltaWay = deltaOldWay;
				} else {
					double cos = (currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates.x - startNodeOnCurrentWay.coordinates.x)
							/ currentWay.length;
					double sin = (currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates.y - startNodeOnCurrentWay.coordinates.y)
							/ currentWay.length;
					coordinates.x += cos * deltaOldWay;
					coordinates.y += sin * deltaOldWay;
					if (distToFistNode > distToSecondNode) {
						startNodeOnCurrentWay = tmpWayCurrent.secondNode;
					} else {
						startNodeOnCurrentWay = tmpWayCurrent.fistNode;
					}
					currentWay = tmpWayCurrent;
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
		double cos = (currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates.x - startNodeOnCurrentWay.coordinates.x)
				/ currentWay.length;
		double sin = (currentWay.getOppositeNode(startNodeOnCurrentWay).coordinates.y - startNodeOnCurrentWay.coordinates.y)
				/ currentWay.length;
		coordinates.x += cos * deltaWay;
		coordinates.y += sin * deltaWay;
		return delta;
	}

	static public double distance(Coordinates pointA, Coordinates pointB) {
		return Math.sqrt(Math.pow(pointA.x - pointB.x, 2) + Math.pow(pointA.y - pointB.y, 2));
	}

	protected double getCurrentSpeed() {
		if (waysToTakeList.size() == 1 && currentWay.length == distanceWay)
			return 0.0;
		else return currentWay.speedLimit < speedMax ? currentWay.speedLimit : speedMax;
	}

	protected void print() {
		System.out.printf("%-5s", id);
		System.out.printf("%-15s", name);

		if (currentWay == null)
			System.out.printf("%-15s", "NOWAY!!!");
		else
			System.out.printf("%-15s", currentWay.name);

		System.out.printf("%-15s", decimalFormat.format(speedMax));
		System.out.printf("%-15s", decimalFormat.format(getCurrentSpeed()));
		System.out.printf("%-15s", decimalFormat.format(mileageAll));
		System.out.printf("%-15s", decimalFormat.format(timeCurrent));
	}

	public void addWay(Way way, Node startNodeOnCurrentWay) {
		this.startNodeOnCurrentWay = startNodeOnCurrentWay;
		waysToTakeList.add(way);
		if (currentWay == null) {
			currentWay = waysToTakeList.get(0);
			coordinates.x = startNodeOnCurrentWay.coordinates.x;
			coordinates.y = startNodeOnCurrentWay.coordinates.y;
		}
	}

	public void addWay(Way way) {
		this.startNodeOnCurrentWay = way.fistNode;
		waysToTakeList.add(way);
		if (currentWay == null) {
			currentWay = waysToTakeList.get(0);
			coordinates = startNodeOnCurrentWay.coordinates;
		}
	}
}
