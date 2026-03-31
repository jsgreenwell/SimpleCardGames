import java.util.ArrayList;
import static java.util.Collections.shuffle;


public class War {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;


    /**
     * Creates a deck using numbers for rank and symbols for suit
     * @return an array list of size 52, the whole deck.
     */
    public ArrayList<String> makeDeck(){
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "K", "Q"};
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
     * Checks if a card has a double digit rank, then returns an ASCII version of the card using proper spacing
     * @param card A string with two characters, rank and suit.
     */
    public String printCard(String card) {
        if (card.length() == 2) {

            return String.format("""
                    -------
                    |  %s  |
                    |  %s  |
                    |_____|
                    """, card.charAt(0), card.charAt(1));
        } else {
            return String.format("""
                    -------
                    |  %s%s |
                    |  %s  |
                    |_____|
                    """, card.charAt(0), card.charAt(1), card.charAt(2));
        }
    }

    /**
     * Splits a deck in half to create a hand for a player or cpu. For a player, uses the front half. For a cpu, the back
     * @param deck Array of 52 cards, splits them up among the players
     * @param human Boolean, decides which half of the deck to use
     * @return returns the finished hand, an array list of 26 cards.
     */
    public ArrayList<String> makeHand(ArrayList<String> deck, boolean human){
        ArrayList<String> hand = new ArrayList<>();
        int dealer = 0;
        if (human){
            for (int i=0; i<=25; i++){
                hand.add(deck.get(i));
            }
        } else{
            for (int i=26; i<=51; i++){
                hand.add(deck.get(i));
            }
        }
        return hand;
    }

    /**
     * Gets an integer representation of the rank of a card
     * @param card 2-3 character string with a value and a rank
     * @return the determined value of the card after manipulating it into an integer.
     */
    public int getValue(String card){
        if (card.length() == 2){
            //checks first if the card has a letter rank and assigns the appropriate value
            switch (card.charAt(0)){
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    //If it had a numerical value, gets a string version of the 0th character, then converts it to an int
                    return Integer.parseInt(String.valueOf(card.charAt(0)));
            }
        } else{
            //if the rank is double digits, creates a double digit string and then converts
            return Integer.parseInt(String.valueOf(card.charAt(0)+card.charAt(1)));
        }
    }


    /**
     * Checks which card out of two is worth more, prints the results, and returns a boolean that the program will use
     * to move loser's the card to the other player's deck. Will be updated for ties.
     * @param pCard The player's card.
     * @param cCard The CPU's card.
     * @return Boolean, used to proceed.
     */
    public boolean winLose(String pCard, String cCard){
        System.out.printf("""
                    Your card:
                    %s
                    Opponent's card:
                    %s
                    """, printCard(pCard), printCard(cCard));
        if (getValue(pCard) > getValue(cCard)){
            IO.println("Your card wins! You have taken your opponent's card and put it at the bottom of your deck");
            return true;
        } else{
            IO.println("Your card loses! Your opponent has taken your card and put it at the bottom of their deck.");
            return false;
        }
    }


    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    public void run() {
        IO.println("""
                Welcome to War! You'll be playing against a CPU. during each turn and after you read this text, enter C
                 to continue. If you want to quit at any time, you can do so by entering Q.
                """);
        ArrayList<String> deck = makeDeck();
        ArrayList<String> playerHand = makeHand(deck, true);
        ArrayList<String> cpuHand = makeHand(deck, false);
        while (keepPlaying) {
            String confirm = IO.readln();
            if (confirm.equals("c") || confirm.equals("C")) {
            if (winLose(playerHand.get(0), cpuHand.get(0))) {
                playerHand.add(playerHand.get(0));
                playerHand.remove(0);
                playerHand.add(cpuHand.get(0));
                cpuHand.remove(0);
            } else {
                cpuHand.add(cpuHand.get(0));
                cpuHand.remove(0);
                cpuHand.add(playerHand.get(0));
                playerHand.remove(0);
            }
            } else if  (confirm.equals("q") || confirm.equals("Q")) {
                keepPlaying = false;
            } else{
                IO.println("Oops! Please enter 'c' to continue or 'q' to quit");
            }
        }
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
