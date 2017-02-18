package TripCo;


public class Coordinates {

    private int degrees;
    private int minutes;
    private int seconds;
    private char direction;


    public Coordinates(int d, int m, int s, char dir){
        this.degrees = d;
        this.minutes = m;
        this.seconds = s;
        this.direction = dir;
    }
    
    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public char getDirection() {
        return direction;
    }

}
