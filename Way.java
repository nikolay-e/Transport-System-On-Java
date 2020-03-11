public class Way extends TransportSystem {

	protected double length;
	protected int speedLimit;
	protected String name = "DEFAULT_WAY_NAME";
	//private boolean isOvertakeProhibited = false;
	protected double coordinates[][] = {{0.0, 0.0}, {100.0 ,100.0}};

	public Way(String name, int limit, double coordinates[][]){
		this.name = name;
		this.length = Vehicle.distance(coordinates[0], coordinates[1]);
		this.speedLimit = limit;
		this.coordinates = coordinates;
		waysList.add(this);
	}

	public Way(int limit, double coordinates[][]){
		this.length = Vehicle.distance(coordinates[0], coordinates[1]);
		this.speedLimit = limit;
		this.coordinates = coordinates;
		waysList.add(this);
	}

	public Way(Node one, Node two){
		this.coordinates[0] = one.coordinates;
		this.coordinates[1] = two.coordinates;
		this.length = Vehicle.distance(coordinates[0], coordinates[1]);
		this.speedLimit = 60;
		waysList.add(this);
	}
	
}
