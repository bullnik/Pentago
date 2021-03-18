import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game(new Player[] {
                        new Player(BallColor.Black),
                        new Player(BallColor.White)
        });

        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOver()) {
            printLines();
            print(game.getField());

            System.out.println("Insert coordinates of ball (x, y):");
            int x = scanner.nextInt();;
            int y = scanner.nextInt();
            while (!game.canPutBall(x, y)) {
                System.out.println("Cannot put ball to this coordinates, retry (x, y):");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }
            game.putBall(y, x);

            if (game.isGameOver()) continue;

            printLines();
            print(game.getField());

            System.out.println("Change the field to rotate (x, y)");
            x = scanner.nextInt();
            y = scanner.nextInt();
            while (!(x >= 0 && x <= 1 && y >= 0 && y <= 1)) {
                System.out.println("Incorrect field coordinates, retry (x, y):");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }
            String str = scanner.next();
            while (!(str.equals("left") || str.equals("right"))) {
                System.out.println("Incorrect field coordinates, retry (x, y):");
                str = scanner.next();
            }
            RotateDirection dir = str.equals("left") ? RotateDirection.Left : RotateDirection.Right;
            game.rotateField(y, x, dir);
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
        for (char[] ar : arr) {
            for (char ch : ar) {
                System.out.print(ch);
            }
            System.out.println();
        }
    }
}
