import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short choice = 100;

        while (choice != 9) {
            // Card games are listed from easiest to program to hardest
            System.out.print("""
                    Hello and Welcome to the main menu!
                    Please select from the following options (or 9 to exit):
                        0: BlackJack
                        1: Go Fish
                        2: Solitaire
                        3: Poker
                        4: Uno
                        5: Rummy
                        9: Exit
                    Enter the number of your selection:
                    """);

            choice = Short.parseShort(scan.nextLine());
            switch (choice) {
                case 0:
                    BlackJack blackJack = new BlackJack();
                    while (blackJack.keepPlaying) {
                        blackJack.run();
                        blackJack.checkExit();
                    }
                    break;
                case 1:
                    Fish gofish = new Fish();
                    while (gofish.keepPlaying) {
                        gofish.run();
                        gofish.checkExit();
                    }
                    break;
                case 2:
                    Solitaire sol = new Solitaire();
                    while (sol.keepPlaying) {
                        sol.run();
                        sol.checkExit();
                    }
                    break;
                case 3:
                    Poker poker = new Poker();
                    while (poker.keepPlaying) {
                        poker.run();
                        poker.checkExit();
                    }
                    break;
                case 4:
                    Uno uno = new Uno();
                    while (uno.keepPlaying) {
                        uno.run();
                        uno.checkExit();
                    }
                    break;
                case 5:
                    Rummy rummy = new Rummy();
                    while (rummy.keepPlaying) {
                        rummy.run();
                        rummy.checkExit();
                    }
                    break;
                default:
                    System.out.println("Thanks for playing!\nGoodbye!");
                    choice = 9; // set to exit
            }
        }
    }
}