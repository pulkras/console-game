package game;

public class Field {

    private int rows;

    private int columns;

    private Fieldable[][] field;

    public Field(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        field = new Fieldable[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setField(int row, int column, Fieldable point) {

        field[row][column] = point;
    }

    public Fieldable getField(int row, int column) {
        return field[row][column];
    }

    public void showField() {
        System.out.println();

        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < columns; j++) {
                System.out.print(field[i][j].getSymbol());
            }
            System.out.println();
        }
    }


}
