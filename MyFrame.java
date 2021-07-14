import javax.swing.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MyFrame(int width, int height, MyPanel panel) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.getContentPane().add(panel);
		setSize(width, height);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		double horizontalMin = TransportSystem.waysList.get(0).fistNode.coordinates.x;
		double horizontalMax = TransportSystem.waysList.get(0).fistNode.coordinates.x;

		double verticalMin = TransportSystem.waysList.get(0).fistNode.coordinates.y;
		double verticalMax = TransportSystem.waysList.get(0).fistNode.coordinates.y;

		for (Way ways : TransportSystem.waysList) {
			horizontalMin = Math.min(horizontalMin, ways.fistNode.coordinates.x);
			horizontalMin = Math.min(horizontalMin, ways.secondNode.coordinates.x);
			horizontalMax = Math.max(horizontalMax, ways.fistNode.coordinates.x);
			horizontalMax = Math.max(horizontalMax, ways.secondNode.coordinates.x);
			verticalMin = Math.min(verticalMin, ways.fistNode.coordinates.y);
			verticalMin = Math.min(verticalMin, ways.secondNode.coordinates.y);
			verticalMax = Math.max(verticalMax, ways.fistNode.coordinates.y);
			verticalMax = Math.max(verticalMax, ways.secondNode.coordinates.y);
		}

		double horizontalScale = 0.8 * (super.getContentPane().getWidth() / Math.abs(horizontalMax - horizontalMin));
		double verticalScale = 0.8 * (super.getContentPane().getHeight() / Math.abs(verticalMax - verticalMin));

		MyGraphics.horizontalShift = horizontalMin;
		MyGraphics.verticalShift =  verticalMin; 

		MyGraphics.scale = Math.min(horizontalScale, verticalScale);

		MyGraphics.mapCenter.x = (horizontalMin + horizontalMax) / 2;
		MyGraphics.mapCenter.y = (verticalMin + verticalMax) / 2;


		for (Way ways : TransportSystem.waysList) {
			double aX = MyGraphics.scale * (ways.fistNode.coordinates.x - MyGraphics.mapCenter.x)
					+ super.getContentPane().getWidth() / 2;
			double aY = MyGraphics.scale * (ways.fistNode.coordinates.y - MyGraphics.mapCenter.y)
					+ super.getContentPane().getHeight() / 2;
			double bX = MyGraphics.scale * (ways.secondNode.coordinates.x - MyGraphics.mapCenter.x)
					+ super.getContentPane().getWidth() / 2;
			double bY = MyGraphics.scale * (ways.secondNode.coordinates.y - MyGraphics.mapCenter.y)
					+ super.getContentPane().getHeight() / 2;
			Line2D line = new Line2D.Double(aX, aY, bX, bY);
			if (!ways.colorBlack) {
				g2.setColor(Color.RED);
				g2.setStroke(new BasicStroke(3));

			} else {
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(1));
			}
			g2.draw(line);
		}

		for (Vehicle vehicle : TransportSystem.vehiclesList) {

			double aX = MyGraphics.scale * (vehicle.coordinates.x - MyGraphics.mapCenter.x)
					+ super.getContentPane().getWidth() / 2;
			double aY = MyGraphics.scale * (vehicle.coordinates.y - MyGraphics.mapCenter.y)
					+ super.getContentPane().getHeight() / 2;

			Rectangle2D point = new Rectangle2D.Double(aX, aY, 4, 4);
			g2.draw(point);
		}

	}

}
