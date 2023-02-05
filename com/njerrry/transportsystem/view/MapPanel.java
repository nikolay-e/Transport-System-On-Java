package com.njerrry.transportsystem.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Vehicle;
import com.njerrry.transportsystem.model.Road;

public class MapPanel extends JPanel {

    private final List<Road> roads;
    private final List<Vehicle> vehicles;
    private final double horizontalDelta;
    private final double verticalDelta;
    private final Coordinates mapCenter;

    MapPanel(List<Vehicle> vl, List<Road> wl, double hd, double vd, Coordinates mc) {
        vehicles = vl;
        roads = wl;
        horizontalDelta = hd;
        verticalDelta = vd;
        mapCenter = mc;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        double horizontalScale = 0.8 * (super.getWidth() / Math.abs(horizontalDelta));
        double verticalScale = 0.8 * (super.getHeight() / Math.abs(verticalDelta));
        double scale = Math.min(horizontalScale, verticalScale);

        for (Road road : roads) {

            double startX = scale * (road.getFistNode().getX() - mapCenter.getX()) + super.getWidth() / 2;
            double startY = scale * (road.getFistNode().getY() - mapCenter.getY()) + super.getHeight() / 2;
            double endX = scale * (road.getSecondNode().getX() - mapCenter.getX()) + super.getWidth() / 2;
            double endY = scale * (road.getSecondNode().getY() - mapCenter.getY()) + super.getHeight() / 2;
            Line2D line = new Line2D.Double(startX, startY, endX, endY);

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
        }

        for (Vehicle vehicle : vehicles) {

            double vehicleX = scale * (vehicle.getCoordinates().getX() - mapCenter.getX()) + super.getWidth() / 2;
            double vehicleY = scale * (vehicle.getCoordinates().getY() - mapCenter.getY()) + super.getHeight() / 2;

            Rectangle2D point = new Rectangle2D.Double(vehicleX - 2, vehicleY - 2, 4, 4);

            g2.setPaint(Color.BLUE);
            g2.fill(point);
            g2.draw(point);

            for (Road road : vehicle.getRoadsToTake()) {
                double aaX = scale * (road.getFistNode().getX() - mapCenter.getX())
                        + super.getWidth() / 2;
                double aaY = scale * (road.getFistNode().getY() - mapCenter.getY())
                        + super.getHeight() / 2;
                double bbX = scale * (road.getSecondNode().getX() - mapCenter.getX())
                        + super.getWidth() / 2;
                double bbY = scale * (road.getSecondNode().getY() - mapCenter.getY())
                        + super.getHeight() / 2;
                Line2D line = new Line2D.Double(aaX, aaY, bbX, bbY);

                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(1));
                g2.draw(line);
            }
        }
    }

}
