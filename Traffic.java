import java.util.BitSet;
import java.util.ArrayList;

public class Traffic {

	protected static float timeGlobal = 0.0f;

	public static void main(String[] arg){

		Bicycle b = new Bicycle("Petya", 60f);
		Auto auto6 = new Auto("GAZ", 151f, 15, 17, 512);

		Way way1 = new Way(100,20);
		Way way2 = new Way(50,10);

		b.add_way(way1);
		b.add_way(way2);
		auto6.add_way(way2);
		auto6.add_way(way2);

		while(runGlobalTime(150f)) {
			Vehicle.printOut();
			way1.processing();
			way2.processing();

			wait(2000);
		}
	}

	protected static boolean runGlobalTime(float period) {
		timeGlobal += 0.6f;
		return timeGlobal < period;
	}

	protected static void wait(int int_ms){
		try {
			Thread.sleep(int_ms);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
