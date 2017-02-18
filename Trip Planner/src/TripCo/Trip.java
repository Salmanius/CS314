public class Trip {

    private ArrayList<Leg> Legs;

    public Trip(){
        Legs = ArrayList<Leg>():
    }

    public void addLeg(Leg L){
        Leg.add(L);
    }

    public Leg getLeg(int index){
        return Leg.get(i);
    }

    //We should never need this, but I added incase needed for debugging.
    public void deleteLeg(int index){
        Leg.remove(index);
    }

}