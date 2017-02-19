package TripCo;


public class Coordinates {

    private int latDegrees;
    private int latMinutes;
    private int latSeconds;
    private char latDirection;

    private int longDegrees;
    private int longMinutes;
    private int longSeconds;
    private char longDirection;

    public Coordinates(int latd, int latm, int lats, char latdir, int longd, int longm, int longs, char longdir){
        this.latDegrees = latd;
        this.latMinutes = latm;
        this.latSeconds =lats;
        this.latDirection = latdir;
        this.longDegrees = longd;
        this.longMinutes = longm;
        this.longSeconds = longs;
        this.longDirection = longdir;

    }

    public int getLatDegrees() {
        return latDegrees;
    }

    public int getLatMinutes() {
        return latMinutes;
    }

    public int getLatSeconds() {
        return latSeconds;
    }

    public char getLatDirection() {
        return latDirection;
    }

    public int getLongDegrees() {
        return longDegrees;
    }

    public int getLongMinutes() {
        return longMinutes;
    }

    public int getLongSeconds() {
        return longSeconds;
    }

    public char getLongDirection() {
        return longDirection;
    }




}
