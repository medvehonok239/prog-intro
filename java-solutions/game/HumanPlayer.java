package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        while(true){
            try {
                System.out.println();
                System.out.println("Current position");
                System.out.println(position);
                System.out.println("Enter your move to position " + position.getTurn());
                return new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
            } catch (InputMismatchException e){
                System.out.println("U did something wrong : " + e);
                in = new Scanner(System.in);
            }
        }
    }
}
