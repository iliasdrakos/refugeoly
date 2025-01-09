package refugeoly;


public class Refugee  implements MoneyGiver,MoneyReceiver {
    
    private String name;
    private int money;
    private int square;
    private int expenses;
    private boolean life = true;
    private boolean lifevest = false;
    private boolean win = false;
    private int stay;
    
    public Refugee(String name, int money, int square) {
        this.name = name;
        this.money = money;
        this.square = square;
    }
    
    public Refugee(String name, int money, int square, int expenses ,int stay, boolean alive, boolean lifevest) {
        this.name = name;
        this.money = money;
        this.square = square;
        this.expenses = expenses;
        this.stay = stay;
        this.life = alive;
        this.lifevest = lifevest;
    }
    
    //geters
    public String getName() {return this.name;}
    
    public int getMoney() {return this.money;}
    
    public int getSquare() {return this.square;}
    
    public int getExpenses() {return this.expenses;}
    
    public int getStay() {return this.stay;}
    
    public boolean isAlive(){return this.life;}
    
    public boolean haslifevest() {return this.lifevest;}
    
    public boolean getWinner() {return this.win;}
    
    //setters
    @Override
    public void giveMoney(int amount ) {
        this.money -= amount;
        this.expenses += amount;
        
    }
    
    @Override
    public void receiveMoney(int amount) {
        this.money += amount;
    }
    
    public void moveTo(int Square) {
        this.square = Square;
    }
    
    @Override
    public String toString() { return this.getName() + " has " + this.getMoney() + "$ and they are in SQUARE: " + this.getSquare();}
    
    public void dead() {
        this.life = false;
    }
    
    public void addVest() {
        this.lifevest = true;
    }
    
    public void setStay(int stay) {
        this.stay = stay;
    }
    
    public void decrease_stay() {
        this.stay--;
    }
    
    public void Winner() {
        this.win = true;
    }
    
    
}
