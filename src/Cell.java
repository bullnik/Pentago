public class Cell {
    private CellState state;
    private Ball ball;

    public Cell() {
        state = CellState.Empty;
        ball = null;
    }

    public CellState getState() {
        return state;
    }

    public Ball getBall() throws Exception {
        if (ball == null) {
            throw new Exception("There is no ball in the cell");
        }

        return ball;
    }

    public void putBall(Ball ball) {
        this.ball = ball;
        state = CellState.NotEmpty;
    }
}
