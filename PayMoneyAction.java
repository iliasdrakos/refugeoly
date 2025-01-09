package refugeoly;


public class PayMoneyAction extends Action {
    
    private ReceiverEntity mafia;
    private int PayAmount;
    
    public PayMoneyAction(ReceiverEntity mafia, int PayAmount) {
        this.mafia = mafia;
        this.PayAmount = PayAmount;
    }
    
    @Override
    public void act(Refugee refugee) {
        refugee.giveMoney(PayAmount);
        this.mafia.receiveMoney(PayAmount);
        if(this.getSquare(refugee) == 16) {
            RollDiceAction dice = new RollDiceAction();
            dice.act(refugee);
        }
        
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
    
   
    
}
