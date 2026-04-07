import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Card {
    public static final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "K", "Q"};
    public static final String[] suits = {"♡", "♤", "☘", "⟡"};

    private String rank;
    private String suit  ;

    public Card (String rank, String suit) {
    this.rank = rank;
    this.suit = suit;
    }


    public String toString() {
        return this.rank + this.suit;
    }


    public String printCard() {
        if (this.rank.length() == 1) {

            return String.format("""
                    -------
                    |  %s  |
                    |  %s  |
                    |_____|
                    """, this.rank, this.suit);
        } else {
            return String.format("""
                    -------
                    |  %s%s |
                    |  %s  |
                    |_____|
                    """, this.rank.charAt(0), this.rank.charAt(1), this.suit);
        }
    }


    public int getValue() {
        if (this.rank.length() == 1) {
            //checks first if the card has a letter rank and assigns the appropriate value
            switch (this.rank.charAt(0)) {
                case 'J', 'Q', 'K':
                    return 10;
                case 'A':
                    return 11;
                default:
                    //If it had a numerical value, gets a string version of the 0th character, then converts it to an int
                    return Integer.parseInt(String.valueOf(this.rank.charAt(0)));
            }
        } else {
            //If the rank is double digits, fetches the two string values and parses them into an int.
            //Have to use string.valueof for concatenation. If we don't, the chars get added wrong.
            return Integer.parseInt(String.valueOf(this.rank.charAt(0)) + String.valueOf(this.rank.charAt(1)));
        }
    }





}
