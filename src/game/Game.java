package game;

public class Game {
    private int rows;

    private int columns;

    private int enemies;

    private int transistors;

    private int moves;

    private int flowersGathered;

    private Field field;

    private boolean gameIsFinished;

    public Game(int rows, int columns, int enemies, int transistors, int moves) {
        this.rows = rows;
        this.columns = columns;
        this.enemies = enemies;
        this.transistors = transistors;
        this.moves = moves;
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
    }


    private void showField() {

    }

    private void playerMove() {

    }

    private void botMove() {

    }

    private void checkIfGameIsFinished() {

    }
}
