import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> cards;

    private boolean folded = false;
    private int points = 0;


    public Player(String name) {

        this.name = name;
        cards = new ArrayList<>();

    }


    public void drawCard(Deck deck){
        Card card = deck.draw();
        System.out.println("You draw a " + card.toString());
        cards.add(card);

        points = Game.checkValueOfCards(cards);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
