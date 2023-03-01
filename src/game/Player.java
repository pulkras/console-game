package game;

public class Player implements Fieldable {

    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "s";
    private static final String NO_MOVE = "x";

    private int rowIndex;
    private int columnIndex;

    private Field field;

    @Override
    public String getSymbol() {
        return " $ ";
    }

    public Player(int rowIndex, int columnIndex, Field field) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.field = field;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void makeMove(String movesComand) {
        if(movesComand.equals(MOVE_LEFT))
            movePlayer(-1, 0);
        else if(movesComand.equals(MOVE_RIGHT))
            movePlayer(+1, 0);
        else if(movesComand.equals(MOVE_UP))
            movePlayer(0, -1);
        else if(movesComand.equals(MOVE_DOWN))
        movePlayer(0, +1);
        else if(movesComand.equals(NO_MOVE))
        movePlayer(0, 0);
        else
            showError();
    }

    private void movePlayer(int coordinateX, int coordinateY) {

    }

    private void showError(String command) {
        System.out.println("Sorry, there is no such type of command");
    }
}
