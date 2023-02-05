package com.njerrry.transportsystem.view;

import java.util.List;

import javax.swing.SwingUtilities;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Vehicle;
import com.njerrry.transportsystem.model.Way;

public class MyGraphics {

	private List<Vehicle> vehicleList;
	private List<Way> wayList;
    private double horizontalDelta;
    private double verticalDelta;
    private Coordinates mapCenter = new Coordinates();

	protected static MyFrame frame = new MyFrame();

	public MyGraphics(List<Vehicle> vl, List<Way> wl, double hd, double vd, Coordinates mc) {
		vehicleList = vl;
		wayList = wl;
		horizontalDelta = hd;
        verticalDelta = vd;
        mapCenter = mc;
	}

	public void init() {
		SwingUtilities.updateComponentTreeUI(frame);

		MyPanel myPanel = new MyPanel(vehicleList, wayList, horizontalDelta, verticalDelta, mapCenter);
		frame.getContentPane().add(myPanel);

		while (true) {
			myPanel.repaint();
		}
	}
}