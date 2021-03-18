public class ChildField {
    private Matrix<Cell> cells;

    public ChildField() throws Exception {
        cells = new Matrix<>(3, new Cell[][] {
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() }
        });
    }

    public void putBall(int x, int y, Ball ball) throws Exception {
        if (isIndexNotValid(x, y)) {
            throw new Exception("Invalid index");
        }

        Cell cell = cells.getValue(y, x);

        if (cell.getState() == CellState.Empty) {
            cell.putBall(ball);
        } else {
            throw new Exception("Can not put ball - cell is not empty");
        }
    }

    public boolean isCellEmpty(int x, int y) throws Exception {
        if (isIndexNotValid(x, y)) {
            throw new Exception("Invalid index");
        }

        Cell cell = cells.getValue(y, x);

        return cell.getState() == CellState.Empty;
    }

    private boolean isIndexNotValid(int x, int y) {
        return x < 0 || x > 2 || y < 0 || y > 2;
    }

    public void rotate(RotateDirection direction) throws Exception {
        switch (direction) {
            case Right:
                cells = RotateRight(cells);
                break;
            case Left:
                cells = RotateLeft(cells);
                break;
            default:
                throw new Exception("Not Implemented");
        }
    }

    private Matrix<Cell> RotateRight(Matrix<Cell> cells) throws Exception {
        Matrix<Cell> afterRotate = new Matrix<>(3, new Cell[][] {
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() }
        });

        afterRotate.setValue(0,2, cells.getValue(0, 0));
        afterRotate.setValue(0,1, cells.getValue(1, 0));
        afterRotate.setValue(0,0, cells.getValue(2, 0));

        afterRotate.setValue(1,2, cells.getValue(0, 1));
        afterRotate.setValue(1,1, cells.getValue(1,  1));
        afterRotate.setValue(1,0, cells.getValue(2, 1));

        afterRotate.setValue(2,2, cells.getValue(0, 2));
        afterRotate.setValue(2,1, cells.getValue(1, 2));
        afterRotate.setValue(2,0, cells.getValue(2,  2));

        return afterRotate;
    }

    private Matrix<Cell> RotateLeft(Matrix<Cell> cells) throws Exception {
        Matrix<Cell> afterRotate = new Matrix<>(3, new Cell[][] {
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() },
                new Cell[] { new Cell(), new Cell(), new Cell() }
        });

        afterRotate.setValue(0,0, cells.getValue(0, 2));
        afterRotate.setValue(1,0, cells.getValue(0, 1));
        afterRotate.setValue(2,0, cells.getValue(0, 0));

        afterRotate.setValue(0,1, cells.getValue(1, 2));
        afterRotate.setValue(1,1, cells.getValue(1, 1));
        afterRotate.setValue(2,1, cells.getValue(1, 0));

        afterRotate.setValue(0,2, cells.getValue(2, 2));
        afterRotate.setValue(1,2, cells.getValue(2, 1));
        afterRotate.setValue(2,2, cells.getValue(2, 0));

        return afterRotate;
    }

    public Matrix<Cell> getCells() {
        return cells;
    }
}