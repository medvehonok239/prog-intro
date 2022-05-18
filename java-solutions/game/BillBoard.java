package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author : medvezhonokok
 * @mailto : nocap239@gmail.com
 * @created : 10.12.2021, воксресенье
 **/

public class BillBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "O"
    );
    private final int n, m, k;
    private final Cell[][] field;
    private Cell turn;

    public BillBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        field = new Cell[n][m];
        for (Cell[] cells : field) {
            Arrays.fill(cells, Cell.E);
        }
        this.turn = Cell.X;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        field[move.getRow()][move.getCol()] = move.getValue();

        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        
        return count == 0;
    }

    private boolean checkWin(Move move) {
        boolean winCol = checkCol(move);
        boolean winRow = checkRow(move);
        boolean winDig = checkDig(move);

        return winCol || winRow || winDig;
    }

    private boolean checkDig(Move move) {
        int count = 0;

        int x = move.getRow();
        int y = move.getCol();

        int i = x;
        int j = y;
        
        while ((i < n) && (j < m)) {
            if (field[i++][j++] == turn) {
                count++;
            } else break;

            if (count == k) {
                return true;
            }
        }

        i = x;
        j = y;
        
        while (i > 0 && j > 0) {
            if (field[--i][--j] == turn) {
                count++;
            } else break;
            if (count == k) {
                return true;
            }
        }

        if (count >= k) {
            return true;
        }

        count = 0;

        i = x;
        j = y;

        while (i < n && j >= 0) {
            if (field[i++][j--] == turn) {
                count++;
            } else break;

            if (count == k) {
                return true;
            }
        }
        
        i = x;
        j = y;

        while (i > 0 && j < m - 1) {
            if (field[--i][++j] == turn) {
                count++;
            } else break;
            if (count == k) {
                return true;
            }
        }

        return false;
    }

    private boolean checkRow(Move move) {
        int winC = 0;
        for (int i = 0; i < m; i++) {
            if (field[move.getRow()][i] == turn) {
                winC++;
            } else winC = 0;
            if (winC == k) {
                return true;
            }
        }
        
        return false;
    }

    private boolean checkCol(Move move) {
        int winC = 0;
        for (int i = 0; i < n; i++) {
            if (field[i][move.getCol()] == turn) {
                winC++;
            } else winC = 0;
            if (winC == k) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 1; i <= m; i++) {
            sb.append(i);
        }
        
        sb.append(System.lineSeparator());

        for (int r = 0; r < n; r++) {
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    public void clear(Cell turn) {
        for (Cell[] arrayCell : field) {
            Arrays.fill(arrayCell, Cell.E);
        }
        this.turn = turn == Cell.X ? Cell.O : Cell.X;
    }
}
