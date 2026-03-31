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



    public void draw(ArrayList<String> hand, ArrayList<String> deck, int times){
        for (int i=1; i<=times; i++) {
            hand.add(deck.get(0));
            deck.remove(0);
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
        String choice = IO.readln("Would you like to hit or stand? Enter H to hit or S to stand.");

        if (choice.equals("h")){
            boolean notSatisfied = true;
            while (notSatisfied) {
                IO.readln("You have chosen to hit. Enter c to draw your next card.");
                playerHand.add(deck.get(0));
                deck.remove(0);

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
