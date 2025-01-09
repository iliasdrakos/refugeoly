
package refugeoly;


public class WinGameAction extends Action {
    
    @Override
    public void act(Refugee refugee) {
        refugee.Winner();
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
    
}
