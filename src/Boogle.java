import java.util.*;
public class Boogle {
    // Boolean variable which is true until player wants to quit
    public  boolean keepPlaying = true;
    //initializes scanner variable
    Scanner scan = new Scanner(System.in);
    /**
     * Main game function for Boggle.
     */
    public void run() {
        Character[][] board = rollDice();
        printBoard(board);
        List<String> acceptedWords = new ArrayList<>();

        //Runs gameloop, runs continuously until an empty word is entered.
        System.out.println("Enter every every word you can find! Press Enter to calculate points.");
        while (true) {
            String curWord = scan.nextLine().toUpperCase();
            if (curWord.isEmpty()) {
                break;
            }
            if (verifyWord(curWord,acceptedWords, board)){
                acceptedWords.add(curWord);
                System.out.println("Word accepted!");
            }
            else{
                System.out.println("Word invalid, try again!");
            }
        }
        calcPoints(acceptedWords);

    }
    /**
     * Rolls the 16 dice needed for Boggle and places into a board.
     * @return board
     */
    public Character[][] rollDice() {
        Character[][] dice = {
                {'R','I','F','O','B','X'}, {'I','F','E','H','E','Y'}, {'D','E','N','O','W','S'}, {'U','T','O','K','N','D'},
                {'H','M','S','R','A','O'}, {'L','U','P','E','T','S'}, {'A','C','I','T','O','A'}, {'Y','L','G','K','U','E'},
                {'Q','B','M','J','O','A'}, {'E','H','I','S','P','N'}, {'V','E','T','I','G','N'}, {'B','A','L','I','Y','T'},
                {'E','Z','A','V','N','D'}, {'R','A','L','E','S','C'}, {'U','W','I','L','R','G'}, {'P','A','C','E','M','D'}
        };
        Random r = new Random();
        List<Character[]> diceList = new ArrayList<>(Arrays.asList(dice));
        Collections.shuffle(diceList);
        Character[][] board = new Character[4][4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Character[] die = diceList.get(index);
                board[i][j] = die[r.nextInt(6)];
                index++;
            }
        }
        return board;
    }

    /**
     * Prints the Boggle board in a 4x4 grid to console.
     * @param board
     */
    public void printBoard(Character[][] board) {
        for (int i = 0; i < 4; i++) {
            System.out.println("━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("┃");
            for (int j = 0; j < 4; j++) {
                System.out.printf(" %-2s ", board[i][j]);
                if (j != 3) {
                    System.out.print("┃");
                }

            }
            System.out.print("┃");
            System.out.println();
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Verifies a given word by checking if it has already been used,
     * if it contains only letters on the board,
     * and if it contains a vowel.
     * @param word the word to verify
     * @param acceptedWords all previous accepted words
     * @param board the current Boggle board in list format
     * @return Boolean value depending on if word is valid
     */
    public Boolean verifyWord(String word,List<String> acceptedWords, Character[][] board) {
        //check if word has already been used
        if (acceptedWords.contains(word)) {
            return false;
        }
        List<Character> vowels = List.of('A', 'E', 'I', 'O', 'U', 'Y');

        //Flattens the given board into an ArrayList in order to search and remove values
        List<Character> boardList = new ArrayList<>();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                boardList.add(board[i][j]);
            }
        }

        boolean hasVowel = false;
        for (int i = 0; i < word.length(); i++) {
            Character letter = word.charAt(i);

            //check for all letters in list to board, removes as it searches to avoid duplicates
            if(boardList.contains(letter)){
                boardList.remove(letter);
            }
            else{
                return false;
            }
            //checks for vowels
            if (vowels.contains(letter)) {
                hasVowel = true;
            }
        }
        //if gotten this far, returns if vowel was found
        return hasVowel;
    }

    /**
     * Calcuates points for a standard game of Boggle given the list of accepted words.
     * @param wordList
     */
    public void calcPoints(List<String> wordList) {
        int points = 0;
        for (String word : wordList){
            switch (word.length()){
                case 3: case 4:
                    points += 1;
                    System.out.println(word + ": 1 Point");
                    break;
                case 5:
                    points += 2;
                    System.out.println(word + ": 2 Points");
                    break;
                case 6:
                    points += 3;
                    System.out.println(word + ": 3 Points");
                    break;
                case 7:
                    points += 5;
                    System.out.println(word + ": 5 Points");
                    break;
                case 8:
                    points += 11;
                    System.out.println(word + ": 11 Points");
                    break;
            }
        }
        System.out.println("You earned a total of " + points + " points!");
    }


/**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     * For now just changes flog so this can exit
     */
    public void checkExit() {
        System.out.println("Would you like to play again? [y/n]");
        String choice = scan.nextLine().toLowerCase().trim();
        while(!choice.equals("n") && !choice.equals("y")){
            System.out.println("Please enter 'y' to continue playing or 'n' to return to main menu.");
            choice = scan.nextLine().toLowerCase().trim();
        }
        if(choice.equals("y")){
            System.out.println("You have decided to play again, have fun!");
            keepPlaying = true;
        }
        else{
            System.out.println("Thank you for playing Boggle! Returning to the main menu.");
            keepPlaying = false;
        }
    }
}
