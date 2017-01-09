package KnightsTour;




import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Knight {
    private Square currentSquare;
    private Square startingSquare;
    private boolean[][] board;
    Random random = new Random();

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
        boolean[][] PREboard = new boolean[rows][cols];
        board = PREboard;
        this.startingSquare = s;
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

        //do something to find a reachable
        if(this.getCurrentSquare()) {
            return true;
        } else return false;
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
//        bestList.add(possible.get(0));
        for (int r = 0; r < possible.size()-1; r++) {

            if(possible.get(r).getScore() < bestList.get(r).getScore()) {
                bestList.add(possible.get(r));
            }
            //TODO: Add coditional to if ==

        }
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

        ArrayList<Square> possible = new ArrayList<Square>();

        //TODO: also add check if possible location
        //CHECK IF VALID

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

                Square questionSquare = new Square(r, c, getScoreOfSquare(r, c));
                if(questionSquare.getRow() == getCurrentSquare().getRow()-2 && questionSquare.getColumn() == getCurrentSquare().getColumn()+1) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()-1 && questionSquare.getColumn() ==  getCurrentSquare().getColumn()+2) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()+1 && questionSquare.getColumn() == getCurrentSquare().getColumn()+2) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()+2 && questionSquare.getColumn() == getCurrentSquare().getColumn()+1) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()+2 && questionSquare.getColumn() == getCurrentSquare().getColumn()-1) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()+1 && questionSquare.getColumn() == getCurrentSquare().getColumn()-2) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()+1 && questionSquare.getColumn() == getCurrentSquare().getColumn()-2) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()-1 && questionSquare.getColumn() == getCurrentSquare().getColumn()-2) {
                    possible.add(questionSquare);
                }
                else if (questionSquare.getRow() == getCurrentSquare().getRow()-2 && questionSquare.getColumn() == getCurrentSquare().getColumn()-1) {
                    possible.add(questionSquare);
                }
            }
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

        int ScoreOfSquare = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if(((r == row+2 || r == row-2) && (c == col-1 || c == col+1)) && !board[r][c]){
                    ScoreOfSquare++;
                }
                if(((r == row+1 || r == row-1) && (c == col-2 || c == col+2)) && !board[r][c]) {
                    ScoreOfSquare++;
                }
            }
        }

        return ScoreOfSquare;




    }

    /**
     * Returns true if the square at row r, column c is in this Knight's board; returns false otherwise.
     * @param r the row
     * @param c the column
     * @return true if the square at row r, column c is in this Knight's board; false otherwise
     */
    public boolean isValid(int r, int c) {





    }
}