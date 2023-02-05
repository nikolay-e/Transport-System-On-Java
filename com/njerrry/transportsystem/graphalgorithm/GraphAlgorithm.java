package com.njerrry.transportsystem.graphalgorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Road;

public class GraphAlgorithm {

    public Map<Coordinates, List<Road>> shortestPathsToCurrentNode = new HashMap<Coordinates, List<Road>>();
    public Map<Coordinates, Double> distancesToCurrentNode = new HashMap<Coordinates, Double>();

    class ToBeServed {
        Coordinates node;
        Double length;
        List<Road> roadlist;

        public ToBeServed(Coordinates node, List<Road> roadlist, Double length) {
            this.node = node;
            this.roadlist = roadlist;
            this.length = length;
        }
    }

    Queue<ToBeServed> serveNext = new ConcurrentLinkedQueue<>();

    Map<Integer, Coordinates> nodes;
    Map<Coordinates, List<Road>> edges;

    public GraphAlgorithm(Map<Integer, Coordinates> nodes, Map<Coordinates, List<Road>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void calculateDijkstra(Coordinates currentNode) {

        try {
            dijkstra(currentNode, new ArrayList<Road>(), 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int counter = 0;
        ToBeServed ts;
        while (true) {
            try {
                ts = serveNext.poll();
                dijkstra(ts.node, ts.roadlist, ts.length);
                System.out.println(++counter);
            } catch (Exception e) {
                break;
            }
        }
    }

    private void dijkstra(Coordinates currentNode, List<Road> mypath, Double mypathLength) throws Exception {

        for (Road road : edges.get(currentNode)) {

            List<Road> myNewpath = new ArrayList<Road>(mypath);
            myNewpath.add(road);

            if (distancesToCurrentNode.get(road.getOppositeNode(currentNode)) == null) {

                distancesToCurrentNode.put(road.getOppositeNode(currentNode), mypathLength + road.getLength());
                shortestPathsToCurrentNode.put(road.getOppositeNode(currentNode), myNewpath);

                serveNext.add(
                        new ToBeServed(road.getOppositeNode(currentNode), myNewpath, mypathLength + road.getLength()));
            } else {
                if (mypathLength + road.getLength() < distancesToCurrentNode.get(road.getOppositeNode(currentNode))) {

                    distancesToCurrentNode.put(road.getOppositeNode(currentNode), mypathLength + road.getLength());
                    shortestPathsToCurrentNode.put(road.getOppositeNode(currentNode), myNewpath);

                }
            }

        }
    }

}
