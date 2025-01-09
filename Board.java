package refugeoly;

import java.util.*;
import java.io.*;


public class Board {
    
    private ArrayList<String> squares = new ArrayList<>();
    private ArrayList<Action> Actions = new ArrayList<>();
    
    private GiverEntity NGO_bank = new GiverEntity();
    private ReceiverEntity mafia = new ReceiverEntity();
    
    
    public Board (){
        
        Scanner file = null;
        try{
            file = new Scanner(new FileInputStream("refugeoly-squares.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Error reading the file: " + e.getMessage());
            System.exit(0);
        }
        
        String line = null;
        while(file.hasNextLine()){
            line = file.nextLine();
            this.addSquare(line);
            
        }
        file.close();
        
        for(int square = 0; square < 39; square++){
            this.addAction(square, NGO_bank, mafia);
        }
    }
    
    
    
    
    private void addSquare(String line){
        squares.add(line);
    }
    
    public String getSquare(int board_square){
        for(int i = 0; i < squares.size(); i+= 4){
            int squareNo = Integer.parseInt(squares.get(i).trim());
            if(squareNo == board_square){
                return squares.get(i + 1).trim();
            }
        }
        return null;
    }
    
    private void addAction(int board_square, GiverEntity NGO_bank, ReceiverEntity mafia) {
        
        if(board_square == 7) {
            this.Actions.add(new LifeVestAction());
            return;
            
        } else if(board_square == 10) {
            this.Actions.add(new DeadAtSeaAction());
            return;
        } else if(board_square == 8 || board_square == 11 || board_square == 14 || board_square == 19 || board_square == 24 || board_square == 27 || board_square == 32 || board_square == 34) {
            this.Actions.add(new StayAction(1));
            return;
        } else if(board_square == 2 || board_square == 12 || board_square == 17 || board_square == 22 || board_square == 28 || board_square == 31 || board_square == 33) {
            this.Actions.add(new RollDiceAction());
            return;
        }
        
        switch(board_square) {
            
            case 0:
                this.Actions.add(new StartAction());
            return;
            case 1:
                
                this.Actions.add(new PayMoneyAction(mafia,100));
            return;
            
            case 3:
                this.Actions.add(new PayMoneyAction(mafia,300));
            return;
            case 4:
            case 5:
                this.Actions.add(new GoToAction(0));
            return;
            
            case 6:
            case 37:
                this.Actions.add(new PayMoneyAction(mafia,1000));
            return;
            
            case 9:
                this.Actions.add(new PayAndRollAction(mafia,3000));
            return;
            
            case 13:
                this.Actions.add(new PayMoneyAction(mafia,200));
            return;
            
            case 15:
                this.Actions.add(new GoToAction(5));
            return;
            
            case 16:
                this.Actions.add(new PayAndRollAction(mafia,500));
            return;
            
            case 18:
                this.Actions.add(new GoToAction(22));
            return;
            
            case 20:
                this.Actions.add(new ReceiveMoneyAction(NGO_bank,1000));
            return;
            
            case 21:
                this.Actions.add(new PayMoneyAction(mafia,1500));
            return;
            
            case 23:
                this.Actions.add(new GoToAction(29));
            return;
            
            case 25:
                this.Actions.add(new GoToAction(15));
            return;
            
            case 26:
                this.Actions.add(new TwoOptionsAction(mafia,1500));
            return;
            case 29:
                this.Actions.add(new GoToAction(31));
            return;
            
            case 30:
                this.Actions.add(new GoToAction(24));
            return;
            
            case 31:
                this.Actions.add(new PayMoneyAction(mafia,800));
            return;
            
            case 33:
                this.Actions.add(new GoToAction(17));
            return;
            
            case 35:
                this.Actions.add(new GoToAction(25));
            return;
            
            case 38:
                this.Actions.add(new GoToAction(0));
            return;
            
            case 36:
            case 39:
                this.Actions.add(new WinGameAction());
            
            
        }
    }
    
    public Action getAction(int board_square) {
        return this.Actions.get(board_square);
    }
        
    
    public void doAction(int board_square, Refugee refugee){
        this.Actions.get(board_square).act(refugee);
    }
    
    
    public GiverEntity getBank(){ return this.NGO_bank; }
    public ReceiverEntity getMafia(){ return this.mafia;}
    
    
    

    
}
