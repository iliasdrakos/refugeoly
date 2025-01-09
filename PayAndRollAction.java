
package refugeoly;


public class PayAndRollAction extends Action {
    
    private ReceiverEntity mafia;
    private int PayAmount;
    
    public PayAndRollAction(ReceiverEntity mafia, int PayAmount) {
        this.mafia = mafia;
        this.PayAmount = PayAmount;
    }
    
    @Override
    public void act(Refugee refugee) {
        
        refugee.giveMoney(PayAmount);
        this.mafia.receiveMoney(PayAmount);
        
        RollDiceAction dice = new RollDiceAction();
        dice.act(refugee);
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
}
