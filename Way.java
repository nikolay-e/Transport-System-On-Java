public class Way extends TransportSystem {

	protected double length;
	protected int speedLimit;
	protected String name = "DEFAULT_WAY_NAME";
	// private boolean isOvertakeProhibited = false;
	protected Node fistNode;
	protected Node secondNode;
	protected boolean colorBlack = true;

	public Way(String name, int limit, double coordinates[][]) {
		this.name = name;
		this.fistNode = new Node(coordinates[0][0], coordinates[0][1]);
		this.secondNode = new Node(coordinates[1][0], coordinates[1][1]);
		this.length = Vehicle.distance(fistNode.coordinates, secondNode.coordinates);
		this.speedLimit = limit;
		waysList.add(this);
	}

	public Way(int limit, double coordinates[][]) {
		this.fistNode = new Node(coordinates[0][0], coordinates[0][1]);
		this.secondNode = new Node(coordinates[1][0], coordinates[1][1]);
		this.length = Vehicle.distance(fistNode.coordinates, secondNode.coordinates);
		this.speedLimit = limit;
		waysList.add(this);
	}

	public Way(Node fistNode, Node secondNode) {
		this.fistNode = fistNode;
		this.secondNode = secondNode;
		this.length = Vehicle.distance(fistNode.coordinates, secondNode.coordinates);
		this.speedLimit = 60;
		waysList.add(this);
	}

	public Node getOppositeNode(Node node) throws Exception {
		if (node == fistNode) {
			return secondNode;
		} else if (node == secondNode) {
			return fistNode;
		} else {
			throw new Exception();
		}
	}

}
