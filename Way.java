public class Way extends TransportSystem {

    protected int length;
    protected int speedLimit;
    protected String name = "WAY";

    public Way(String name, int length, int limit){
        this.name = name;
        this.length = length;
        this.speedLimit = limit;
    }

}
