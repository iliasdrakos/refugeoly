
package refugeoly;


public class LifeVestAction extends Action {
    
    @Override
    public void act(Refugee refugee) {
        refugee.addVest();
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
