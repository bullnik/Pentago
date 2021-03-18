import java.util.ArrayList;

public class Field {
    private final Matrix<ChildField> childFields;

    public Field() throws Exception {
        childFields = new Matrix<>(2, new ChildField[][] {
                new ChildField[] { new ChildField(), new ChildField() },
                new ChildField[] { new ChildField(), new ChildField() }
        });
    }

    public boolean isFull() throws Exception {
        Matrix<Cell> cellMatrix = mergeChildFields();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                if (cellMatrix.getValue(x, y).getState() == CellState.NotEmpty) {
                    return false;
                }
            }
        }
        return false;
    }

    public void rotateField(int x, int y, RotateDirection rotateDirection) throws Exception {
        childFields.getValue(x, y).rotate(rotateDirection);
    }

    public int getMaxLineLength(BallColor ballColor) throws Exception {
        Matrix<Cell> cells = mergeChildFields();

        int mh = getMaxHorizontalLineLength(cells, ballColor);
        int mv = getMaxVerticalLineLength(cells, ballColor);
        int md = getMaxDiagonalLineLength(cells, ballColor);

        return Math.max(mh, Math.max(mv, md));
    }

    private int getMaxHorizontalLineLength(Matrix<Cell> cells, BallColor ballColor) throws Exception {
        int max = 0;
        ArrayList<ArrayList<Cell>> list = new ArrayList<>();
        cells.getRows(list);
        for (ArrayList<Cell> arr : list) {
            int seqL = getMaxSeqLength(arr, ballColor);
            if (seqL > max) max = seqL;
        }
        return max;
    }

    private int getMaxVerticalLineLength(Matrix<Cell> cells, BallColor ballColor) throws Exception {
        int max = 0;
        ArrayList<ArrayList<Cell>> list = new ArrayList<>();
        cells.getColumns(list);
        for (ArrayList<Cell> arr : list) {
            int seqL = getMaxSeqLength(arr, ballColor);
            if (seqL > max) max = seqL;
        }
        return max;
    }

    private int getMaxDiagonalLineLength(Matrix<Cell> cells, BallColor ballColor) throws Exception {
        int max = 0;
        ArrayList<ArrayList<Cell>> list = new ArrayList<>();
        cells.getDiagonals(list);
        for (ArrayList<Cell> arr : list) {
            int seqL = getMaxSeqLength(arr, ballColor);
            if (seqL > max) max = seqL;
        }
        return max;
    }

    private int getMaxSeqLength(ArrayList<Cell> seq, BallColor ballColor) throws Exception {
        int max = 0;
        int counter = 0;
        for (Cell cell : seq) {
            if (cell.getState() == CellState.NotEmpty
                    && cell.getBall().getColor() == ballColor) {
                counter++;
            } else {
                if (counter > max) max = counter;
                counter = 0;
            }
        }

        return max;
    }

    public Matrix<Cell> getCells() throws Exception {
        return mergeChildFields();
    }

    private ChildField getChildFieldByIndex(int x, int y) {
        int childFieldXIndex = (x > 2) ? 1 : 0;
        int childFieldYIndex = (y > 2) ? 1 : 0;

        return childFields.getValue(childFieldXIndex, childFieldYIndex);
    }

    private Matrix<Cell> mergeChildFields() throws Exception {
        Matrix<Cell> cells = new Matrix<>(6, new Cell[6][6]);

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                ChildField childField = getChildFieldByIndex(y, x);
                Matrix<Cell> childFieldCells = childField.getCells();
                cells.setValue(x, y, childFieldCells.getValue(x % 3, y % 3));
            }
        }

        return cells;
    }

    public void putBall(int x, int y, Ball ball) throws Exception {
        if (isIndexNotValid(x, y)) {
            throw new Exception("Invalid index");
        }

        ChildField childField = getChildFieldByIndex(x, y);
        childField.putBall(x % 3, y % 3, ball);
    }

    public boolean isCellEmpty(int x, int y) throws Exception {
        if (isIndexNotValid(x, y)) {
            throw new Exception("Invalid index");
        }

        ChildField childField = getChildFieldByIndex(x, y);

        return childField.isCellEmpty(x % 3, y % 3);
    }

    private boolean isIndexNotValid(int x, int y) {
        return x < 0 || x > 5 || y < 0 || y > 5;
    }
}
