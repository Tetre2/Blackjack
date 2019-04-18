import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Game game;
    private static Scanner sc = new Scanner(System.in);

    private static ArrayList<Suit> suits = new ArrayList<>();
    private static ArrayList<Value> values = new ArrayList<>();

    private static boolean addingSuits = true;
    private static boolean addingValues = true;


    public static void main(String[] args) {

        setup();
        game = new Game(values, suits);

    }

    private static void setup(){
        while (addingValues){
            System.out.print("Commands: defaults, done ");
            for (int i = 0; i < Value.values().length; i++) {
                System.out.print(", " + Value.values()[i]);
            }
            System.out.println();
            addValues();
        }
        while (addingSuits){
            System.out.println("Commands: defaults, done ");
            for (int i = 0; i < Suit.values().length; i++) {
                System.out.print(", " + Suit.values()[i]);
            }
            System.out.println();
            addSuits();
        }
        System.out.println("Game Staring!");
    }



    private static void addValues(){
        System.out.print("Current Values:");
        printCurrentValues();
        System.out.println();
        System.out.println("Add a Value:");

        String s = sc.nextLine();
        if(s.equals("done")){
            addingValues = false;
            System.out.println("Saving Values...");
            System.out.println();
            return;
        }else if(s.equals("defaults")){
            addDefaultValues();
            System.out.println("Adding Defaults...");
            return;
        }


        Value value = getValue(s);

        if(value != null){
            values.add(value);
        }
    }


    private static void addDefaultValues(){
        Value[] vals = Value.values();
        for (int i = 0; i < 13; i++) {
            values.add(vals[i]);
        }
    }

    private static Value getValue(String s){

        try{
            Value value = Value.valueOf(s);
            System.out.println("Added " + value);
            return value;
        }catch (Exception e){
            System.out.println("Not a Value");
        }

        return null;

    }



    private static void addSuits(){
        System.out.print("Current Suits:");
        printCurrentSuits();
        System.out.println();
        System.out.println("Add a Suit:");

        String s = sc.nextLine();

        if(s.equals("done")){
            addingSuits = false;
            System.out.println("Saving Suits...");
            System.out.println();
            return;
        }else if(s.equals("defaults")){
            addDefaultSuits();
            System.out.println("Adding Defaults...");
            return;
        }


        Suit suit = getSuit(s);

        if(suit != null){
            suits.add(suit);
        }
    }

    private static Suit getSuit(String s){
        try{
            Suit suit = Suit.valueOf(s);
            System.out.println("Added " + suit);
            return suit;
        }catch (Exception e){
            System.out.println("Not a Suit");
        }

        return null;
    }

    private static void addDefaultSuits(){
        Suit[] suits1 = Suit.values();
        for (int i = 0; i < 4; i++) {
            suits.add(suits1[i]);
        }
    }




    public static void playAgain(){
        game = new Game(values, suits);
    }

    private static void printCurrentValues(){
        for (Value v:values) {
            System.out.print(" " + v.toString());
        }
    }

    private static void printCurrentSuits(){
        for (Suit v:suits) {
            System.out.print(" " + v.toString());
        }
    }

}
