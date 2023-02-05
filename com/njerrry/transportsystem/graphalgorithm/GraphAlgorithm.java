package com.njerrry.transportsystem.graphalgorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.njerrry.transportsystem.model.Coordinates;
import com.njerrry.transportsystem.model.Way;

public class GraphAlgorithm {

    public Map<Coordinates, List<Way>> shortestPathsToCurrentNode = new HashMap<Coordinates, List<Way>>();
    public Map<Coordinates, Double> distancesToCurrentNode = new HashMap<Coordinates, Double>();

    class ToBeServed {
        Coordinates node;
        Double length;
        List<Way> waylist;

        public ToBeServed(Coordinates node, List<Way> waylist, Double length) {
            this.node = node;
            this.waylist = waylist;
            this.length = length;
        }
    }

    Queue<ToBeServed> serveNext = new ConcurrentLinkedQueue<>();

    Map<Integer, Coordinates> nodes;
    Map<Coordinates, List<Way>> edges;

    public GraphAlgorithm(Map<Integer, Coordinates> nodes, Map<Coordinates, List<Way>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void calculateDijkstra(Coordinates currentNode) {

        try {
            dijkstra(currentNode, new ArrayList<Way>(), 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int counter = 0;
        ToBeServed ts;
        while (true) {
            try {
                ts = serveNext.poll();
                dijkstra(ts.node, ts.waylist, ts.length);
                System.out.println(++counter);
            } catch (Exception e) {
                break;
            }
        }
    }

    private void dijkstra(Coordinates currentNode, List<Way> mypath, Double mypathLength) throws Exception {

        for (Way way : edges.get(currentNode)) {

            List<Way> myNewpath = new ArrayList<Way>(mypath);
            myNewpath.add(way);

            if (distancesToCurrentNode.get(way.getOppositeNode(currentNode)) == null) {

                distancesToCurrentNode.put(way.getOppositeNode(currentNode), mypathLength + way.getLength());
                shortestPathsToCurrentNode.put(way.getOppositeNode(currentNode), myNewpath);

                serveNext.add(new ToBeServed(way.getOppositeNode(currentNode), myNewpath, mypathLength + way.getLength()));
            } else {
                if (mypathLength + way.getLength() < distancesToCurrentNode.get(way.getOppositeNode(currentNode))) {

                    distancesToCurrentNode.put(way.getOppositeNode(currentNode), mypathLength + way.getLength());
                    shortestPathsToCurrentNode.put(way.getOppositeNode(currentNode), myNewpath);

                }
            }

        }
    }

}
