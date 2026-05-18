import java.util.*;

public class Scramble {
    public boolean keepPlaying = true;

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    // Words List
    private final String[] easy = {"cat", "dog", "bat", "key", "left", "java", "loop"};
    private final String[] medium = {"enter", "array", "logic", "right", "wrong"};
    private final String[] hard = {"keyboard", "programming", "developer", "Scramble", "computer"};

    private double score = 0;
    private int round = 1;
    private static final int points_correct = 10;
    private static final int points_wrong = -5;
    /**
     * Runs the main game loop
     */
    public void run() {
        System.out.println("Welcome to Scramble!");

        while (keepPlaying) {
            playRound();
            checkExit();
        }
        System.out.println("Final Score: " + score);
    }

    private void playRound() {
        String word = getRandomWord();
        String scrambled = scrambleWord(word);

        System.out.println("\nRound " + round++);
        System.out.println("\nUnscramble the word: " + scrambled);


        System.out.print("Your guess: ");
        String guess = scanner.nextLine().trim();

        if (guess.equalsIgnoreCase(word)) {
            score += points_correct;
            System.out.println("Correct! +" + points_correct + " points");
        } else {
            score += points_wrong;
            System.out.println("Wrong! The word was: " + word);
            System.out.println(points_wrong + " points");
        }
        System.out.println("Current Score: " + score);

    }

    private String getRandomWord() {
        String difficulty;

        while (true) {
            System.out.print("Choose difficulty (easy/medium/hard): ");
            difficulty = scanner.nextLine().toLowerCase().trim();

            if (difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("hard")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter easy, medium, or hard.");
            }
        }

        String[] selected;

        switch (difficulty) {
            case "easy" -> selected = easy;
            case "medium" -> selected = medium;
            default -> selected = hard;
        }
        return selected[random.nextInt(selected.length)];
    }

    private String scrambleWord(String word) {
        String scrambled;
        do {
            List<Character> letters = new ArrayList<>();

            for (char c : word.toCharArray()) {
                letters.add(c);
            }

            Collections.shuffle(letters);

            StringBuilder sb = new StringBuilder();
            for (char c : letters) {
                sb.append(c);
            }
            scrambled = sb.toString();

        } while (scrambled.equalsIgnoreCase(word));

        return scrambled;
    }


    public void checkExit() {
        while (true) {
        System.out.print("Play again? (yes/no): ");
        String response = scanner.nextLine().toLowerCase().trim();

        if (response.equals("yes")) {
            return;
        } else if (response.equals("no")) {
            keepPlaying = false;
            System.out.println("Thanks for playing!");
            return;
        } else {
            System.out.println("Invalid input. Please type 'yes' or 'no'.");
        }
    }

}
}