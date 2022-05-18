package game;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static int getNum() {
        Scanner in = new Scanner(System.in);
        String str;
        int n;
        while (true) {
            try {
                str = in.nextLine();
                n = Integer.parseInt(str);
                if (n < 1) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("U did something wrong. Please, try again :");
            } catch (NoSuchElementException e1){
                System.out.println("Congratulations! Вы ввели что-то не понятное, поэтому вы проиграли!");
                System.exit(0);
                //break;

            }

        }

        return n;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Chose your 1st player : Human, Sequential, Random");

        Player player1 = getPlayer(in.nextLine(), in);

        System.out.println("Chose your 2nd player : Human, Sequential, Random");

        Player player2 = getPlayer(in.nextLine(), in);

        System.out.println("Enter your height :");

        int n = getNum();

        System.out.println("Enter your length");
        int m = getNum();

        System.out.println("Count of symbols in row");
        int k = getNum();

        System.out.println("Count of wins");
        int x = getNum();

        final int result = new TwoPlayerGame(
                new BillBoard(n, m, k),
                player1, player2
                /*new HumanPlayer(new Scanner(System.in)),
                new HumanPlayer(new Scanner(System.in))*/
        ).play1(false, x);
        switch (result) {
            case 1:
                System.out.println();
                System.out.println("1st player won");
                break;
            case 2:
                System.out.println();
                System.out.println("2nd player won");
                break;
            case 0:
                System.out.println();
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result" + result);
        }

    }

    private static Player getPlayer(String str, Scanner in) {
        Player player = null;
        switch (str.toLowerCase(Locale.ROOT)) {
            case "human":
                player = new HumanPlayer(in);
                break;
            case "sequential":
                player = new SequentialPlayer();
                break;
            case "random":
                player = new RandomPlayer();
                break;
            default:
                break;
        }
        return player;
    }

}


