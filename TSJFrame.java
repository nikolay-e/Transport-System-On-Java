import java.awt.*;
import javax.swing.*;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

class TSFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private double horizontalScale;
	private double verticalScale;
	
	private double horizontalShift;
	private double verticalShift;

	double scale = 0.8;

    public TSFrame(int width, int height) {
		JPanel panel = new JPanel();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
		setSize(width, height);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		double horizontalMin = TransportSystem.waysList.get(0).coordinates[0][0];
		double horizontalMax = TransportSystem.waysList.get(0).coordinates[0][0];

		double verticalMin = TransportSystem.waysList.get(0).coordinates[0][1];
		double verticalMax = TransportSystem.waysList.get(0).coordinates[0][1];

		for (Way ways : TransportSystem.waysList) {
			horizontalMin = Math.min(horizontalMin, ways.coordinates[0][0]);
			horizontalMin = Math.min(horizontalMin, ways.coordinates[1][0]);
			horizontalMax = Math.max(horizontalMax, ways.coordinates[0][0]);
			horizontalMax = Math.max(horizontalMax, ways.coordinates[1][0]);
			verticalMin = Math.min(verticalMin, ways.coordinates[0][1]);
			verticalMin = Math.min(verticalMin, ways.coordinates[1][1]);
			verticalMax = Math.max(verticalMax, ways.coordinates[0][1]);
			verticalMax = Math.max(verticalMax, ways.coordinates[1][1]);
		};

		horizontalScale = scale * (super.getContentPane().getWidth() / Math.abs(horizontalMax - horizontalMin));
		verticalScale = scale * (super.getContentPane().getHeight() / Math.abs(verticalMax - verticalMin));
		horizontalShift = horizontalMin;
		verticalShift =  verticalMin; 

		for (Way ways : TransportSystem.waysList) {
			double aX = horizontalScale * (ways.coordinates[0][0] - horizontalShift) + super.getContentPane().getWidth()/2 - horizontalScale * (horizontalMax - horizontalMin)/2;
			double aY = verticalScale * (ways.coordinates[0][1] - verticalShift) + super.getContentPane().getHeight()/2 - verticalScale * (verticalMax - verticalMin)/2;
			double bX = horizontalScale * (ways.coordinates[1][0] - horizontalShift) + super.getContentPane().getWidth()/2 - horizontalScale * (horizontalMax - horizontalMin)/2;
			double bY = verticalScale * (ways.coordinates[1][1] - verticalShift) + super.getContentPane().getHeight()/2 - verticalScale * (verticalMax - verticalMin)/2;
			Line2D line = new Line2D.Double(aX, aY, bX, bY);
			g2.draw(line);
		}

		for (Vehicle vehicle : TransportSystem.vehiclesList) {
			double pX = horizontalScale * (horizontalShift + vehicle.coordinates[0]) - 2;
			double pY = verticalScale * (verticalShift + vehicle.coordinates[1]) - 2;
			Rectangle2D point = new Rectangle2D.Double(pX, pY, 4, 4);
			g2.draw(point);
		}

	}

}

