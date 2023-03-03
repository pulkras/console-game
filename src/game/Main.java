package game;

import java.util.Scanner;

public class Main {

    public static int rows = 10;
    public static int columns = 10;
    public static int enemies = 5;
    public static int transistors = 20;
    public static int moves = 20;

    public static int flowers = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.println("Welcome to JavaGame. This game is an example of usage java constructions.\n" +
                    "Make your choice and press Enter, please\n" +
                    "put 1 to start a new game\n" +
                    "put 2 to see options\n" +
                    "put 3 to see info about author\n" +
                    "put 4 to exit from this game");

            command = scanner.nextLine();

            if (command.equals("1"))
                startNewGame();
            else if (command.equals("2"))
                OptionsMenu.showOptions();
            else if (command.equals("3"))
                showInfoAboutAuthor();
            else if (!command.equals("4"))
                System.out.println("please try again to choose a number.");
        } while (!command.equals("4"));
    }

    private static void startNewGame() {
        Game game = new Game(rows, columns, enemies, transistors, moves, flowers);

        game.fillFieldIfEmpty();

        game.startGame();
    }

    private static void showInfoAboutAuthor() {
        System.out.println("This game was created by pulkras.\n" +
                "I took the idea from Дмитрий Финашкин channel.\n" +
                "I decided to do this because I've never done games on Java previously\n");
    }
}
