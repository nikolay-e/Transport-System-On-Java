public class Traffic {

	public static double timeGlobal = 0.0;	

	public static void main(String[] args) {
		Vehicle auto1 = new Vehicle("BMW", 260.5);
		Vehicle auto2 = new Vehicle("Audi", 200.5);
		Vehicle auto3 = new Vehicle("Toyota", 360.5);
		Vehicle auto4 = new Vehicle("UAZ", 460.5);
		Vehicle auto5 = new Vehicle("GAZ", 60.5);
	
		for(; timeGlobal < 15; timeGlobal += 0.01) {

			Vehicle.processing();
			Vehicle.printOut();

			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();						
			}
		}
	}
}
