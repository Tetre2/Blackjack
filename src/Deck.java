import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();
    private static Random random = new Random();


    public Deck(ArrayList<Suit> suits, ArrayList<Value> values) {

        for (int value = 0; value < values.size(); value++) {
            for (int suit = 0; suit < suits.size(); suit++) {

                cards.add(new Card(suits.get(suit), values.get(value)));

            }
        }

    }




    private void debug(){
        for (Card c : cards) {
            System.out.println(c.toString());
        }
        System.out.println(cards.size());
    }


    public Card draw(){

        Card card = cards.get(random.nextInt(cards.size()));
        cards.remove(card);
        return card;

    }

    public int getCardsLeft(){
        return cards.size();
    }


}
