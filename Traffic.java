import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Traffic {

	protected static double timeGlobal = 0.0;

	public static void main(final String[] arg) throws IOException {

		Map<Integer, Node> nodes = new HashMap<Integer, Node>(); 
		Map<Integer, Way> ways = new HashMap<Integer, Way>(); 

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cnode.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] l = line.split(" ");
				int id = Integer.parseInt(l[0]);
				double[] coordinates = { Double.parseDouble(l[1]),  - Double.parseDouble(l[2]) };
				nodes.put(id, new Node(id, coordinates));
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cedge.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] l = line.split(" ");
				int wayId = Integer.parseInt(l[0]);
				int firstNodeId = Integer.parseInt(l[1]);
				int secondNodeId = Integer.parseInt(l[2]);
				ways.put(wayId, new Way( nodes.get(firstNodeId), nodes.get(secondNodeId)));
			}
		}

		new Thread() {
			public void run() {
				TransportSystem.paintMap();
				//TransportSystem.runGUI();
			}
		}.start();

		// final Bicycle bicycle = new Bicycle("Petya", 60);
		// final Auto auto = new Auto("GAZ", 151.0, 15, 100000, 100000);
		// final double w1[][] = { { 200., 60. }, { 320., 150. } };
		// final Way way1 = new Way(30, w1);

		// bicycle.addWay(way1, 0);
		// auto.addWay(way1, 0);

		// for (int i = 0; i < 10000; i++) {
		// 	auto.addWay(way2);
		// }

		while (runtimeGlobal(1000000)) {
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
