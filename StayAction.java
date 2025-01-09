
package refugeoly;

public class StayAction extends Action {
    
    private int stay;
    
    public StayAction(int stay) {
        this.stay = stay;
    }
    
    
    @Override
    public void act(Refugee refugee) {
        refugee.setStay(stay);
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
