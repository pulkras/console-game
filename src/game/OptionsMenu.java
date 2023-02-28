package game;

import java.util.Scanner;

public class OptionsMenu {

    static Scanner scanner = new Scanner(System.in);
    static String command;
    static String changes;

    static String optionToChoose;

    public static void showOptions() {
        do {
            System.out.println("make your choice and press Enter, please\n" +
                    "put 1 to show your current settings\n" +
                    "put 2 to change settings\n" +
                    "put 3 to exit");
            command = scanner.nextLine();

            if(command.equals("1"))
                showCurrentSettings();
            else if(command.equals("2"))
                changeSettings();
            else if(!command.equals("3"))
                System.out.println("try again to put correct number");


        } while(!command.equals("3"));
    }

    public static void showCurrentSettings() {
        System.out.println("Current settings:\n" +
                "rows: " + Main.rows + "\n" +
                "columns: " + Main.columns + "\n" +
                "enemies: " + Main.enemies + "\n" +
                "transistors: " + Main.transistors + "\n" +
                "moves: " + Main.moves);
    }

    public static void changeSettings() {
        System.out.println("Put 1 to change one option or 2 to change more");
        changes = scanner.nextLine();
        if(changes.equals("1")) {
            changeOneOptionManually();
        }
        else if(changes.equals("2")) {
            System.out.println("enter a new value for rows: ");
            Main.rows = Integer.parseInt(scanner.nextLine());
            System.out.println("enter a new value for columns: ");
            Main.columns = Integer.parseInt(scanner.nextLine());
            System.out.println("enter a new value for enemies: ");
            Main.enemies = Integer.parseInt(scanner.nextLine());
            System.out.println("enter a new value for transistors: ");
            Main.transistors = Integer.parseInt(scanner.nextLine());
            System.out.println("enter a new value for moves: ");
            Main.moves = Integer.parseInt(scanner.nextLine());
        }
    }
    public static void changeOneOptionManually() {
        System.out.println("what option do you want to change?\n" +
                "put 1 to change amount of rows\n" +
                "put 2 to change amount of columns\n" +
                "put 3 to change amount of enemies\n" +
                "put 4 to change amount of transistors\n" +
                "or put 5 to change your moves");
        optionToChoose = scanner.nextLine();
        System.out.println("Write your number");
        switch (optionToChoose) {
            case "1":
                Main.rows = Integer.parseInt(scanner.nextLine());
                break;
            case "2":
                Main.columns = Integer.parseInt(scanner.nextLine());
                break;
            case "3":
                Main.enemies = Integer.parseInt(scanner.nextLine());
                break;
            case "4":
                Main.transistors = Integer.parseInt(scanner.nextLine());
                break;
            case "5":
                Main.moves = Integer.parseInt(scanner.nextLine());
                break;

        }
    }
}
