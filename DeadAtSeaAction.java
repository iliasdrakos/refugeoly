package refugeoly;


public class DeadAtSeaAction extends Action {
    
    @Override
    public void act(Refugee refugee) {
        if(!refugee.haslifevest()) {
            refugee.dead();
        }
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
