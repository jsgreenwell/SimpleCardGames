import java.util.ArrayList;
import static java.util.Collections.shuffle;


public class War {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;


    /**
     * Creates a deck using numbers for rank and symbols for suit. Randomizes order using shuffle.
     *
     * @return an array list of size 52, the whole deck after being shuffled.
     */
    public ArrayList<String> makeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "K", "Q"};
        String[] suits = {"♡", "♤", "☘", "⟡"};
        ArrayList<String> deck = new ArrayList<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                deck.add(rank + suit);
            }
        }
        shuffle(deck);
        return deck;
    }

    /**
     * Checks if a card has a double digit rank, then returns an ASCII version of the card using proper spacing
     *
     * @param card A string with two characters, rank and suit.
     * @return A string constructed to look like a card.
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
     * Splits a deck in half to create a hand for a player or cpu. For a player, uses the front half. For a cpu, the back.
     *
     * @param deck  Array of 52 cards, splits them up among the players.
     * @param human Boolean, decides which half of the deck to use.
     * @return returns the finished hand, an array list of 26 cards.
     */
    public ArrayList<String> makeHand(ArrayList<String> deck, boolean human) {
        ArrayList<String> hand = new ArrayList<>();
        if (human) {
            for (int i = 0; i <= 25; i++) {
                hand.add(deck.get(i));
            }
        } else {
            for (int i = 26; i <= 51; i++) {
                hand.add(deck.get(i));
            }
        }
        return hand;
    }

    /**
     * Gets an integer representation of the rank of a card
     *
     * @param card 2-3 character string with a value and a rank
     * @return the determined value of the card after manipulating it into an integer.
     */
    public int getValue(String card) {
        if (card.length() == 2) {
            //checks first if the card has a letter rank and assigns the appropriate value
            switch (card.charAt(0)) {
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
        } else {
            //If the rank is double digits, fetches the two string values and parses them into an int.
            //Have to use string.valueof for concatenation. If we don't, the chars get added wrong.
            return Integer.parseInt(String.valueOf(card.charAt(0)) + String.valueOf(card.charAt(1)));
        }
    }


    /**
     * Checks which card out of two is worth more, prints the results, and returns a char that the program will use
     * to determine how to move on.
     *
     * @param pCard The player's card, a 2-3 character string.
     * @param cCard The CPU's card, a 2-3 character string.
     * @return Character, used by other functions to determine a win, loss or tie.
     */
    public char winLose(String pCard, String cCard) {
        System.out.printf("""
                Your card:
                %s
                Opponent's card:
                %s
                """, printCard(pCard), printCard(cCard));
        if (getValue(pCard) > getValue(cCard)) {
            IO.println("Your card wins! You have taken your opponent's card and put it at the bottom of your deck");
            return 'w';
        } else if (getValue(pCard) < getValue(cCard)) {
            IO.println("Your card loses! Your opponent has taken your card and put it at the bottom of their deck.");
            return 'l';
        } else {
            return 't';
        }
    }

    /**
     * Acts as the main function that checks whether the player or the cpu wins a match. If there is a tie, informs
     * the player and loops until the tie is broken
     *
     * @param pHand an array list of player cards
     * @param cHand an array list of cpu cards
     * @return an array containing two string arrays. The first is used to make the player's new hand, the second is
     * used for the cpu's new hand
     */
    public ArrayList<ArrayList<String>> tieBreak(ArrayList<String> pHand, ArrayList<String> cHand) {
        int attempt = 0;
        boolean tied = true;
        while (tied && keepPlaying) {
            //uses winLose function to check for a player win, loss or a tie
            char winner = winLose(pHand.get(attempt), cHand.get(attempt));
            //for win and loss, the inner for loops ensure all cards from any previous ties get put at the bottom of the deck.
            if (winner == 'w') {
                for (int i = 0; i <= attempt; i++) {
                    pHand.add(pHand.get(0));
                    pHand.remove(0);
                    pHand.add(cHand.get(0));
                    cHand.remove(0);
                }
                tied = false;
            } else if (winner == 'l') {
                for (int i = 0; i <= attempt; i++) {
                    cHand.add(cHand.get(0));
                    cHand.remove(0);
                    cHand.add(pHand.get(0));
                    pHand.remove(0);
                }
                tied = false;
                /*In the case of a tie, loops over again. Adds one to attempt, which lets the program know what cards
                to check (for example, if attempt=2, then check the third card)*/
            } else {
                IO.println("""
                        A tie? This means War! Let's check you and your opponents' next cards and see who wins!
                        press C to continue.
                        """);
                String confirm = IO.readln();
                //User can still choose to quit during a tie
                if (confirm.equals("q") || confirm.equals("Q")) {
                   keepPlaying = false;
                } else {
                    IO.println("Oops! Please enter 'c' to continue or 'q' to quit");
                }
                attempt += 1;
            }
        }
        ArrayList<ArrayList<String>> newHands = new ArrayList<>();
        newHands.add(pHand);
        newHands.add(cHand);
        return newHands;
    }


    /**
     * Runs the main game loop. First, creates a deck, a player hand and a cpu hand. Then, loops through the war game.
     * User presses c to continue each turn, and can quit any time with q. The while loops exits when either hand is
     * empty.
     */
    public void run() {
        IO.println("""
                Welcome to War! You'll be playing against a CPU. during each turn and after you read this text, enter C
                 to continue. If you want to quit at any time, you can do so by entering Q.
                """);
        //creating a deck and subsequent hands for each player
        ArrayList<String> deck = makeDeck();
        ArrayList<String> playerHand = makeHand(deck, true);
        ArrayList<String> cpuHand = makeHand(deck, false);

        while (keepPlaying) {
            String confirm = IO.readln();
            if (confirm.equals("c") || confirm.equals("C")) {
                //newHands is an array of two string arrays. The first is the player's, the second is the cpu's.
                ArrayList<ArrayList<String>> newHands = (tieBreak(playerHand, cpuHand));
                playerHand = newHands.get(0);
                cpuHand = newHands.get(1);
                System.out.printf("Here are the hand sizes after that round:\nYou: %s\nOpponent: %s", playerHand.size(), cpuHand.size());

            } else if (confirm.equals("q") || confirm.equals("Q")) {
                keepPlaying = false;
            } else {
                IO.println("Oops! Please enter 'c' to continue or 'q' to quit");
            }
            //If either player runs out of cards, lets the user know and has them exit
            if (playerHand.isEmpty()) {
                IO.println("You've run out of cards! Better luck next time! Press any key to exit.");
                IO.readln();
                keepPlaying = false;
            } else if (cpuHand.isEmpty()) {
                IO.println("Your opponent is out of cards, you win! here's your final hand, and you can press any key to continue.");
                IO.println(playerHand);
                IO.readln();
                keepPlaying = false;
            }
        }
    }




    /**
     * Confirms whether the player wants to stop playing. Why would you want to quit such an enthralling game?
     */
    public void checkExit() {
        IO.println("Would you like to start over? Enter C to continue or Q to quit.");
        String confirm = IO.readln();
        while (!confirm.equals("c") && !confirm.equals("Q") && !confirm.equals("C") && !confirm.equals("q")) {
            confirm = IO.readln("Whoops! Press C to play another game or Q to quit.");
        }
        if (confirm.equals("q") || confirm.equals("Q")) {
            keepPlaying = false;
        } else {
            IO.println("Great! We'll play another game:\n");
            keepPlaying = true;
        }
    }
}

