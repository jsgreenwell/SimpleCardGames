import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    // Boolean variable which is true until player wants to quit
    public boolean keepPlaying = true;

    /**
     * Runs the main game loop - calling other functions as needed.
     * You will create this as for now it just prints out - TBD.
     */
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    String[] choices = {"rock", "paper", "scissors"};

    public void run() {
        while (keepPlaying) {
            System.out.print("Enter your move (rock, paper, scissors): ");
            String playerChoice = scanner.nextLine().toLowerCase();

            // Validate input
            if (!playerChoice.equals("rock") &&
                    !playerChoice.equals("paper") &&
                    !playerChoice.equals("scissors")) {
                System.out.println("Invalid entry. Try again.");
                continue;
            }

            //Computer Choice
            String computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);

            //Determine Winner
            if (playerChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                    (playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                            (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
                            (playerChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("You're Winner!");
            } else {
                System.out.println("You're a Loser!");
            }
            checkExit();


            /**
             * Asks the player if they want to exit or keep playing
             * If they want to exit - change keepPlaying flag (variable) to false.
             * For now just changes flog so this can exit
             */

        }}
    public void checkExit () {
        System.out.print("Play again? (yes/no)");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("no")){
            keepPlaying = false;
            System.out.println("Thanks for playing!");
        }}}