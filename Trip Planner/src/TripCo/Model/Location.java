package TripCo.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
public class Location {
    protected String name;
    protected String stringLatitude;
    protected String stringLongitude;
    protected double dblLatitude;
    protected double dblLongitude;

    public Location(String ID, String name, String latitude, String longitude){
        this.name = name;
        this.stringLatitude = latitude;
        this.stringLongitude = longitude;

        parseLatitude();
        parseLongitude();
    }

    public double getIntLatitude(){
        return dblLatitude;
    }

    public double getIntLongitude(){
        return dblLongitude;
    }

    public String getName() {
        return name;
    }

    public String getStringLatitude() {
        return stringLatitude;
    }

    public String getStringLongitude() {
        return stringLongitude;
    }

    private void parseLongitude(){
        //convert string longitude to double representation
    }

    private void parseLatitude(){
        //convert string latitude to double representation
    }
}
