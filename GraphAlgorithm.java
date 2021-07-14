import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GraphAlgorithm {

    public Map<Node, ArrayList<Way>> shortestPathsToCurrentNode = new HashMap<Node, ArrayList<Way>>();
    public Map<Node, Double> distancesToCurrentNode = new HashMap<Node, Double>();

    class ToBeServed {
        Node node;
        Double length;
        ArrayList<Way> waylist;

        public ToBeServed(Node node, ArrayList<Way> waylist, Double length) {
            this.node = node;
            this.waylist = waylist;
            this.length = length;
        }
    }

    Queue<ToBeServed> serveNext = new ConcurrentLinkedQueue<>();

    Map<Integer, Node> nodes;
    Map<Node, ArrayList<Way>> edges;

    public GraphAlgorithm(Map<Integer, Node> nodes, Map<Node, ArrayList<Way>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void calculateDijkstra(Node currentNode) {

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

    public void dijkstra(Node currentNode, ArrayList<Way> mypath, Double mypathLength) throws Exception {

        for (Way way : edges.get(currentNode)) {

            ArrayList<Way> myNewpath = new ArrayList<Way>(mypath);
            myNewpath.add(way);

            if (distancesToCurrentNode.get(way.getOppositeNode(currentNode)) == null) {

                distancesToCurrentNode.put(way.getOppositeNode(currentNode), mypathLength + way.length);
                shortestPathsToCurrentNode.put(way.getOppositeNode(currentNode), myNewpath);

                serveNext.add(new ToBeServed(way.getOppositeNode(currentNode), myNewpath, mypathLength + way.length));
            } else {
                if (mypathLength + way.length < distancesToCurrentNode.get(way.getOppositeNode(currentNode))) {

                    distancesToCurrentNode.put(way.getOppositeNode(currentNode), mypathLength + way.length);
                    shortestPathsToCurrentNode.put(way.getOppositeNode(currentNode), myNewpath);

                }
            }

        }
    }

}
