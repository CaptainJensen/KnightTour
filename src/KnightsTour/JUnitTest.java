package KnightsTour;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class KnightTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testKnight() {
        Knight rider = new Knight(new Square(2,3,0),8,8);
        assertEquals(8,rider.getBoard().length);
        assertEquals(8,rider.getBoard()[0].length);
        assertEquals(3,rider.getStartingSquare().getColumn());
        assertEquals(2,rider.getStartingSquare().getRow());
        assertEquals(3,rider.getCurrentSquare().getColumn());
        assertEquals(2,rider.getCurrentSquare().getRow());
        for (int r = 0; r < rider.getBoard().length; r++) {
            for (int c = 0; c < rider.getBoard()[r].length; c++) {
                if (r != rider.getCurrentSquare().getRow() || c != rider.getCurrentSquare().getColumn()) {
                    assertFalse(rider.getBoard()[r][c]);
                }
            }
        }
        assertTrue(rider.getBoard()[rider.getCurrentSquare().getRow()][rider.getCurrentSquare().getColumn()]);
    }

    @Test
    public void testStartIsReachableFromCurrent() {

    }

    @Test
    public void testGetBestSquare() {
        Knight lancelot = new Knight(new Square(0,0,0),8,8);
        ArrayList<Square> possible = lancelot.getPossibleSquares();
        Square sq = lancelot.getBestSquare(possible);
        assertTrue(sq.equals(new Square(1,2,0)) || sq.equals(new Square(2,1,0)));

        Knight mildred = new Knight(new Square(4,4,0),8,8);
        possible = mildred.getPossibleSquares();
        sq = mildred.getBestSquare(possible);
        assertTrue(sq.equals(new Square(3,6,0)) || sq.equals(new Square(5,6,0)) ||
                sq.equals(new Square(6,3,0)) || sq.equals(new Square(6,5,0)));
    }

    @Test
    public void testClearBoard() {
    }

    @Test
    public void testGetPossibleSquares() {
        Knight lancelot = new Knight(new Square(0,0,0),8,8);
        ArrayList<Square> possible = lancelot.getPossibleSquares();
        assertEquals(2,possible.size());
        for (Square sq: possible) {
            assertEquals(5,sq.getScore());
        }
    }

    @Test
    public void testGetScoreOfSquare() {
    }

    @Test
    public void testIsValid() {
        Knight lancelot = new Knight(new Square(0,0,0),8,8);
        assertFalse(lancelot.isValid(-1, 1));
        assertFalse(lancelot.isValid(1, -1));
        assertFalse(lancelot.isValid(1, 8));
        assertFalse(lancelot.isValid(8, 1));

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                assertTrue(lancelot.isValid(r, c));
            }
        }
    }

}
