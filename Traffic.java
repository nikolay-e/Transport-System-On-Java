public class Traffic {

	public static float timeGlobal = 0.0f;	//

	public static void main(String[] arg){

		Vehicle auto0 = new Vehicle();
		Vehicle auto1 = new Vehicle("BMW", 260f);
		Vehicle auto2 = new Vehicle("Audi", 200f);
		Vehicle auto3 = new Vehicle("Toyota", 235f);
		Vehicle auto4 = new Vehicle("UAZ", 180f);
		Auto auto5 = new Auto();	
		Auto auto6 = new Auto("GAZ", 151f);	

	
	
		while(timeGlobal < 15f) {

			Vehicle.processing();
			Vehicle.printOut();
			timeGlobal += 0.01f;

			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();						
			}
		}
	}
}
