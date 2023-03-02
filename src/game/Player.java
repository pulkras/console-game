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

    private Game game;

    @Override
    public String getSymbol() {
        return " $ ";
    }

    public Player(int rowIndex, int columnIndex, Game game) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setField(rowIndex, columnIndex, this);
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

    public Boolean makeMove(String movesCommand) {
        Boolean isItACorrectMove = true;

        if(movesCommand.equals(MOVE_LEFT))
            isItACorrectMove = movePlayer(0, -1);
        else if(movesCommand.equals(MOVE_RIGHT))
            isItACorrectMove = movePlayer(0, 1);
        else if(movesCommand.equals(MOVE_UP))
            isItACorrectMove = movePlayer(-1, 0);
        else if(movesCommand.equals(MOVE_DOWN))
            isItACorrectMove = movePlayer(1, 0);
        else if(movesCommand.equals(NO_MOVE))
            isItACorrectMove = false;
        else
            showError();
        return isItACorrectMove;
    }

    private Boolean movePlayer(int deltaRowIndex, int deltaColumnIndex) {

        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        if((newRowIndex >= 0) && (newRowIndex < field.getRows())
            && (newColumnIndex >= 0) && (newColumnIndex < field.getColumns())
            && !((field.getField(newRowIndex, newColumnIndex)) instanceof Enemy)) {
            if(field.getField(newRowIndex, newColumnIndex) instanceof Flower) {
                Flower flowerIndex = (Flower) field.getField(newRowIndex, newColumnIndex);
                game.setTransistorsGathered(flowerIndex.getTransistors());
                game.getFlowerArrayList().remove(flowerIndex);
                swapPlayer(newRowIndex, newColumnIndex);
            }

            if(field.getField(newRowIndex, newColumnIndex) instanceof Empty) {
                swapPlayer(newRowIndex, newColumnIndex);
            }
            return false;
        }
        else {
            return true;
        }
    }

    private void swapPlayer(int newRowIndex, int newColumnIndex) {
        field.setField(newRowIndex, newColumnIndex, this);
        field.setField(rowIndex, columnIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }

    private void showError() {
        System.out.println("Sorry, there is no such type of command");
    }
}
