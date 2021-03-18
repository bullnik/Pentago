public class Player {
    private final BallColor ballColor;
    public boolean isWin;

    public Player(BallColor ballColor) {
        this.ballColor = ballColor;
        isWin = false;
    }

    public BallColor getBallColor() {
        return ballColor;
    }
}
