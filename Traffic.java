import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Traffic {

	protected static double timeGlobal = 0.0;

	public static void main(final String[] arg) throws Exception {

		Map<Integer, Node> nodes = new HashMap<Integer, Node>();
		Map<Node, ArrayList<Way>> ways = new HashMap<Node, ArrayList<Way>>();

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cnode.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] l = line.split(" ");
				int nodeId = Integer.parseInt(l[0]);
				nodes.put(nodeId, new Node(Double.parseDouble(l[1]), -Double.parseDouble(l[2])));
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cedge.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] l = line.split(" ");
				int firstNodeId = Integer.parseInt(l[1]);
				int secondNodeId = Integer.parseInt(l[2]);
				Way way = new Way(nodes.get(firstNodeId), nodes.get(secondNodeId));

				//new Vehicle().addWay(way);

				if (ways.get(nodes.get(firstNodeId)) == null) {
					ways.put(nodes.get(firstNodeId), new ArrayList<Way>());
					ways.get(nodes.get(firstNodeId)).add(way);
				} else {
					ways.get(nodes.get(firstNodeId)).add(way);
				}

				if (ways.get(nodes.get(secondNodeId)) == null) {
					ways.put(nodes.get(secondNodeId), new ArrayList<Way>());
					ways.get(nodes.get(secondNodeId)).add(way);
				} else {
					ways.get(nodes.get(secondNodeId)).add(way);
				}

			}
		}

		new Thread() {
			public void run() {
				MyGraphics.init();
			}
		}.start();

		while (runtimeGlobal(10000000)) {
			TransportSystem.printTS();
			TransportSystem.runTS(timeGlobal);
			wait(1);
		}
	}

	protected static boolean runtimeGlobal(final double period) {
		timeGlobal += 1;
		return timeGlobal < period;
	}

	protected static void wait(final int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
