package com.njerrry.transportsystem.model;

public class Road {

	private double length;
	protected final int DEFAULT_SPEED_LIMIT = 100;
	protected int speedLimit;

	protected String name = "DEFAULT_WAY_NAME";
	// private boolean isOvertakeProhibited = false;
	private Coordinates fistNode;
	private Coordinates secondNode;
	protected boolean colorBlack = true;

	public Road(Coordinates fn, Coordinates sn) {
		this.fistNode = fn;
		this.secondNode = sn;
		this.length = Coordinates.distance(fistNode, secondNode);
		this.speedLimit = DEFAULT_SPEED_LIMIT;
	}

	public double getLength() {
		return length;
	}

	public Coordinates getSecondNode() {
		return secondNode;
	}

	public Coordinates getFistNode() {
		return fistNode;
	}

	public Coordinates getOppositeNode(Coordinates node) throws Exception {
		if (node == getFistNode()) {
			return getSecondNode();
		} else if (node == getSecondNode()) {
			return getFistNode();
		} else {
			throw new Exception();
		}
	}

}
