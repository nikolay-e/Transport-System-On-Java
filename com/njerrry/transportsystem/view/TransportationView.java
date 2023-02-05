package com.njerrry.transportsystem.view;

import java.util.List;

import javax.swing.SwingUtilities;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Vehicle;
import com.njerrry.transportsystem.model.Road;

public class TransportationView implements Runnable {

    private volatile boolean stopThread = false;

    private MapFrame mapFrame;
    private MapPanel mapPanel;

    public TransportationView(List<Vehicle> vehicles, List<Road> roads, double horizDelta, double vertDelta,
            Coordinates mapCenter) {
        mapFrame = new MapFrame();
        mapPanel = new MapPanel(vehicles, roads, horizDelta, vertDelta, mapCenter);
        mapFrame.getContentPane().add(mapPanel);

        SwingUtilities.updateComponentTreeUI(mapFrame);
    }

    public void stop() {
        this.stopThread = true;
    }

    @Override
    public void run() {
        while (!stopThread) {
            mapPanel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException occurred while sleeping the thread: " + e.getMessage());
            }
        }
    }
}