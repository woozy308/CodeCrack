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

    public boolean hintStatus = false;
    public boolean cheatStatus = false;

    public char doorCheat1;
    public char doorCheat2;

    Code() {

    }

    public void gameIntro() {
        System.out.println("Welcome to CodeCrack!");
        System.out.println("The game where you have to brute force or spam your way through two different locked doors to escape!");
    }

    public void gameMenu() {
        int choice;
        System.out.println();
        System.out.println("Game Menu: ");
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
                System.out.println("Game Credits: ");
                System.out.println("Lead Game Developer: Jadon Nguyen");
                System.out.println("Programmer & Designer: Jadon Nguyen");
                System.out.println();
                System.out.println("Game inspiration from Flood Escape 1 by Crazyblox on Roblox.");
                System.out.println();
                System.out.println("A special thanks to early playtesters and you!");
                break;
            default:
                System.out.println("Please reselect a menu option.");
                choice = keyboard.nextInt();
        }
    }

    public void gameSettings() {
        boolean finished = false;
        int settingChoice;

        while (!finished) {
            System.out.println("Game Settings:");
            System.out.println("Enable Hints: " + hintStatus);
            System.out.println("Enable Cheats: " + cheatStatus);
            System.out.println();
            System.out.println("Would you like to enable/disable these settings?");
            System.out.println("1- Enable/Disable Hints");
            System.out.println("2- Enable/Disable Cheats");
            System.out.println("3- Exit Settings");
            settingChoice = keyboard.nextInt();
            switch (settingChoice) {
                case 1:
                    if (!hintStatus) {
                        hintStatus = true;
                    } else {
                        hintStatus = false;
                    }
                    break;
                case 2:
                    if (!cheatStatus) {
                        cheatStatus = true;
                    } else {
                        cheatStatus = false;
                    }
                    break;
                case 3:
                    finished = true;
                    break;
                default:
                    System.out.println("Error: Please reselect choice!");
                    settingChoice = keyboard.nextInt();
            }
        }

    }

    public void playGame() {
        generateCode();
        if (hintStatus) {
            devHint();
        }
        if (cheatStatus) {
            devCheat();
        }
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

    public void devHint() {
        int random1 = rand.nextInt(0, codeLength);
        int random2 = rand.nextInt(0, codeLength);
        doorCheat1 = doorCode1.charAt(random1);
        doorCheat2 = doorCode2.charAt(random2);
        System.out.println("Hint note: index values start at 0.");
        System.out.println("Door1 Hint: " + "'" + doorCheat1 + "'" + " is at index " + random1);
        System.out.println("Door2 Hint: " + "'" + doorCheat2 + "'" + " is at index " + random2);
    }

    public void devCheat() {
        System.out.println("Door1 Code: " + doorCode1);
        System.out.println("Door2 Code: " + doorCode2);
    }
}