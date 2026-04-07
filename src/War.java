import java.util.ArrayList;
import static java.util.Collections.shuffle;


public class War {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;





    /**
     * Splits a deck in half to create a hand for a player or cpu. For a player, uses the front half. For a cpu, the back.
     *
     * @param deck  Array of 52 cards, splits them up among the players.
     * @param human Boolean, decides which half of the deck to use.
     * @return returns the finished hand, an array list of 26 cards.
     */
    public ArrayList<Card> makeHand(Deck deck, boolean human) {
        ArrayList<Card> hand = new ArrayList<>();
        if (human) {
            for (int i = 0; i <= 25; i++) {
                hand.add(deck.cards.get(i));
            }
        } else {
            for (int i = 26; i <= 51; i++) {
                hand.add(deck.cards.get(i));
            }
        }
        return hand;
    }



    /**
     * Checks which card out of two is worth more, prints the results, and returns a char that the program will use
     * to determine how to move on.
     *
     * @param pCard The player's card, a 2-3 character string.
     * @param cCard The CPU's card, a 2-3 character string.
     * @return Character, used by other functions to determine a win, loss or tie.
     */
    public char winLose(Card pCard, Card cCard) {
        System.out.printf("""
                Your card:
                %s
                Opponent's card:
                %s
                """, pCard.printCard(), cCard.printCard());
        if (pCard.getValue() > cCard.getValue()) {
            IO.println("Your card wins! You have taken your opponent's card and put it at the bottom of your deck");
            return 'w';
        } else if (pCard.getValue() < cCard.getValue()) {
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
    public ArrayList<ArrayList<Card>> tieBreak(ArrayList<Card> pHand, ArrayList<Card> cHand) {
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
        ArrayList<ArrayList<Card>> newHands = new ArrayList<>();
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
        Deck deck = new Deck();
        ArrayList<Card> playerHand = makeHand(deck, true);
        ArrayList<Card> cpuHand = makeHand(deck, false);

        while (keepPlaying) {
            String confirm = IO.readln();
            if (confirm.equals("c") || confirm.equals("C")) {
                //newHands is an array of two string arrays. The first is the player's, the second is the cpu's.
                ArrayList<ArrayList<Card>> newHands = (tieBreak(playerHand, cpuHand));
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

