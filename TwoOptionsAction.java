package refugeoly;

import java.util.Scanner;

public class TwoOptionsAction extends Action {
    
    
    private ReceiverEntity mafia;
    private int PayAmount;
    
    public TwoOptionsAction(ReceiverEntity mafia, int payAmount) {
        this.mafia = mafia;
        this.PayAmount = payAmount;
    }
    
    
    @Override
    public void act(Refugee refugee) {
        
        RollDiceAction dice = new RollDiceAction();
        
        refugee.giveMoney(1000);
        this.mafia.receiveMoney(1000);
        
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("you want option A or option B:");
        String option = input.nextLine();
        
        
      
        if(option.equalsIgnoreCase("a")) {
            refugee.giveMoney(PayAmount);
            this.mafia.receiveMoney(PayAmount);
            dice.act(refugee);
        } else {
            refugee.setStay(2);
        }
        
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
