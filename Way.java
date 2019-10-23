// import javafx.util.Pair;

public class Way extends TransportSystem {

    protected double length;
    protected int speedLimit;
    protected String name = "DEFAULT_WAY_NAME";
    //private boolean isOvertakeProhibited = false;
    protected double coordinates[][] = {{0.0, 0.0}, {100.0 ,100.0}};

    public Way(String name, int limit, double coordinates[][]){
        waysList.add(this);
        this.name = name;
        this.length = Math.sqrt((coordinates[0][0] - coordinates[1][0])*(coordinates[0][0] - coordinates[1][0]) + (coordinates[0][1] - coordinates[1][1])*(coordinates[0][1] - coordinates[1][1]));
        this.speedLimit = limit;
        this.coordinates = coordinates;
    }

    public Way(int limit, double coordinates[][]){
        waysList.add(this);
        this.length = Math.sqrt((coordinates[0][0] - coordinates[1][0])*(coordinates[0][0] - coordinates[1][0]) + (coordinates[0][1] - coordinates[1][1])*(coordinates[0][1] - coordinates[1][1]));
        this.speedLimit = limit;
        this.coordinates = coordinates;
    }

}
