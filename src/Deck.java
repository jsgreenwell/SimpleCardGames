import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Deck {

    ArrayList<Card> cards = new ArrayList<>();


    public Deck() {
        for (String rank : Card.ranks) {
            for (String suit : Card.suits) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }
        shuffle(cards);
    }


    public String toString() {
        String fullDeck = "";
        for (Card card : this.cards){
            fullDeck = fullDeck + ' ' + card.toString();
        }
        return fullDeck;
    }



}
