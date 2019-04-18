import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private Player[] players = {new Player("Martin"), new Player("Mattias")};
    private ArrayList<Player> activePlayers;

    private Dealer dealer;

    private Scanner sc = new Scanner(System.in);

    private Player currentPlayer;

    private Deck deck;

    private ArrayList<Value> values;
    private ArrayList<Suit> suits;


    public Game(ArrayList<Value> values, ArrayList<Suit> suits) {
        this.values = values;
        this.suits = suits;

        deck = new Deck(suits, values);
        activePlayers = new ArrayList<>(Arrays.asList(players));
        dealer = new Dealer();

        currentPlayer = activePlayers.get(0);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        dealer.drawCard(deck);

        for (int i = 0; i < activePlayers.size(); i++) {
            System.out.println();
            System.out.println(activePlayers.get(i).getName() + ": ");
            activePlayers.get(i).drawCard(deck);
            activePlayers.get(i).drawCard(deck);
        }

        while (true){

            preformAction();
            setNextPlayer();
            if(currentPlayer == null){
                break;
            }
        }

        while ((dealer.getPoints() < 17)){
            dealer.drawCard(deck); //Less than 17, dealer can draw!
            System.out.println("Dealer has now " + dealer.getPoints() + " points");
        }
        System.out.println();

        for (int i = 0; i < players.length; i++) {
            checkWin(players[i]);
            System.out.println(" With " + players[i].getPoints() + " points against the dealers " + dealer.getPoints());
        }

        System.out.println("Play again?");
        String s = sc.nextLine();
        s.toLowerCase();
        if(s.equals("yes")){
            Main.playAgain();
        }



    }

    private void checkWin(Player player){
        int win = 0;
        int dealerPoints = dealer.getPoints();
        int playerPoints = player.getPoints();


        if( dealerPoints > 21){

            if(playerPoints > 21){
                win = 0;
            }else {
                win = 1;
            }

        }else if( playerPoints > 21){
            win = -1;
        }else {

            if(playerPoints > dealerPoints){
                win = 1;
            }else if(dealerPoints > playerPoints){
                win = -1;
            }//else draw // == o
        }


        if(win == 1){
            System.out.print(player.getName() + " Won against the dealer");
        }else if(win == -1){
            System.out.print(player.getName() + " Lost against the dealer");
        }else {
            System.out.print(player.getName() + " Drawed with the dealer");
        }
    }

    private void preformAction(){
        System.out.println();
        System.out.println("Current Player is: " + currentPlayer.getName());
        System.out.println("Your current points are: " + currentPlayer.getPoints());

        System.out.println("There are " + deck.getCardsLeft() + " cards left.");

        System.out.println();
        System.out.println("You have: ");
        printCards(currentPlayer.getCards());

        System.out.println();
        System.out.println("Dealer has: ");
        printCards(dealer.getCards());

        String s = sc.nextLine();

        switch (s){
            case "draw":
                currentPlayer.drawCard(deck);
                System.out.println("Your points are now: " + currentPlayer.getPoints() + "\n");
                checkBust();
                break;

            case "fold":        //jag vill inte ha fler kort
                currentPlayer.setFolded(true);
                break;

            case "double":      //jag vill ha ett kort sedan fold
                currentPlayer.drawCard(deck);       //behöver inte checkbust eftersom vi sedan ändå tar bort spelaren ur de aktiva
                currentPlayer.setFolded(true);
                break;

            case "split":      //dela upp två lika kort //TODO implementera senare??
                System.out.println("not implemented");
                preformAction();
                break;

            default: preformAction();break;
        }

    }

    private void checkBust(){

        if(currentPlayer.getPoints() >= 21){
            currentPlayer.setFolded(true);
        }

    }

    private void printCards(ArrayList<Card> cards){
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i).toString());
        }
    }

    private void setNextPlayer(){

            int index = activePlayers.indexOf(currentPlayer);

            //System.out.println("Debugg: " + activePlayers.size() + " | " + index + " | " + ((index + 1) % activePlayers.size()));

            if (currentPlayer.isFolded()) {

                activePlayers.remove(currentPlayer);
            }

            if(activePlayers.size() == 0){
                currentPlayer = null;
            }else {
                index = (index + 1) % activePlayers.size();
                currentPlayer = activePlayers.get(index);
            }


    }

    public static int checkValueOfCards(ArrayList<Card> cards){

        boolean haveAce = false;
        int sum = 0;

        for (Card c: cards) {

            int i = checkValue(c.getValue());

            if(i != -10){
                sum += i;
            }else {
                haveAce = true;
                sum += 11;
            }
        }

        if(sum > 21 && haveAce){
            sum -= 10;
        }

        return sum;
    }

    private static int checkValue(Value value){     //Om du lägger till ett nytt kor glöm inte att även lägga till det här!!!

        switch (value){
            case two: return 2;
            case three: return 3;
            case four: return 4;
            case five: return 5;
            case six: return 6;
            case seven: return 7;
            case eight: return 8;
            case nine: return 9;
            case ten: return 10;
            case jack: return 10;
            case queen: return 10;
            case king: return 10;

            //dlc saker
            case president: return 10;
            case priest: return 10;
            case unAce: return -1;
            case unTwo: return -2;
            case unThree: return -3;
            case unFour: return -4;
            case unFive: return -5;
            case unSix: return -6;
            case unSeven: return -7;
            case unEight: return -8;
            case unNine: return -9;

            default: return -10;
        }
    }

}
