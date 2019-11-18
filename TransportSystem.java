import java.util.ArrayList;
import java.text.DecimalFormat;
import java.awt.*;
import javax.swing.*;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

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
		frame = new TSFrame(300, 300);
		frame.setVisible(true);

		while (true) {
			SwingUtilities.updateComponentTreeUI(frame);
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
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

class TSFrame extends JFrame{
	public TSFrame(int width, int height){
		JPanel panel = new JPanel();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
		setSize(width, height);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		double hotizontalMin = super.getContentPane().getWidth();
		double hotizontalMax = 0;

		double verticalMin = super.getContentPane().getHeight();
		double verticalMax = 0;

		double hotizontalShift = 0;
		double verticalShift = 0;

		double hotizontalScale = 1.0;
		double verticalScale = 1.0;

		double scale = 1;

		for (Way ways : TransportSystem.waysList) {
			hotizontalMin = Math.min(hotizontalMin, ways.coordinates[0][0]);
			hotizontalMin = Math.min(hotizontalMin, ways.coordinates[1][0]);
			hotizontalMax = Math.max(hotizontalMax, ways.coordinates[0][0]);
			hotizontalMax = Math.max(hotizontalMax, ways.coordinates[1][0]);
			verticalMin = Math.min(verticalMin, ways.coordinates[0][1]);
			verticalMin = Math.min(verticalMin, ways.coordinates[1][1]);
			verticalMax = Math.max(verticalMax, ways.coordinates[0][1]);
			verticalMax = Math.max(verticalMax, ways.coordinates[1][1]);
		};

		hotizontalScale = (super.getContentPane().getWidth() * 0.8) / Math.abs(hotizontalMax - hotizontalMin);
		verticalScale = (super.getContentPane().getHeight() * 0.8) / Math.abs(verticalMax - verticalMin);

		hotizontalShift = (super.getContentPane().getWidth() - hotizontalScale * (hotizontalMax - hotizontalMin)) / 2;
		verticalShift = (super.getContentPane().getHeight() - verticalScale * (verticalMax - verticalMin)) / 2;

		for (Way ways : TransportSystem.waysList) {
			double aX = (hotizontalShift + hotizontalScale * ways.coordinates[0][0]);
			double aY = (verticalShift + verticalScale * ways.coordinates[0][1]);
			double bX = (hotizontalShift + hotizontalScale * ways.coordinates[1][0]);
			double bY = (verticalShift + verticalScale * ways.coordinates[1][1]);

			Line2D line = new Line2D.Double(aX, aY, bX, bY);
			g2.draw(line);
		}

		for (Vehicle vehicle : TransportSystem.vehiclesList) {
			double aX = hotizontalShift + hotizontalScale * (vehicle.coordinates[0]) - 2;
			double aY = verticalShift + verticalScale * (vehicle.coordinates[1]) - 2;

			Rectangle2D point = new Rectangle2D.Double(aX, aY, 4, 4);
			g2.draw(point);
		}
	}
}

