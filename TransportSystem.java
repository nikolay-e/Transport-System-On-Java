import java.util.ArrayList;
import java.text.DecimalFormat;

public class TransportSystem {
    
    protected static ArrayList<Vehicle> vehiclesList = new ArrayList<Vehicle>();
    protected static DecimalFormat df = new DecimalFormat("0.00");


    public static void runTS(double globalTime) {
		for (Vehicle veh : vehiclesList) {
			veh.run(globalTime);
		}
    }
    
    public static void printTS() {
        System.out.println();
        
        System.out.printf("Global Time:  %s\n", df.format(Traffic.timeGlobal));
        
        System.out.printf(
            "%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n",
            "ID", "Name", "Way", "Max Speed", "Current Speed", "Mileage", "Current Time", "Consumption", "Tank Volume", "Tank Content"
        );

		for (Vehicle veh : vehiclesList) {
			veh.print();
			System.out.println();
		}
		System.out.println();
	}
    
}
