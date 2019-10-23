
public class Traffic {

	protected static double timeGlobal = 0.0;
	public static void main(String[] arg){
		new Thread() {
			public void run(){
				TransportSystem.runGUI();
			}
		}.start();
		
		Bicycle bicycle = new Bicycle("Petya", 60);
		Auto auto = new Auto("GAZ", 151.0, 15, 100000, 100000);
		double w1[][] = {{200.,60.},{320.,150.}};
		double w2[][] = {{200.,60.},{80.,90.}};
		double w3[][] = {{320.,300.},{170.,300.}};
		double w4[][] = {{320.,300.},{320.,150.}};
		double w5[][] = {{320.,300.},{680.,300.}};
		double w6[][] = {{320.,420.},{320.,300.}};
		double w7[][] = {{350.,510.},{320.,420.}};
		double w8[][] = {{350.,510.},{500.,570.}};
		double w9[][] = {{680.,40.},{680.,300.}};
		double w10[][] = {{680.,570.},{500.,570.}};
		double w11[][] = {{680.,570.},{680.,300.}};
		double w12[][] = {{680.,570.},{850.,570.}};
		double w13[][] = {{70.,250.},{170.,300.}};
		double w14[][] = {{70.,250.},{80.,90.}};
		double w15[][] = {{850.,300.},{680.,300.}};
		double w16[][] = {{850.,570.},{970.,500.}};
		double w17[][] = {{970.,390.},{850.,300.}};
		double w18[][] = {{970.,390.},{970.,500.}};

		Way way1 = new Way(30, w1);
		Way way2 = new Way(30, w2);
		Way way3 = new Way(30, w3);
		Way way4 = new Way(30, w4);
		Way way5 = new Way(30, w5);
		Way way6 = new Way(30, w6);
		Way way7 = new Way(30, w7);
		Way way8 = new Way(30, w8);
		Way way9 = new Way(30, w9);
		Way way10 = new Way(30, w10);
		Way way11 = new Way(30, w11);
		Way way12 = new Way(30, w12);
		Way way13 = new Way(30, w13);
		Way way14 = new Way(30, w14);
		Way way15 = new Way(30, w15);
		Way way16 = new Way(30, w16);
		Way way17 = new Way(30, w17);
		Way way18 = new Way(30, w18);

		bicycle.addWay(way1, 0);
		auto.addWay(way1, 0);

		for (int i = 0; i < 10000 ; i++) {
			auto.addWay(way2);
			auto.addWay(way3);
			auto.addWay(way4);
			auto.addWay(way5);
			auto.addWay(way6);
			auto.addWay(way7);
			auto.addWay(way8);
			auto.addWay(way9);
			auto.addWay(way10);
			auto.addWay(way11);
			auto.addWay(way12);
			auto.addWay(way13);
			auto.addWay(way14);
			auto.addWay(way15);
			auto.addWay(way16);
			auto.addWay(way17);
			auto.addWay(way18);
			auto.addWay(way1);
		}

		while(runtimeGlobal(1000000)) {
			TransportSystem.printTS();
			TransportSystem.runTS(timeGlobal);
			//wait(1);
		}
	}

	protected static boolean runtimeGlobal(double period) {
		timeGlobal += 1;
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
