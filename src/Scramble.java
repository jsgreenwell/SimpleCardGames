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
    }

    private void playRound() {
        String word = getRandomWord();
        String scrambled = scrambleWord(word);

        System.out.println("\nUnscramble the word: " + scrambled);


        System.out.print("Your guess: ");
        String guess = scanner.nextLine();

        if (guess.equalsIgnoreCase(word)) {
            score += points_correct;
            System.out.println("Correct!");
            System.out.println("\nScore: " + score);
        } else {
            score += points_wrong;
            System.out.println("Wrong! The word was: " + word);
        }
    }

    private String getRandomWord() {
        System.out.print("Choose difficulty (easy/medium/hard): ");
        String difficulty = scanner.nextLine().toLowerCase();

        String[] selected;

        switch (difficulty) {
            case "easy" -> selected = easy;
            case "medium" -> selected = medium;
            case "hard" -> selected = hard;
            default -> {
                System.out.println("Invalid choice. Defaulting to easy.");
                selected = easy;
            }
        }

        return selected[random.nextInt(selected.length)];
    }

    private String scrambleWord(String word) {
        List<Character> letters = new ArrayList<>();

        for (char c : word.toCharArray()) {
            letters.add(c);
        }

        Collections.shuffle(letters);

        StringBuilder scrambled = new StringBuilder();

        for (char c : letters) {
            scrambled.append(c);
        }
        return scrambled.toString();
    }

    public void checkExit() {
        System.out.print("Play again? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("no")) {
            keepPlaying = false;
            System.out.println("Thanks for playing!");
        }
    }
}