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
                "moves: " + Main.moves + "\n" +
                "flowers " + Main.flowers);
    }

    public static void changeSettings() {
        System.out.println("Put 1 to change one option or 2 to change more");

        changes = scanner.nextLine();
        if (changes.equals("1")) {
            changeOneOptionManually();
        }
        if (changes.equals("2")) {
            changeAllOptions();
        }
    }

    public static void changeAllOptions() {
        System.out.println("enter a new value for rows, leave blank to keep current value:");

        Main.rows = Integer.parseInt(scanner.nextLine());

        System.out.println("enter a new value for columns, leave blank to keep current value:");

        Main.columns = Integer.parseInt(scanner.nextLine());

        System.out.println("enter a new value for enemies, leave blank to keep current value:");

        Main.enemies = Integer.parseInt(scanner.nextLine());

        System.out.println("enter a new value for transistors, leave blank to keep current value:");

        Main.transistors = Integer.parseInt(scanner.nextLine());

        System.out.println("enter a new value for moves, leave blank to keep current value:");

        Main.moves = Integer.parseInt(scanner.nextLine());

        System.out.println("enter a new value of flowers amount, leave blank to keep current value:");

        Main.flowers = Integer.parseInt(scanner.nextLine());

        if(!isValuesPlayable()) {
            System.out.println("Values you entered are not playable. please, verify again. now settings return to default");
            Main.rows = 12;
            Main.columns = 20;
            Main.enemies = 20;
            Main.transistors = 100;
            Main.moves = 40;
            Main.flowers = 20;
        }

    }

    public static void changeOneOptionManually() {
        System.out.println("what option do you want to change?\n" +
                "put 1 to change amount of rows\n" +
                "put 2 to change amount of columns\n" +
                "put 3 to change amount of enemies\n" +
                "put 4 to change amount of transistors\n" +
                "put 5 to change your moves\n" +
                "or put 6 to change amount of flowers");
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
            case "6":
                Main.flowers = Integer.parseInt(scanner.nextLine());
                break;

        }
        if(!isValuesPlayable()) {
            System.out.println("Values you entered are not playable. please, verify again. now settings return to default");
            Main.rows = 12;
            Main.columns = 20;
            Main.enemies = 20;
            Main.transistors = 100;
            Main.moves = 40;
            Main.flowers = 20;
        }
    }

    private static Boolean isValuesPlayable() {
        int fieldSize = Main.rows * Main.columns;

        int allGameObjects = Main.enemies + Main.flowers + 1;

        boolean isValuesPlayable = ((allGameObjects / fieldSize) < 0.75);

        return isValuesPlayable;
    }



}
