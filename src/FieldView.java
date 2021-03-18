public class FieldView {
    private final Field field;

    public FieldView(Field field) {
        this.field = field;
    }

    public char[][] fieldToCharArray() throws Exception {
        Matrix<Cell> cells = field.getCells();
        char[][] output = new char[6][6];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if (cells.getValue(i, j).getState() == CellState.Empty) {
                    output[i][j] = '-';
                } else {
                    switch (cells.getValue(i, j).getBall().getColor()) {
                        case Black:
                            output[i][j] = 'B';
                            break;
                        case White:
                            output[i][j] = 'W';
                            break;
                    }
                }
            }
        }

        return output;
    }
}
