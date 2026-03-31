//This class currently borrows a LOT of functions from War. I might make a generic "card" class later on to reduce bloat.

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class BlackJack {
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

    public void printHand(ArrayList<String> hand) {
        for  (String card : hand) {
            IO.println(printCard(card));
        }
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
                case 'J', 'Q', 'K':
                    return 10;
                case 'A':
                    return 11;
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



    public void draw(ArrayList<String> hand, ArrayList<String> deck, int times){
        for (int i=1; i<=times; i++) {
            hand.add(deck.get(0));
            deck.remove(0);
        }
    }



    public void hit(ArrayList<String> hand, ArrayList<String> deck) {
        IO.readln("You have chosen to hit. Enter c to draw your next card.");
        hand.add(deck.get(0));
        deck.remove(0);
        IO.println("Here is your new hand.");
        printHand(hand);
    }

    public int getHandValue(ArrayList<String> hand) {
        int totalValue = 0;
        for (String card : hand) {
            totalValue += getValue(card);
        }
        return totalValue;
    }


    public char hitOrStand() {
        while (true) {
            switch (IO.readln()){
                case "h", "H":
                    return 'h';
                case "s", "S":
                    return 's';
                default:
                    IO.println("Oops! Please enter H to hit or S to stand.");
            }
        }
    }


    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    public void run() {
        IO.println("""
                Welcome to Black Jack! You'll be playing against a CPU. Aces have a value of 11, and face cards have values of 10.
                """);
        //creating a deck and subsequent hands for each player
        ArrayList<String> deck = makeDeck();
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> cpuHand = new ArrayList<>();

        IO.println("Here are your first two cards:");
        draw(playerHand, deck, 2);
        IO.println(printCard(playerHand.get(0)) + printCard(playerHand.get(1)));
        IO.readln("Press c to continue");

        IO.println("Here is the dealer's first card. He has two, but you cannot see the second.");
        draw(cpuHand, deck, 2);
        IO.println(printCard(cpuHand.get(0)) + printCard("??"));
        IO.println("Would you like to hit or stand? Enter H to hit or S to stand.");

        if (hitOrStand() == 'h') {
            boolean notSatisfied = true;
            while (notSatisfied) {
                hit(playerHand, deck);
                if  (getHandValue(playerHand) > 21) {
                    IO.readln("Your cards total more than 21, a bust! Better luck next time. Press any key to continue");
                    notSatisfied = false;
                    keepPlaying = false;
                } else {
                    IO.println("Your hand is still lower than or equal to 21. Would you like to hit again? Enter H to hit or S to stand.");

                    if (hitOrStand() == 'h') {
                        notSatisfied = true;
                    } else {
                        notSatisfied = false;
                    }
                }
            }

        } else {
            IO.println("You are satisfied with your hand. The dealer will now reveal his full hand:");
            printHand(cpuHand);
            if (getHandValue(cpuHand) < 17) {
                while (getHandValue(cpuHand) < 17) {
                    draw(cpuHand, deck, 1);
                }
                IO.readln("""
                                 The dealer's hand is worth less than 17. The dealer is obligated to draw until his hand is worth 17 or more
                                 Press any key to continue""");
                IO.println("The dealer has drawn his cards. Here is his new hand:");
                printHand(cpuHand);
                if (getHandValue(cpuHand) > 21) {
                    System.out.printf("With a value of %s, the dealer has a bust! You win!", getHandValue(cpuHand));
                }
                if (getHandValue(playerHand) > getHandValue(cpuHand)) {
                    System.out.printf("With a hand value of %s, you have beaten the dealer! Congratulations!", getHandValue(playerHand));
                } else {
                    System.out.printf("With a hand value of %s, the dealer wins! Better luck next time!");
                }

            }

        }

    }




    /**
     * Confirms whether the player wants to stop playing. Why would you want to quit such an enthralling game?
     */
    public void checkExit() {
        IO.println("Would you like to start over? Enter C to continue or Q to quit.");
        String confirm = IO.readln();
        if (confirm.equals("q") || confirm.equals("Q")) {
            keepPlaying = false;
        } else if (confirm.equals("c") || confirm.equals("C")) {
            IO.println("Great! We'll play another game:\n");
            keepPlaying = true;
        }
    }
}
