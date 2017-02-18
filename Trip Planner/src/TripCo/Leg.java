package TripCo;

public class Leg {

    private int sequence;
    private String startName;
    private String startCity;
    private int startAltitude;
    private Coordinates startCoords;
    private String finishName;
    private String finishCity;
    private int finishAltitude;
    private Coordinates finishCoords;
    private float mileage;

    public Leg(int s, float m){
        //purposely left most empty. Loading will be done with other methods to avoid 4 trillion args
        this.sequence = s;
        this.mileage = m;
    }

    public void loadStart(String name, String city, int altitude, Coordinates coords){
        this.startName = name;
        this.startCity = city;
        this.startAltitude = altitude;
        this.startCoords = coords;
    }

    public void loadFinish(String name, String city, int altitude, Coordinates coords){
        this.finishName = name;
        this.finishCity = city;
        this.finishAltitude = altitude;
        this.finishCoords = coords;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public String getStartName() {
        return startName;
    }

    public String getStartCity() {
        return startCity;
    }

    public int getStartAltitude() {
        return startAltitude;
    }

    public Coordinates getStartCoords() {
        return startCoords;
    }

    public String getFinishName() {
        return finishName;
    }

    public String getFinishCity() {
        return finishCity;
    }

    public int getFinishAltitude() {
        return finishAltitude;
    }

    public Coordinates getFinishCoords() {
        return finishCoords;
    }

    public float getMileage() {
        return mileage;
    }
}