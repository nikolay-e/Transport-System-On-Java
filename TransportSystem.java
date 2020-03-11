import java.util.ArrayList;
import java.text.DecimalFormat;

import javax.swing.*;

public class TransportSystem {

	protected static ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
	protected static ArrayList<Way> waysList = new ArrayList<Way>();
	protected static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	protected static JFrame frame;

	public static void runTS(double globalTime) {
		for (Vehicle vehichle : vehiclesList) {
			vehichle.run(globalTime);
		}
	}

	public static void runGUI () {
		while (true) {
			frame.repaint();
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void paintMap () {
		frame = new TSFrame(500, 500);
		frame.setVisible(true);
		SwingUtilities.updateComponentTreeUI(frame);
	}

	public static void printTS() {
		System.out.println();
		System.out.printf("Global Time:  %s\n", decimalFormat.format(Traffic.timeGlobal));
		System.out.printf(
			"%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n",
			"ID", "Name", "Way", "Max Speed", "Current Speed", "Mileage", "Current Time", "Consumption", "Tank Volume", "Tank Content"
		);
		for (Vehicle vehichle : vehiclesList) {
			vehichle.print();
			System.out.println();
		}
		System.out.println();
	}
}

 