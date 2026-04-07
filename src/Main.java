import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short choice = 100;

        while (choice != 9) {
            System.out.print("""
                    Hello and Welcome to the main menu!
                    Please select from the following options (or 9 to exit):
                        0: Boogle
                        1: War
                        2: Rock Paper Scissors
                        3: Word Scamble
                        4: Tic Tac Toe
                        5: Wheel of Fortune
                        6: Black Jack
                        9: Exit
                    Enter the number of your selection:
                    """);

            choice = Short.parseShort(scan.nextLine());
            switch (choice) {
                case 0:
                    Boogle boogle = new Boogle();
                    while (boogle.keepPlaying) {
                        boogle.run();
                        boogle.checkExit();
                    }
                    break;
                case 1:
                    CardGames war = new CardGames();
                    while (war.keepPlaying) {
                        war.runWar();
                        war.checkExit();
                    }
                    break;
                case 2:
                    RockPaperScissors rps = new RockPaperScissors();
                    while (rps.keepPlaying) {
                        rps.run();
                        rps.checkExit();
                    }
                    break;
                case 3:
                    Scramble scramble = new Scramble();
                    while (scramble.keepPlaying) {
                        scramble.run();
                        scramble.checkExit();
                    }
                    break;
                case 4:
                    TicTacToe ttt = new TicTacToe();
                    while (ttt.keepPlaying) {
                        ttt.run();
                        ttt.checkExit();
                    }
                    break;
                case 5:
                    Wheel wheel = new Wheel();
                    while (wheel.keepPlaying) {
                        wheel.run();
                        wheel.checkExit();
                    }
                    break;
                case 6:
                    CardGames blackjack = new CardGames();
                    while (blackjack.keepPlaying) {
                        blackjack.runBlackjack();
                        blackjack.checkExit();
                    }
                    break;
                case 7:
                    Deck deck = new Deck();

                    IO.println(deck);

                    break;
                default:
                    System.out.println("Thanks for playing!\nGoodbye!");
                    choice = 9; // set to exit
            }
        }
    }
}