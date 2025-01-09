package refugeoly;


import java.util.*;
import java.io.*;

public class Game {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Refugee> refugees = new ArrayList<>();
        boolean game = true;
        int numberRefugees;

                
        System.out.println("WELCOME TO REFUGEOLY!");
        System.out.print("Enter number of players (1-4): ");
        do {
            numberRefugees = input.nextInt();
        
            input.nextLine();

            if(numberRefugees >= 1 && numberRefugees <= 4){
                for(int i = 0; i < numberRefugees; i++){
                    System.out.print("Enter name of player " + (i+1) + ": ");
                    String name = input.nextLine();
                    refugees.add(new Refugee(name,10000,0));
                }
            }
            else{
                System.out.println("Invalid input.Enter a number between 1-4.");
            }
            
        }while(numberRefugees < 1 || numberRefugees > 4);
        
        
        System.out.println("All players start with 10000$");
        System.out.println();
        System.out.println("GAME STARTS! ");
        
        
        Board board = new Board();
        
        System.out.println("You start on Square 0: " + board.getSquare(0));
        System.out.printf("\n");
        RollDiceAction dice = new RollDiceAction();
        while(game){
            
            for(int i = 0; i < refugees.size() ; i++) {
                
                System.out.print("do you want to save or load a game:");
                String Choice = input.nextLine();
                
                if(Choice.equalsIgnoreCase("save")){
                    Save(refugees,board,i);
                    i--;
                } else if(Choice.equalsIgnoreCase("Load")) {
                    PrintSavedGames();
                    Load(refugees,board);
                    i = -1;
                } else {
                        if(!refugees.get(i).isAlive() || refugees.get(i).getMoney() <= 0){
                            System.out.println(refugees.get(i).getName() + " is out of the game! :(");
                            System.out.print("----------------------------------------");
                            System.out.println("------------------------------");
                            refugees.remove(refugees.get(i));
                            
                        } else if(refugees.get(i).getStay() > 0) {
                            System.out.println(refugees.get(i).getName() +" you need to stay");   
                            refugees.get(i).decrease_stay();
                        } else {
                            do {
                                dice.act(refugees.get(i));
                                String squareInfo = board.getSquare(refugees.get(i).getSquare());
                                System.out.println("You landed on SQUARE " +  refugees.get(i).getSquare()  + ":");
                                System.out.println(squareInfo); 

                                if(board.getAction(refugees.get(i).getSquare()).getClass().equals(PayAndRollAction.class)) {
                                    board.doAction(refugees.get(i).getSquare(), refugees.get(i));
                                    
                                    squareInfo = board.getSquare(refugees.get(i).getSquare());
                                    System.out.println("You landed on SQUARE " +  refugees.get(i).getSquare()  + ":");
                                    System.out.println(squareInfo);
                                }

                            }while(board.getAction(refugees.get(i).getSquare()).getClass().equals(RollDiceAction.class));

                            board.doAction(refugees.get(i).getSquare(), refugees.get(i));
                            
                            System.out.printf("\n");
                            System.out.println(refugees.get(i).toString());
                            System.out.print("----------------------------------------");
                            System.out.println("------------------------------");
                            
                            if(refugees.get(i).getWinner()) {
                                System.out.println(refugees.get(i).getName() + " HAS WON THE GAME !!!");
                                game = false;
                                break;
                            } else if(refugees.isEmpty()){
                                System.out.println("GAME OVER. NO ONE WINS");
                                game = false;
                                break;
                            }
                    }
                }
                
            }
        }
        
    }
    
    public static void Save(ArrayList<Refugee> refugees,Board board, int StartPosition) {
        try {

            try (FileOutputStream fos = new FileOutputStream("SavedGames.txt",true)) {
                PrintWriter pw = new PrintWriter(fos);
                
                pw.println("\nSaved game " + (getSavedSize() + 1));
                
                int i = (StartPosition) % refugees.size();
                pw.printf("player%d name: %s money: %d square: %d Expenses: %d Stay: %d Alive: %b lifevest: %b\n", 
                            i + 1, 
                            refugees.get(i).getName(),
                            refugees.get(i).getMoney(),
                            refugees.get(i).getSquare(),
                            refugees.get(i).getExpenses(),
                            refugees.get(i).getStay(),
                            refugees.get(i).isAlive(),
                            refugees.get(i).haslifevest());
                
                i = (StartPosition + 1) % refugees.size();
                while(i != StartPosition) {
                    
                    pw.printf("player%d name: %s money: %d square: %d Expenses: %d Stay: %d Alive: %b lifevest: %b\n", 
                            i + 1, 
                            refugees.get(i).getName(),
                            refugees.get(i).getMoney(),
                            refugees.get(i).getSquare(),
                            refugees.get(i).getExpenses(),
                            refugees.get(i).getStay(),
                            refugees.get(i).isAlive(),
                            refugees.get(i).haslifevest());
                    
                    i = (i + 1) % refugees.size(); 
                }
                
                pw.printf("NGOBANK: %d\n", board.getBank().getMoney());
                pw.printf("Mafia: %d\n", board.getMafia().getMoney());
                
                pw.close();
            }

        } catch (FileNotFoundException e) {
            System.err.println("file not found!!");
        } catch (IOException e) {
            System.out.println("file done!!!");
        }
    }
    
    public static int getSavedSize() {
        
        int NumberOfSaved = 0;
        
        try{
            Scanner s = new Scanner(new File("SavedGames.txt"));
            
            String savedgame = s.nextLine();
            
            while(s.hasNextLine()) {
            
                if(savedgame.contains("Saved game")) {
                    NumberOfSaved++;
                }
            
                savedgame = s.nextLine();
            }
            s.close();
            
            
        } catch(FileNotFoundException e) {
            System.err.println("FILE NOT FOUND");
        }
        
        return NumberOfSaved;
        
    }
    
    public static void PrintSavedGames() {
         try{
            Scanner s = new Scanner(new File("SavedGames.txt"));
            
            String savedgame = s.nextLine();
            
            System.out.println("------------------------------");
            
            while(s.hasNextLine()) {
            
                if(savedgame.contains("Saved game")) {
                    System.out.println(savedgame);
                }
            
                savedgame = s.nextLine();
            }
            System.out.println("------------------------------");
            s.close();
            
            
        } catch(FileNotFoundException e) {
            System.err.println("FILE NOT FOUND");
        }
    }
    
    public static void Load(ArrayList<Refugee> refugees,Board board) {
        
        Scanner input = new Scanner(System.in);
        
        try{
            Scanner s = new Scanner(new File("SavedGames.txt"));
            
            System.out.print("tell me what game do you want to load:");
            String findgame = input.nextLine();
            boolean find = false;
            String string = s.nextLine();
            String[] saveditems;
            int player = 0;
            
            while(string.isBlank()) {string = s.nextLine();}
            
            while(true) {
                try {
                    if(string.equalsIgnoreCase(findgame)) {
                        find = true;
                        refugees.clear();
                        
                        string = s.nextLine();
                        while (!string.isBlank()) {
                            int i = 2;
                            saveditems = string.split(" ");
                            if(saveditems[0].contains("NGOBANK")) {
                                int bankMoney = Integer.parseInt(saveditems[1]);
                                board.getBank().setMoney(bankMoney);
                            } else if(saveditems[0].contains("Mafia")) {
                                int mafiaMoney = Integer.parseInt(saveditems[1]);
                                board.getMafia().setMoney(mafiaMoney);
                            } else {
                                String name = saveditems[i];
                                int money = Integer.parseInt(saveditems[i += 2]);
                                int square = Integer.parseInt(saveditems[i += 2]);
                                int expenses = Integer.parseInt(saveditems[i += 2]);
                                int stay = Integer.parseInt(saveditems[i += 2]);
                                boolean alive = Boolean.parseBoolean(saveditems[i += 2]);
                                boolean lifevest = Boolean.parseBoolean(saveditems[i += 2]);
                                
                                refugees.add(player, new Refugee(name, money, square, expenses, stay, alive, lifevest));
                                player++;
                            }
                            string = s.nextLine();
                        }
               
                    } else {
                        string = s.nextLine();
                    }

                }catch(NoSuchElementException e) {
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("there was an error reading the file!!");
                    break;
                }

                
            }
            
            if(!find) {
                System.out.println("not saved game");
            } else {
                System.out.println("found saved game");
            }
            
            s.close();
            
            
        } catch(FileNotFoundException e) {
            System.err.println("FILE NOT FOUND");
        }
    }



    
}
