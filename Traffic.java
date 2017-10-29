public class Traffic {

	public static double timeGlobal = 0.0;	

	public static void main(String[] arg){

	Vehicle veh1 = new Vehicle();
	Vehicle veh2 = new Vehicle("PID", -95.666);

	Vehicle byc1 = new Bycicle();
	Vehicle byc2 = new Bycicle("BMX", 15);

	Vehicle aut1 = new Auto();
	Vehicle aut2 = new Auto("MOS", 56);

	Vehicle.printOut();

	}
	
	public void testPart1() {

		Vehicle auto1 = new Vehicle("BMW", 260);
		Vehicle auto2 = new Vehicle("Audi", 200);
		Vehicle auto3 = new Vehicle("Toyota", 235);
		Vehicle auto4 = new Vehicle("UAZ", 180);
		Vehicle auto5 = new Vehicle("GAZ", 151);
	
		for(; timeGlobal < 15; timeGlobal += 0.01) {

			Vehicle.processing();
			Vehicle.printOut();

			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();						
			}
		}
	}
}
