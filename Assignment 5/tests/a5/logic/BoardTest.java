package a5.logic;

import static org.junit.jupiter.api.Assertions.*;

import a5.util.PlayerRole;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testEquals() {
        Board board1 = new Board(3, 3);
        Board board2 = new Board(3, 3);
        Board board3 = new Board(3, 4);
        Board board4 = new Board(4, 3);

        // test 1: empty boards should be equal
        assertEquals(board1, board2);

        // test 2: adding a piece should break equality
        board2.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board1, board2);

        // test 3: empty boards should not be equal
        assertNotEquals(board3, board4);

        // test 4: adding a piece should maintain the equality
        board1.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        board2.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        board1.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertEquals(board1, board2);

        // test 5: adding a piece to different boards should maintain inequality
        board3.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        board4.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board3, board4);
        // TODO 1: write at least 3 test cases
    }

    @Test
    void testHashCode() {
        Board board1 = new Board(3, 3);
        Board board2 = new Board(3, 3);
        Board board3 = new Board(3, 4);
        Board board4 = new Board(4, 3);

        // test 1: equal boards should have the same hash code
        assertEquals(board1.hashCode(), board2.hashCode());

        // test 2: unequal boards should be very unlikely to have the same hash code
        board2.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board1.hashCode(), board2.hashCode());

        // test 3: unequal empty boards should be very unlikely to have the same hash code
        assertNotEquals(board2.hashCode(), board3.hashCode());

        // test 4: equal boards with same piece position should have the same hash code
        board1.place(new Position(0, 0), PlayerRole.FIRST_PLAYER);
        board1.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        board2.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        assertEquals(board1.hashCode(), board2.hashCode());

        // test 5: unequal boards with same piece position should be very unlikely to have
        // the same hash code
        board3.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        board4.place(new Position(1, 2), PlayerRole.FIRST_PLAYER);
        assertNotEquals(board3.hashCode(), board4.hashCode());
        // TODO 2: write at least 3 test cases
    }

    @Test
    void get() {
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        // test 1: Check that an empty space reports 0
        assertEquals(0, board.get(p));

        // test 2: Check that a placed piece is seen by get()
        board.place(p, PlayerRole.FIRST_PLAYER);
        assertEquals(PlayerRole.FIRST_PLAYER.boardValue(), board.get(p));
    }

    @Test
    void place() {
        // test 1: do placed pieces show up where they are placed?
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        assertEquals(0, board.get(p));
        board.place(p, PlayerRole.SECOND_PLAYER);
        assertEquals(PlayerRole.SECOND_PLAYER.boardValue(), board.get(p));
    }

    @Test
    void erase() {
        Board board = new Board(5, 5);
        Position p = new Position(0, 0);
        board.place(p, PlayerRole.SECOND_PLAYER);

        // test 1: do pieces get erased?
        board.erase(p);
        assertEquals(0, board.get(p));
    }

    @Test
    void rowSize() {
        Board board = new Board(5, 6);
        assertEquals(5, board.rowSize());
    }

    @Test
    void colSize() {
        Board board = new Board(5, 6);
        assertEquals(6, board.colSize());
    }

    @Test
    void validPos() {
        Board board = new Board(5, 6);
        Position p = new Position(3, 3);
        // test 1: is a valid position valid?
        assertTrue(board.validPos(p));
        board.place(p, PlayerRole.FIRST_PLAYER);

        // test 2: is an invalid position invalid?
        p = new Position(5, 5);
        assertFalse(board.validPos(p));
    }

    @Test
    void onBoard() {
        Board board = new Board(5, 6);
        Position p = new Position(3, 3);
        // test 1: is a valid empty position on board?
        assertTrue(board.onBoard(p));
        // test 2: is a valid nonempty position on board?
        board.place(p, PlayerRole.FIRST_PLAYER);
        assertTrue(board.onBoard(p));
        // test 3: is an invalid position on board?
        p = new Position(5, 5);
        assertFalse(board.onBoard(p));
    }
}