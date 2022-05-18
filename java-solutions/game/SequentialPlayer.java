package game;

public class SequentialPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
        for (int c = 0; c < 3; c++) {
            final Move move = new Move(0, c, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
        for (int c = 0; c < 3; c++) {
            final Move move = new Move(1, c, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
        for (int c = 0; c < 3; c++) {
            final Move move = new Move(2, c, position.getTurn());
            if (position.isValid(move)) {
                return move;
            }
        }
        throw new AssertionError("No valid moves ");
    }
}
