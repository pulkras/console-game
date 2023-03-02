package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int rows;

    private int columns;

    private int enemies;

    private int transistorsNeeded;

    private int transistorsGathered;

    private int movesLeft;

    private int flowers;

    private Field field;

    private boolean gameIsFinished;

    private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();

    private Random randomNumber = new Random();

    private Player player;

    private Scanner scanner = new Scanner(System.in);

    private Boolean isItACorrectCommand = true;


    public Game(int rows, int columns, int enemies, int transistorsNeeded, int movesLeft, int flowers) {
        this.rows = rows;
        this.columns = columns;
        this.enemies = enemies;
        this.transistorsNeeded = transistorsNeeded;
        this.movesLeft = movesLeft;
        this.flowers = flowers;
        field = new Field(rows,columns);
    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return this.flowerArrayList;
    }

    public void setTransistorsGathered(int transistorsAdded) {
        this.transistorsGathered += transistorsAdded;
    }

    public void fillFieldIfEmpty() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                field.setField(i, j, new Empty());
            }
        }
    }

    public void startGame() {
        playerPose();
        enemiesPoses();
        flowersPoses();

        while(!gameIsFinished) {
            showField();

            playerMove();
            if(isItACorrectCommand) {
                continue;
            }
            botMove();

            checkIfGameIsFinished();
        }
    }


    private void playerPose() {

        int playerRowsPosition = randomNumber.nextInt(rows);
        int playerColumnsPosition = randomNumber.nextInt(columns);
        player = new Player(playerRowsPosition, playerColumnsPosition, this);
    }

    private void enemiesPoses() {
    }

    private void flowersPoses() {
        generateFlowers();
    }


    private void showField() {
        System.out.println("\n\nMoves left: " + movesLeft +
                ", transistors gathered: " + transistorsGathered +
                "/" + transistorsNeeded);
        field.showField();
    }

    private void playerMove() {
        System.out.println("Please put your command and press Enter");
        String command = scanner.nextLine();
        player.makeMove(command);
    }

    private void botMove() {
        movesLeft--;
    }

    private void generateFlowers() {

        for(int i = flowers - flowerArrayList.size(); i > 0;) {
            int flowerTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowsPosition = randomNumber.nextInt(rows);
            int flowerColumnsPosition = randomNumber.nextInt(columns);

            if(field.getField(flowerRowsPosition, flowerColumnsPosition) instanceof Player) {
                transistorsGathered += flowerTransistors;
                i--;
            }

            else if(field.getField(flowerRowsPosition, flowerColumnsPosition) instanceof Empty) {
                Flower flower = new Flower(flowerTransistors, flowerRowsPosition, flowerColumnsPosition);

                field.setField(flowerRowsPosition, flowerColumnsPosition, flower);

                flowerArrayList.add(flower);
                i--;
            }
        }
    }

    private void checkIfGameIsFinished() {

    }
}
