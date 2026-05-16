import java.util.Random;
import java.util.Scanner;

import static jdk.tools.jmod.Main.main;

public class RockPaperScissors {
    public boolean keepPlaying;

    public static void main(String[] args) {
    // Boolean variable which is true until player wants to quit
    boolean keepPlaying = true;
    
    Scanner input = new Scanner(System.in);
    Random r = new Random(); 
    
        int cMove = r.nextInt(3) + 1;
        int uMove = 0;
        int cScore = 0;
        int pScore = 0;
        int tie = 0;
        int rounds = 0;

        String answer = "yes";

        while (answer.equalsIgnoreCase("yes")) {
            System.out.println("Choose your move!");
            System.out.println("Enter 1 for rock, 2 for paper, or 3 for scissors:");

            int userMove = input.nextInt();
            while (input.hasNextInt()) {
                if (userMove != 1 && userMove != 2 && userMove != 3) {
                    System.out.println("Invalid move. Try again.");
                    System.out.println("Enter your move: ");
                    userMove = input.nextInt();
                }
            }

            if (userMove == 1) {
                System.out.println("You have chosen rock!");
            } else if (userMove == 2) {
                System.out.println("You have chosen paper!");
            } else if (userMove == 3) {
                System.out.println("You have chosen scissors!");
            }


            if (userMove == cMove) {
                System.out.println("Tie game!");
                tie++;
                rounds++;
            } else if (cMove == 1 && userMove == 3) {
                System.out.println("Computer chose rock.");
                System.out.println("Rock beats scissors.");
                System.out.println("Computer Wins!");
                cScore++;
                rounds++;
            } else if (cMove == 1 && userMove == 2) {
                System.out.println("Computer chose rock.");
                System.out.println("Paper beats rock.");
                System.out.println("You win!");
                pScore++;
                rounds++;
            } else if (cMove == 2 && userMove == 3) {
                System.out.println("Computer chose rock");
                System.out.println("Scissors beats paper.");
                System.out.println("You win!");
                pScore++;
                rounds++;
            } else if (cMove == 2 && userMove == 1) {
                System.out.println("Computer chose paper.");
                System.out.println("Paper beats rock.");
                System.out.println("Computer wins!");
                cScore++;
                rounds++;
            } else if (cMove == 3 && userMove == 1) {
                System.out.println("Computer chose scissors.");
                System.out.println("Rock beats scissors.");
                System.out.println("You win!");
                pScore++;
                rounds++;
            } else if (cMove == 3 && userMove == 2) {
                System.out.println("Computer chose scissors.");
                System.out.println("Scissors beats paper.");
                System.out.println("Computer wins!");
                cScore++;
                rounds++;
            }
            

            System.out.println("Would you like to play again?");
            System.out.println("Yes or no?");
            input.next();
            answer = input.next();


            if (answer.equalsIgnoreCase("yes")) {
                try {
                    main(null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Here is the final score after " + rounds + "rounds:");
                System.out.println("You: " + pScore + "Computer: " + cScore + "Ties: " + tie);
            }
        }
    }

    public void run() {
        System.out.println("Rock Paper Scissors - TBD");
    }

    /**
     * Asks the player if they want to exit or keep playing
     * If they want to exit - change keepPlaying flag (variable) to false.
     * For now just changes flog so this can exit
     */
    public void checkExit() {
        boolean keepPlaying = false;
    }
}
