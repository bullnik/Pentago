import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        startGame();
    }

    public static void startGame() throws Exception {
        Game game = new Game(new Player[] {
                new Player(BallColor.Black),
                new Player(BallColor.White)
        });

        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            printLines();
            print(game.getField());

            System.out.println();
            System.out.println("Insert coordinates of ball (x, y):");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            while (!game.canPutBall(x, y)) {
                System.out.println("Cannot put ball to this coordinates, retry (x, y):");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }
            game.putBall(x, y);

            if (game.isGameOver()) continue;

            printLines();
            print(game.getField());

            System.out.println();
            System.out.println("Change the field to rotate (1 - 4)");
            x = scanner.nextInt();
            while (!(x >= 1 && x <= 4)) {
                System.out.println("Incorrect field coordinates, retry (1 - 4):");
                x = scanner.nextInt();
            }
            System.out.println("Change rotation (left, right):");
            String str = scanner.next();
            while (!(str.equals("left") || str.equals("right"))) {
                System.out.println("Incorrect rotation, retry (left, right):");
                str = scanner.next();
            }
            RotateDirection dir = str.equals("left") ? RotateDirection.Left : RotateDirection.Right;
            game.rotateField(
                    (x == 1 || x == 3) ? 0 : 1,
                    (x == 1 || x == 2) ? 0 : 1,
                    dir);
        }

        printLines();
        print(game.getField());
        System.out.println();
        System.out.println("Game over.");
    }

    public static void printLines() {
        for (int i = 0; i < 1; i++) {
            System.out.println();
        }
    }

    public static void print(char[][] arr) {
        System.out.print("  ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i);
            System.out.print(' ');
        }
        System.out.println(" ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i);
            System.out.print(" ");
            char[] ar = arr[i];
            for (char ch : ar) {
                System.out.print(ch);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
