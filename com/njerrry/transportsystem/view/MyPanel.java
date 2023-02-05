package com.njerrry.transportsystem.view;

import java.awt.*;

import java.util.List;
import java.awt.geom.Rectangle2D;

import java.awt.Graphics;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Vehicle;
import com.njerrry.transportsystem.model.Way;

public class MyPanel extends JPanel {

    private List<Way> wayList;
    private List<Vehicle> vehicleList;

    private double horizontalDelta;
    private double verticalDelta;
    private Coordinates mapCenter = new Coordinates();

    MyPanel(List<Vehicle> vl, List<Way> wl, double hd, double vd, Coordinates mc) {
        vehicleList = vl;
        wayList = wl;
        horizontalDelta = hd;
        verticalDelta = vd;
        mapCenter = mc;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        double horizontalScale = 0.8 * (super.getWidth() / Math.abs(horizontalDelta));
        double verticalScale = 0.8 * (super.getHeight() / Math.abs(verticalDelta));
        double scale = Math.min(horizontalScale, verticalScale);

        for (Way way : wayList) {
            double aX = scale * (way.getFistNode().getX() - mapCenter.getX())
                    + super.getWidth() / 2;
            double aY = scale * (way.getFistNode().getY() - mapCenter.getY())
                    + super.getHeight() / 2;
            double bX = scale * (way.getSecondNode().getX() - mapCenter.getX())
                    + super.getWidth() / 2;
            double bY = scale * (way.getSecondNode().getY() - mapCenter.getY())
                    + super.getHeight() / 2;
            Line2D line = new Line2D.Double(aX, aY, bX, bY);

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.draw(line);
        }

        for (Vehicle vehicle : vehicleList) {

            double aX = scale * (vehicle.getCoordinates().getX() - mapCenter.getX())
                    + super.getWidth() / 2;
            double aY = scale * (vehicle.getCoordinates().getY() - mapCenter.getY())
                    + super.getHeight() / 2;

            Rectangle2D point = new Rectangle2D.Double(aX, aY, 4, 4);

            g2.setPaint(Color.BLUE);
            g2.fill(point);
            g2.draw(point);

            for (Way way : vehicle.getWaysToTakeList()) {
                double aaX = scale * (way.getFistNode().getX() - mapCenter.getX())
                        + super.getWidth() / 2;
                double aaY = scale * (way.getFistNode().getY() - mapCenter.getY())
                        + super.getHeight() / 2;
                double bbX = scale * (way.getSecondNode().getX() - mapCenter.getX())
                        + super.getWidth() / 2;
                double bbY = scale * (way.getSecondNode().getY() - mapCenter.getY())
                        + super.getHeight() / 2;
                Line2D line = new Line2D.Double(aaX, aaY, bbX, bbY);
    
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(1));
                g2.draw(line);
            }
        }
    }

}
