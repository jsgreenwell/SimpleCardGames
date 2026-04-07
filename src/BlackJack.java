//This class currently borrows a LOT of functions from War. I might make a generic "card" class later on to reduce bloat.


import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class BlackJack {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;





    //might not need to keep this
    public void printHand(ArrayList<String> hand) {
        for  (String card : hand) {
            IO.println(printCard(card));
        }
    }




    /**
     * Adds the first card in a deck to a hand, then removes that element from the deck. Can do this multiple times.
     * @param hand An array list of strings, will have a new card put into it.
     * @param deck An array list of strings, will have a card taken out of it.
     * @param times An integer, the number of times we want to draw.
     */
    public void draw(ArrayList<String> hand, Deck deck, int times){
        for (int i=1; i<=times; i++) {
            hand.add(deck.cards.get(0));
            deck.remove(0);
        }
    }


    /**
     *  Slightly modified draw function. Only draws once and prints some text explaining what is happening to the user.
     * @param hand An array of strings, will have a new card put into it.
     * @param deck An array of strings, will have a card removed from it.
     */
    public void hit(ArrayList<String> hand, Deck deck) {
        IO.readln("You have chosen to hit. Enter any key to continue.");
        draw(hand, deck, 1);
        IO.println("Here is your new hand.");
        printHand(hand);
    }


    /**
     * Counts up the cards in a hand and returns the total value.
     * @param hand An array of strings to be evaluated.
     * @return An integer containing the total value.
     */
    public int getHandValue(ArrayList<String> hand) {
        int totalValue = 0;
        for (String card : hand) {
            totalValue += getValue(card);
        }
        return totalValue;
    }


    /**
     * A simple choice evaluation function. Demands the user enter h or s, or q to quit.
     * @return A character representing the user's choice
     */
    public char hitOrStand() {
        while (true) {
            switch (IO.readln()){
                case "h", "H":
                    return 'h';
                case "s", "S":
                    return 's';
                case "q", "Q":
                    return 'q';
                default:
                    IO.println("Oops! Please enter H to hit or S to stand.");
            }
        }
    }


    /**
     * This function acts as the first half of the main game logic. It allows players to choose whether to hit, stand or
     * quit. It also evaluated the value of a player's hand to check if they have a bust and will automatically end the
     * game. If the player's hand is still less than 21 after the first hit, enters a loop that lets them hit as many
     * times as they like.
     * @param hand An array of strings, the player's current cards.
     * @param deck An array of strings, the cards not taken by the player or dealer.
     */
    public void checkBust(ArrayList<String> hand, ArrayList<String> deck) {
        IO.println("Would you like to hit or stand? Enter H to hit or S to stand. If you would like to quit, press Q.");
        char choice = hitOrStand();
        if (choice == 'q' ||  choice == 'Q') {
            keepPlaying = false;
        }
        if (choice == 'h' || choice == 'H') {
            boolean notSatisfied = true;
            while (notSatisfied) {
                hit(hand, deck);
                if  (getHandValue(hand) > 21) {
                    IO.readln("Your cards total more than 21, a bust! Better luck next time. Press any key to continue");
                    notSatisfied = false;
                    keepPlaying = false;
                } else {
                    IO.println("Your hand is still lower than or equal to 21. Would you like to hit again? Enter H to hit or S to stand.");
                    choice = hitOrStand();
                    if (choice == 'h' || choice == 'H') {
                        notSatisfied = true;
                    } else if (choice == 's' || choice == 'S') {
                        notSatisfied = false;
                    } else {
                        keepPlaying = false;
                    }
                }
            }

        }
    }

    /**
     * This function acts as the second half of the main game logic, occurring after checkBust has finished (if the player
     * did not decide to quit). Reveals the dealer's full hand and, if their hand is less than 17, loops them into drawing
     * cards until the value is at least 17. Checks if the dealer has a bust (an automatic win for the player), and if
     * they don't, compares the values of the player and dealer's hands, printing out a matching message.
     * @param pHand An array of strings, the player's current hand.
     * @param cHand An array of strings, the cpu's current hand.
     * @param deck An array of strings, the deck that the dealer can draw from.
     */
    public void dealerReveal(ArrayList<String> pHand, ArrayList<String> cHand, ArrayList<String> deck) {
        IO.println("You are satisfied with your hand. The dealer will now reveal his full hand:");
        printHand(cHand);
        if (getHandValue(cHand) < 17) {
            while (getHandValue(cHand) < 17) {
                draw(cHand, deck, 1);
            }
            IO.readln("""
                        The dealer's hand is worth less than 17. The dealer is obligated to draw until his hand is worth 17 or more
                        Enter any key to continue""");
            IO.println("The dealer has drawn his cards. Here is his new hand:");
            printHand(cHand);
        }
        if (getHandValue(cHand) > 21) {
            System.out.printf("With a value of %s, the dealer has a bust! You win!\n", getHandValue(cHand));
        } else if (getHandValue(pHand) > getHandValue(cHand)) {
            System.out.printf("With a hand value of %s, you have beaten the dealer! Congratulations!\n", getHandValue(pHand));
        } else if (getHandValue(pHand) < getHandValue(cHand)) {
            System.out.printf("With a hand value of %s, the dealer wins! Better luck next time!\n", getHandValue(cHand));
        } else {
            System.out.printf("With hands of %s, we have a draw! What are the odds?", getHandValue(pHand));
        }
    }




    /**
     * Sets up the black jack game by creating a deck and hands, then calls for the other two logical functions.
     */
    public void run() {
        IO.println("""
                Welcome to Black Jack! You'll be playing against a CPU. Aces have a value of 11, and face cards have values of 10.
                """);
        //creating a deck and subsequent hands for each player
        ArrayList<Deck> deck = new Deck();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> cpuHand = new ArrayList<>();

        IO.println("Here are your first two cards:");
        draw(playerHand, deck, 2);
        IO.println(printCard(playerHand.get(0)) + printCard(playerHand.get(1)));
        IO.readln("Enter any key to continue");

        IO.println("Here is the dealer's first card. He has two, but you cannot see the second.");
        draw(cpuHand, deck, 2);
        IO.println(printCard(cpuHand.get(0)) + printCard("??"));

        checkBust(playerHand, deck);

        if (keepPlaying) {
            dealerReveal(playerHand, cpuHand, deck);
        }
    }




    /**
     * Confirms whether the player wants to stop playing. Forces them to enter either C or Q.
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
