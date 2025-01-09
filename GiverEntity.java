package refugeoly;


public class GiverEntity implements MoneyGiver {
   
    private final String name = "NGO Bank";
    private int money = 10000;
    
    public int getMoney(){return this.money;}
    public void setMoney(int money){this.money = money;}
    
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public void giveMoney(int amount) throws NoMoneyException {
        if(this.money - amount <= 0) {
           throw new NoMoneyException("out of money");
        } else {
            this.money -= amount;
        }
    }
    
}
