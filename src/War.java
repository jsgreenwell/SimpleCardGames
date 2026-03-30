import java.util.ArrayList;
import static java.util.Collections.shuffle;


public class War {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;

    public ArrayList<String> makeDeck(){
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "K", "Q"};
        String[] suits = {"♡", "♤", "☘", "⟡"};
        ArrayList<String> deck = new ArrayList<>();
        for ( String rank : ranks){
            for ( String suit : suits){
                deck.add(rank+suit);
            }
        }
        shuffle(deck);
        return deck;
    }

    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    public void run() {
        System.out.println("Card Game - TBD");
        ArrayList<String> newDeck = makeDeck();
        IO.print(newDeck);
    }

    /**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     * For now just changes flog so this can exit
     */
    public void checkExit() {
        keepPlaying = false;
    }
}
