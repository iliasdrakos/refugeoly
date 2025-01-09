
package refugeoly;


public class ReceiverEntity implements MoneyReceiver {
    private final String name = "Mafia Bank";
    private int money;
    
    public void setMoney(int money){this.money = money;}
    
    public String getName() {
        return this.name;
    }
    
    public int getMoney() {
        return this.money;
    }
    
    @Override
    public void receiveMoney(int amount) {this.money += amount;}
    
    
    
}
