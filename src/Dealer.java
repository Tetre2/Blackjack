import java.util.ArrayList;

public class Dealer {

    private String name = "Dealer";
    private ArrayList<Card> cards;

    private int points = 0;

    public Dealer() {
        cards = new ArrayList<>();

    }

    public void drawCard(Deck deck){
        Card card = deck.draw();
        System.out.println("Dealer draw a " + card.toString());
        cards.add(card);
        points = Game.checkValueOfCards(cards);
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getPoints() {
        return points;
    }
}
