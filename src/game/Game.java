package game;

import java.lang.reflect.ParameterizedType;
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

    private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();

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
                incorrectCommand();
                continue;
            }
            botMove();

            checkIfGameIsFinished();
        }
    }

    private void incorrectCommand() {
        System.out.println("You entered an incorrect command. please, try again");
    }


    private void playerPose() {

        int playerRowsPosition = randomNumber.nextInt(rows);
        int playerColumnsPosition = randomNumber.nextInt(columns);
        player = new Player(playerRowsPosition, playerColumnsPosition, this);
    }

    private void enemiesPoses() {
        generate(enemies, enemyArrayList);
    }

    private void flowersPoses() {
        generate(flowers, flowerArrayList);
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
        enemyMove();

        generate(flowers, flowerArrayList);

        movesLeft--;
    }

    private void enemyMove() {

        int rowIndex = 0;
        int columnIndex = 0;
        int newRowIndex = 0;
        int newColumnIndex = 0;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate = true;

        for(Enemy enemy : enemyArrayList) {

            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();
            do {
                int deltaRow = randomNumber.nextInt(3) - 1;
                int deltaColumn = randomNumber.nextInt(3) - 1;
                newRowIndex = rowIndex + deltaRow;
                newColumnIndex = columnIndex + deltaColumn;

                if((newRowIndex < 0) || (newColumnIndex < 0) ||
                newRowIndex >= field.getRows() || (newColumnIndex >= field.getColumns()) ||
                field.getField(newRowIndex, newColumnIndex) instanceof Player || field.getField(newRowIndex, newColumnIndex) instanceof Enemy) {
                    regenerateIndex++;
                    isNeededToRegenerate = true;
                } else {
                    if(field.getField(newRowIndex, newColumnIndex) instanceof Flower) {
                        Flower flowerIndex = (Flower) field.getField(newRowIndex, newColumnIndex);
                        flowerArrayList.remove(flowerIndex);

                        isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);

                    } else {
                        isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                    }
                }

            } while (isNeededToRegenerate && regenerateIndex <= 10);
        }
    }

    private boolean swapEnemy(int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy) {
        field.setField(newRowIndex, newColumnIndex, enemy);
        field.setField(rowIndex, columnIndex, new Empty());

        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);

        return false;
    }

    private void generate(int typeAmount, ArrayList typeArrayList) {
        for(int i = typeAmount - typeArrayList.size(); i > 0;) {
            int typeRowsPosition = randomNumber.nextInt(rows);
            int typeColumnsPosition = randomNumber.nextInt(columns);

            if (typeArrayList.equals(flowerArrayList)) {
                int flowerTransistors = randomNumber.nextInt(9) + 1;
                if (field.getField(typeRowsPosition, typeColumnsPosition) instanceof Player) {
                    transistorsGathered += flowerTransistors;
                    i--;
                } else if (field.getField(typeRowsPosition, typeColumnsPosition) instanceof Empty) {
                    Flower flower = new Flower(flowerTransistors, typeRowsPosition, typeColumnsPosition);

                    field.setField(typeRowsPosition, typeColumnsPosition, flower);

                    flowerArrayList.add(flower);
                    i--;
                }
            } else if(typeArrayList.equals(enemyArrayList) && field.getField(typeRowsPosition, typeColumnsPosition) instanceof Empty) {
                Enemy enemy = new Enemy(typeRowsPosition, typeColumnsPosition);

                field.setField(typeRowsPosition, typeColumnsPosition, enemy);

                enemyArrayList.add(enemy);
                i--;
            }
        }
    }

    private void checkIfGameIsFinished() {

    }
}
