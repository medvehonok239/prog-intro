package game;

public interface Position {

    Cell getTurn();

    boolean isValid(Move move);

    int getN();
    int getM();

}
