package game;

import java.util.ArrayList;
import java.util.Random;

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


    public Game(int rows, int columns, int enemies, int transistorsNeeded, int movesLeft, int flowers) {
        this.rows = rows;
        this.columns = columns;
        this.enemies = enemies;
        this.transistorsNeeded = transistorsNeeded;
        this.movesLeft = movesLeft;
        this.flowers = flowers;
        field = new Field(rows,columns);
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

            botMove();

            checkIfGameIsFinished();
        }
    }


    private void playerPose() {
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

    }

    private void botMove() {

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
                Flower flower = new Flower(flowerTransistors);

                field.setField(flowerRowsPosition, flowerColumnsPosition, flower);

                flowerArrayList.add(flower);
                i--;
            }
        }
    }

    private void checkIfGameIsFinished() {

    }
}
