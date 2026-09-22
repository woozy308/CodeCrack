import java.util.Scanner;
import java.util.Random;

public class Code {
    Scanner keyboard = new Scanner(System.in);
    Random rand = new Random();

    public char char1;
    public char char2;

    public int codeLength;

    public String doorCode1 = "";
    public String doorCode2 = "";

    Code() {

    }

    public void gameIntro() {
        System.out.println("Welcome to CodeCrack!");
        System.out.println("The game where you have to brute force or spam your way through two different locked doors to escape!");
    }

    public void gameMenu() {
        int choice;
        System.out.println("Please select what you would like to do:");
        System.out.println("1- Play with original settings (x|-, code = 4)");
        System.out.println("2- Play with custom settings");
        System.out.println("3- View credits");
        choice = keyboard.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Original settings loading...");
                codeLength = 4;
                char1 = 'x';
                char2 = '-';
                System.out.println("Starting now!");
                break;
            case 2:
                boolean confirm = false;
                while (!confirm) {
                    System.out.println("Please enter custom settings!");
                    System.out.print("Please enter code character1: ");
                    char1 = keyboard.next().charAt(0);
                    System.out.println();
                    System.out.print("Please enter code character2: ");
                    char2 = keyboard.next().charAt(0);
                    System.out.println();
                    System.out.print("Please select code length: ");
                    codeLength = keyboard.nextInt();
                    System.out.println();

                    System.out.println("Custom Settings input: " + char1 + "|" + char2 + ", code = " + codeLength);
                    confirm = true;
                }
                break;
            case 3:
                System.out.println("credits viewed");
                break;
            default:
                System.out.println("Please reselect a menu option.");
                choice = keyboard.nextInt();
        }
    }

    /*
    public void gameSettings() {

    }
    */

    public void playGame() {
        generateCode();
        devCheat();
        validateCode();
        playAgain();
    }

    public void generateCode() {
        // code1 //
        for (int i = 0; i < codeLength; i++) {
            int codeGen = rand.nextInt(2);
            if (codeGen == 1) {
                doorCode1 = doorCode1 + char1;
            }
            else {
                doorCode1 = doorCode1 + char2;
            }
        }

        // code2 //
        for (int i = 0; i < codeLength; i++) {
            int codeGen = rand.nextInt(2);
            if (codeGen == 1) {
                doorCode2 = doorCode2 + char1;
            }
            else {
                doorCode2 = doorCode2 + char2;
            }
        }
    }

    public void validateCode() {
        boolean codeCrack1 = false;
        boolean codeCrack2 = false;
        System.out.println("Crack the two doors!");

        System.out.print("Insert guess: ");
        String guess = keyboard.nextLine();

        while (!(codeCrack1 && codeCrack2)) {

            if (guess.equals(doorCode1) && !codeCrack1) {
                System.out.println("Door 1's code has been cracked!");
                codeCrack1 = true;

                System.out.println();
                System.out.println("Safe Door progress: (1/2)");
                System.out.println("Moving onto Door 2...");
                System.out.print("Insert guess: ");
                guess = keyboard.nextLine();
            }
            else if (guess.equals(doorCode2) && codeCrack1) {
                System.out.println("Door 2's code has been cracked!");
                codeCrack2 = true;

                System.out.println();
                System.out.println("Safe Door progress: (2/2)");
                System.out.println("Opening safe door now...");
            }
            else {
                System.out.println("Error! Code is incorrect, try again!");
                System.out.print("Insert guess: ");
                guess = keyboard.nextLine();
            }
        }
        System.out.println("Door 1 and Door 2 have been cracked, opening the safe door now!");
        System.out.println("Congratulations, you've won!");
    }

    public void playAgain() {
        int choice;
        System.out.println("Play again?");
        System.out.println("1- Yes | 2- No");
        choice = keyboard.nextInt();

        if (choice == 1) {
            System.out.println("Resetting...");
            doorCode1 = "";
            doorCode2 = "";
            playGame(); // basically a natural factory reset lol //
        }
        else if (choice == 2) {
            System.out.println("Have a nice day! We hope you've enjoyed our game.");
        }
        else {
            System.out.println("Sorry, please reselect your answer.");
            System.out.println("Play again?");
            System.out.println("1- Yes | 2- No");
            choice = keyboard.nextInt();
        }
    }

    public void devCheat() {
        System.out.println("Door1 Code: " + doorCode1);
        System.out.println("Door2 Code: " + doorCode2);
    }
}
