package KnightsTour;




import java.util.ArrayList;
import java.util.Random;

public class Knight {
    private Square currentSquare;
    private Square startingSquare;
    private boolean[][] board;
    static Random random = new Random();

    /**
     * Creates a Knight with board of size rows x columns.
     * Sets the value of board to true in the Square represented
     * by s. Sets all other board values to false.
     * Sets currentSquare and startingSquare to s.
     * @param s the starting Square for this Knight
     * @param rows the number of rows in this Knight's board
     * @param cols the number of columns in this Knight's board
     */
    public Knight(Square s, int rows, int cols) {
        board = new boolean[rows][cols];
        this.startingSquare = s;
        currentSquare = s;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = false;
            }
        }
        board[this.startingSquare.getRow()][this.startingSquare.getColumn()] = true;
    }

    /**
     * Returns this Knight's current Square.
     * @return this Knight's current Square.
     */
    public Square getCurrentSquare() {
        return currentSquare;
    }

    /**
     *
     * Returns this Knight's starting Square.
     * @return this Knight's starting Square.
     */
    public Square getStartingSquare() {
        return startingSquare;
    }

    /**
     * Returns this Knight's board.
     * @return this Knight's board.
     */
    public boolean[][] getBoard() {
        return board;
    }

    /**
     * Returns a list of Squares representing a Knights Tour for this Knight.
     * @return a list of Squares representing a Knights Tour for this Knight
     */
    public ArrayList<Square> solve() {
        ArrayList<Square> sequence = new ArrayList<Square>();

        int pos = 1;
        sequence.add(currentSquare);
        do {
            //System.out.println("Current Loc : " + currentSquare.getRow() + currentSquare.getColumn());
            board[currentSquare.getRow()][currentSquare.getColumn()] = true;
            //sequence.add(currentSquare);
            ArrayList<Square> possible = getPossibleSquares();
            //System.out.println("Possible places: " + possible.toString());
            if (possible.isEmpty()) {
                sequence.clear();
                sequence.add(startingSquare);
                pos = 1;
                currentSquare = startingSquare;
                clearBoard();
            }
            else {
                Square best = getBestSquare(possible);
                sequence.add(best);
                currentSquare = best;
                pos++;
                System.out.println(pos);
            }
        } while (pos < board.length*board[0].length);

        return sequence;
    }



    /**
     * Determines if starting Square is reachable from current Square.
     * @return true if starting Square is reachable from current Square, false otherwise
     */
    public boolean startIsReachableFromCurrent() {

        //FOR Closed Knights Tour

        return false;

    }

    /**
     * Returns a Square with the smallest score in possible.
     * If several Squares in possible have the same lowest score,
     * one of these Squares is selected at random and returned.
     * @param possible the list of Squares
     * @return a Square with the smallest score in possible
     */
    public Square getBestSquare(ArrayList<Square> possible) {
        ArrayList<Square> bestList = new ArrayList<Square>();


        int min = Integer.MAX_VALUE;
        for (Square aPossible : possible) {
            if (aPossible.getScore() <= min) {
                min = aPossible.getScore();
            }
        }
        for (Square bPossible : possible) {
            if (bPossible.getScore() == min) {
                bestList.add(bPossible);
            }
        }
        Random random2 = new Random();
        return bestList.get(random2.nextInt(bestList.size()));

    }

    /**
     * Sets all values in this Knight's board to false.
     */
    public void clearBoard() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = false;
            }
        }
    }

    /**
     * Returns a list of all Squares that are within one knight move of
     * this Knight's current Square.
     * Each Square in the returned list has been given a score representing
     * the number of unvisited Squares that can be reached (with a knight move) from that Square.
     * @return a list of all Squares that are within one knight move of
     * this Knight's current Square
     */
    public ArrayList<Square> getPossibleSquares() {


        ArrayList<Square> possible = new ArrayList();
        int r = currentSquare.getRow();
        int c = currentSquare.getColumn();


        if (isValid(r-2, c-1) && !board[r-2][c-1]) {
            possible.add(new Square(r-2, c-1, getScoreOfSquare(r-2, c-1)));
        }
        if (isValid(r-1, c-2) && !board[r-1][c-2]) {
            possible.add(new Square(r-1, c-2, getScoreOfSquare(r-1, c-2)));
        }
        if (isValid(r+1, c-2) && !board[r+1][c-2]) {
            possible.add(new Square(r+1, c-2, getScoreOfSquare(r+1, c-2)));
        }
        if (isValid(r+2, c-1) && !board[r+2][c-1]) {
            possible.add(new Square(r+2, c-1, getScoreOfSquare(r+2, c-1)));
        }
        if (isValid(r+2, c+1) && !board[r+2][c+1]) {
            possible.add(new Square(r+2, c+1, getScoreOfSquare(r+2, c+1)));
        }
        if (isValid(r+1, c+2) && !board[r+1][c+2]) {
            possible.add(new Square(r+1, c+2, getScoreOfSquare(r+1, c+2)));
        }
        if (isValid(r-1, c+2) && !board[r-1][c+2]) {
            possible.add(new Square(r-1, c+2, getScoreOfSquare(r-1, c+2)));
        }
        if (isValid(r-2, c+1) && !board[r-2][c+1]) {
            possible.add(new Square(r-2, c+1, getScoreOfSquare(r-2, c+1)));
        }
        return possible;

    }

    /**
     * Returns the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col.
     * @param row the row
     * @param col the column
     * @return the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col
     */
    public int getScoreOfSquare(int row, int col) {

//        int ScoreOfSquare = 0;
//        for (int r = 0; r < board.length; r++) {
//            for (int c = 0; c < board[r].length; c++) {
//                if(((r == row+2 || r == row-2) && (c == col-1 || c == col+1)) && !board[r][c]){
//                    ScoreOfSquare++;
//                }
//                if(((r == row+1 || r == row-1) && (c == col-2 || c == col+2)) && !board[r][c]) {
//                    ScoreOfSquare++;
//                }
//            }
//        }
        int count = 0;
        if (isValid(row-2, col-1) && !board[row-2][col-1]) {
            count++;
        }
        if (isValid(row-1, col-2) && !board[row-1][col-2]) {
            count++;
        }
        if (isValid(row+1, col-2) && !board[row+1][col-2]) {
            count++;
        }
        if (isValid(row+2, col-1) && !board[row+2][col-1]) {
            count++;
        }
        if (isValid(row+2, col+1) && !board[row+2][col+1]) {
            count++;
        }
        if (isValid(row+1, col+2) && !board[row+1][col+2]) {
            count++;
        }
        if (isValid(row-1, col+2) && !board[row-1][col+2]) {
            count++;
        }
        if (isValid(row-2, col+1) && !board[row-2][col+1]) {
            count++;
        }
        return count;

        //return ScoreOfSquare;




    }

    /**
     * Returns true if the square at row r, column c is in this Knight's board; returns false otherwise.
     * @param r the row
     * @param c the column
     * @return true if the square at row r, column c is in this Knight's board; false otherwise
     */
    public boolean isValid(int r, int c) {
        if (r >= 0 && r < board.length && c >= 0 && c < board[r].length) {
            return true;
        } else return false;

    }
}