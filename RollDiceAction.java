package refugeoly;

import java.util.*;

public class RollDiceAction extends Action {
    Random rand = new Random(); 
    private int dice;
    
    @Override
    public void act(Refugee refugee){
        Scanner input = new Scanner(System.in);
        
        this.dice = rand.nextInt(6) + 1;
    
        System.out.println("It's " + refugee.getName() + " turn." + "Roll dice (press any key) ? ");
        input.nextLine();
        
        System.out.println("Dice roll result: " + this.getDice());
        
        if(refugee.getSquare() + this.getDice() > 39) {
            refugee.moveTo(39 - (refugee.getSquare() + this.getDice() - 39));
        }else if(refugee.getSquare() == 22){
            refugee.moveTo(refugee.getSquare() - this.getDice());
        }else {
            refugee.moveTo(refugee.getSquare() + this.getDice());
        }
        
        
       
        
    }
        
    public int getDice(){
        return dice ;
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
  
}
