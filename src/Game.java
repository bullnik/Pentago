public class Game {
    private final Field field;
    private final FieldView fieldView;
    private final Player[] players;
    private int currentPlayer;
    private boolean isGameOver;
    private int currentMove;

    public Game(Player[] players) throws Exception {
        if (players.length < 2) {
            throw new Exception("Too few players");
        }

        field = new Field();
        fieldView = new FieldView(field);
        this.players = players;
        isGameOver = false;
        currentPlayer = 0;
        currentMove = 0;
    }

    private void nextPlayer() {
        currentPlayer++;
        currentMove = 0;
        if (currentPlayer == players.length) {
            currentPlayer = 0;
        }
    }

    public char[][] getField() throws Exception {
        return fieldView.fieldToCharArray();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public void checkGaveOver() throws Exception {
        for (Player player : players) {
            player.isWin = field.getMaxLineLength(player.getBallColor()) >= 5;
            if (player.isWin) isGameOver = true;
        }
        if (field.isFull()) isGameOver = true;
    }

    public void putBall(int x, int y) throws Exception {
        if (currentMove != 0) {
            throw new Exception("Prohibited by the game rules");
        }
        if (isGameOver) {
            throw new Exception("Game over");
        }
        currentMove++;
        field.putBall(x, y, new Ball(players[currentPlayer].getBallColor()));
        checkGaveOver();
    }

    public void rotateField(int x, int y, RotateDirection rotateDirection) throws Exception {
        if (currentMove != 1) {
            throw new Exception("Prohibited by the game rules");
        }
        if (isGameOver) {
            throw new Exception("Game over");
        }
        field.rotateField(x, y, rotateDirection);
        checkGaveOver();
        nextPlayer();
    }

    public boolean canPutBall(int x, int y) throws Exception {
        return x >= 0 && x <= 5 && y >= 0 && y <= 5 && field.isCellEmpty(x, y);
    }
}
