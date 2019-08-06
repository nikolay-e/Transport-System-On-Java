import java.util.ArrayList;


public class Way extends TransportSystem {

    protected int length;
    protected int speedLimit;
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    public Way(int iLength, int iLimit){
        length = iLength;
        speedLimit = iLimit;
    }

    protected void add_vehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public static void processing() {
        for (Vehicle vehicle : vehicles) {
            vehicle.processing();
        }
    }
}
