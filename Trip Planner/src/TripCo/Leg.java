public class Leg {

    private int sequence;
    private String start;
    private String finish;
    private float milage;

    public Leg(int seq, String start, String finish, float m){
        this.sequence = seq;
        this.start = start;
        this.finish = finish;
        this.milage = m;
    }

    public void setSequence(int s){
        this.sequence = s;
    }

    public int getSequence() {
        return sequence;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public float getMilage() {
        return milage;
    }
}