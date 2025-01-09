
package refugeoly;


public class ReceiveMoneyAction extends Action{
    
    private GiverEntity NGO_bank;
    private int ReceiveAmount;
    
    public ReceiveMoneyAction(GiverEntity NGO_bank, int ReceiveAmount) {
        this.NGO_bank = NGO_bank;
        this.ReceiveAmount = ReceiveAmount;
    }
    
    @Override
    public void act(Refugee refugee){
        refugee.receiveMoney(ReceiveAmount);
        try{
            this.NGO_bank.giveMoney(ReceiveAmount);
        }
        catch(NoMoneyException e){
            System.out.println("NGO_Bank has 0 money left.");
        }
    }
    
    @Override
    public int getSquare(Refugee refugee){
        return refugee.getSquare();
    }
    
}
