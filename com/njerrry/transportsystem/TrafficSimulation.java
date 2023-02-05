package com.njerrry.transportsystem;

import com.njerrry.transportsystem.model.Helper;
import com.njerrry.transportsystem.model.TransportSystem;
import com.njerrry.transportsystem.view.TransportationView;

public class TrafficSimulation {
	public static void main(final String[] args) throws Exception {
		TransportSystem ts = Helper.ReadTransportSystem("NA.cnode.txt", "NA.cedge.txt");
		Thread transportSystemThread = new Thread(ts);
		TransportationView tv = new TransportationView(ts.getVehicleList(), ts.getRoadList(), ts.getHorizontalDelta(),
				ts.getVerticalDelta(), ts.getMapCenter());
		Thread transportationViewThread = new Thread(tv);

		transportSystemThread.start();
		transportationViewThread.run();
		return;
	}
}
