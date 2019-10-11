
public class Traffic {

	protected static double timeGlobal = 0.0;

	public static void main(String[] arg){

		Bicycle bicycle = new Bicycle("Petya", 60);
		Auto auto = new Auto("GAZ", 151.0, 15, 100, 512);

		Way way1 = new Way("Way1", 33, 20);
		Way way2 = new Way("Way2", 99, 10);

		bicycle.addWay(way1);
		auto.addWay(way2);
		auto.addWay(way1);

		while(runtimeGlobal(150)) {
			TransportSystem.printTS();
			TransportSystem.runTS(timeGlobal);
			wait(1000);
		}
	}

	protected static boolean runtimeGlobal(double period) {
		timeGlobal += 0.5;
		return timeGlobal < period;
	}

	protected static void wait(int milliSec){
		try {
			Thread.sleep(milliSec);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
