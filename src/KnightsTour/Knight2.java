package KnightsTour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Knight2 {
    private Square currentSquare;
    private Square startingSquare;
    private boolean[][] board;
    static Random randy = new Random();

    /**
     * Creates a Knight with board of size rows x columns. DONE
     * Sets the value of board to true in the Square represented DONE
     * by s. Sets all other board values to false. DONE
     * Sets currentSquare and startingSquare to s. DONE
     * @param s the starting Square for this Knight
     * @param rows the number of rows in this Knight's board
     * @param cols the number of columns in this Knight's board
     */
    public Knight2(Square s, int rows, int cols) {
        board = new boolean[rows][cols];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = false;
            }
        }
        startingSquare = s;
        currentSquare = s;
        board[currentSquare.getRow()][currentSquare.getColumn()] = true;

    }

    /**
     * Returns this Knight's current Square.
     * @return this Knight's current Square.
     */
    public Square getCurrentSquare() {
        return currentSquare;

    }

    /**
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
            board[currentSquare.getRow()][currentSquare.getColumn()] = true;
            //sequence.add(currentSquare);
            ArrayList<Square> possible = getPossibleSquares();
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
            }
        } while (pos < board.length*board[0].length);

        return sequence;
    }

    /**
     * Determines if starting Square is reachable from current Square.
     * @return true if starting Square is reachable from current Square, false otherwise
     */
    public boolean startIsReachableFromCurrent() {
        if (startingSquare.getRow() == currentSquare.getRow()-2 && startingSquare.getColumn() == currentSquare.getColumn()-1) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()-1 && startingSquare.getColumn() == currentSquare.getColumn()-2) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()+1 && startingSquare.getColumn() == currentSquare.getColumn()-2) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()+2 && startingSquare.getColumn() == currentSquare.getColumn()-1) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()+2 && startingSquare.getColumn() == currentSquare.getColumn()+1) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()+1 && startingSquare.getColumn() == currentSquare.getColumn()+2) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()-1 && startingSquare.getColumn() == currentSquare.getColumn()+2) {
            return true;
        }
        if (startingSquare.getRow() == currentSquare.getRow()-2 && startingSquare.getColumn() == currentSquare.getColumn()+1) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Returns a Square with the smallest score in possible.
     * If several Squares in possible have the same lowest score,
     * one of these Squares is selected at random and returned.
     * @param possible the list of Squares
     * @return a Square with the smallest score in possible
     */
    public Square getBestSquare(ArrayList<Square> possible) {
        int min = 20;
        for (int k = 0; k < possible.size(); k++) {
            if (possible.get(k).getScore() <= min) {
                min = possible.get(k).getScore();
            }
        }
        ArrayList<Square> offended = new ArrayList();
        for (int j = 0; j < possible.size(); j++) {
            if (possible.get(j).getScore() == min) {
                offended.add(possible.get(j));
            }
        }
        Random randy = new Random();
        int justvar = randy.nextInt(offended.size());
        return offended.get(justvar);
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
        ArrayList<Square> triggered = new ArrayList();
        if (isValid(currentSquare.getRow()-2, currentSquare.getColumn()-1) == true && board[currentSquare.getRow()-2][currentSquare.getColumn()-1] == false) {
            triggered.add(new Square(currentSquare.getRow()-2, currentSquare.getColumn()-1, getScoreOfSquare(currentSquare.getRow()-2, currentSquare.getColumn()-1)));
        }
        if (isValid(currentSquare.getRow()-1, currentSquare.getColumn()-2) == true && board[currentSquare.getRow()-1][currentSquare.getColumn()-2] == false) {
            triggered.add(new Square(currentSquare.getRow()-1, currentSquare.getColumn()-2, getScoreOfSquare(currentSquare.getRow()-1, currentSquare.getColumn()-2)));
        }
        if (isValid(currentSquare.getRow()+1, currentSquare.getColumn()-2) == true && board[currentSquare.getRow()+1][currentSquare.getColumn()-2] == false) {
            triggered.add(new Square(currentSquare.getRow()+1, currentSquare.getColumn()-2, getScoreOfSquare(currentSquare.getRow()+1, currentSquare.getColumn()-2)));
        }
        if (isValid(currentSquare.getRow()+2, currentSquare.getColumn()-1) == true && board[currentSquare.getRow()+2][currentSquare.getColumn()-1] == false) {
            triggered.add(new Square(currentSquare.getRow()+2, currentSquare.getColumn()-1, getScoreOfSquare(currentSquare.getRow()+2, currentSquare.getColumn()-1)));
        }
        if (isValid(currentSquare.getRow()+2, currentSquare.getColumn()+1) == true && board[currentSquare.getRow()+2][currentSquare.getColumn()+1] == false) {
            triggered.add(new Square(currentSquare.getRow()+2, currentSquare.getColumn()+1, getScoreOfSquare(currentSquare.getRow()+2, currentSquare.getColumn()+1)));
        }
        if (isValid(currentSquare.getRow()+1, currentSquare.getColumn()+2) == true && board[currentSquare.getRow()+1][currentSquare.getColumn()+2] == false) {
            triggered.add(new Square(currentSquare.getRow()+1, currentSquare.getColumn()+2, getScoreOfSquare(currentSquare.getRow()+1, currentSquare.getColumn()+2)));
        }
        if (isValid(currentSquare.getRow()-1, currentSquare.getColumn()+2) == true && board[currentSquare.getRow()-1][currentSquare.getColumn()+2] == false) {
            triggered.add(new Square(currentSquare.getRow()-1, currentSquare.getColumn()+2, getScoreOfSquare(currentSquare.getRow()-1, currentSquare.getColumn()+2)));
        }
        if (isValid(currentSquare.getRow()-2, currentSquare.getColumn()+1) == true && board[currentSquare.getRow()-2][currentSquare.getColumn()+1] == false) {
            triggered.add(new Square(currentSquare.getRow()-2, currentSquare.getColumn()+1, getScoreOfSquare(currentSquare.getRow()-2, currentSquare.getColumn()+1)));
        }
        return triggered;
    }

    /**
     * Returns the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col.
     * @param row the row
     * @param col the column
     * @return the number of unvisited Squares that can be reached (with a knight move) from the Square at row, col
     */
    public int getScoreOfSquare(int row, int col) {
        int count = 0;
        if (isValid(row-2, col-1) == true && board[row-2][col-1] == false) {
            count++;
        }
        if (isValid(row-1, col-2) == true && board[row-1][col-2] == false) {
            count++;
        }
        if (isValid(row+1, col-2) == true && board[row+1][col-2] == false) {
            count++;
        }
        if (isValid(row+2, col-1) == true && board[row+2][col-1] == false) {
            count++;
        }
        if (isValid(row+2, col+1) == true && board[row+2][col+1] == false) {
            count++;
        }
        if (isValid(row+1, col+2) == true && board[row+1][col+2] == false) {
            count++;
        }
        if (isValid(row-1, col+2) == true && board[row-1][col+2] == false) {
            count++;
        }
        if (isValid(row-2, col+1) == true && board[row-2][col+1] == false) {
            count++;
        }
        return count;
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
        }
        else {
            return false;
        }
    }
}
