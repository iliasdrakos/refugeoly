package refugeoly;


public class GoToAction extends Action {
    
    private int square;
    
    public GoToAction(int square) {
        this.square = square;
    }
        
    
    @Override
    public void act(Refugee refugee){
        refugee.moveTo(square);
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
