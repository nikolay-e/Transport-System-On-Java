package com.njerrry.transportsystem;

import com.njerrry.transportsystem.model.Helper;
import com.njerrry.transportsystem.model.TransportSystem;
import com.njerrry.transportsystem.view.MyGraphics;

public class TrafficSimulation {

	public static void main(final String[] args) throws Exception {
		TransportSystem ts = Helper.ReadTransportSystem("NA.cnode.txt", "NA.cedge.txt");
		Thread transportSystemThread = new Thread(ts);
		MyGraphics mg = new MyGraphics(ts.getVehicleList(), ts.getWayList(), ts.getHorizontalDelta(), ts.getVerticalDelta(), ts.getMapCenter());

		transportSystemThread.start();
		mg.init();
		return;
	}
}
