package a5.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import a5.util.PlayerRole;
import org.junit.jupiter.api.Test;

class PenteTest {
    @Test
    void testConstructor() {
        // TODO 1: write at least 1 test case
        Pente game1 = new Pente();
        game1.makeMove(new Position(3, 7));
        Pente game2 = new Pente();
        game2.makeMove(new Position(4, 5));
    }

    @Test
    void testCopyConstructor() {
        // test case 1: can a board state be copied to an equal state
        Pente game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        Pente game2 = new Pente(game1);
        assertTrue(game1.stateEqual(game2));
        Pente game3 = new Pente(game2);
        assertTrue(game1.stateEqual(game3));
        assertTrue(game2.stateEqual(game3));
        game2.makeMove(new Position(0,0));
        Pente game4 = new Pente(game2);
        assertFalse(game2.stateEqual(game3));
        assertTrue(game2.stateEqual(game4));
        game2.makeMove(new Position(1,0));
        Pente game5 = new Pente(game2);
        game5.makeMove(new Position(2,0));
        assertFalse(game2.stateEqual(game5));
        // TODO 2: write at least 3 test cases
    }

    @Test
    void testHashCode() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();
        Pente game4 = new Pente();
        Pente game5 = new Pente();
        Pente game6 = new Pente();
        Pente game7 = new Pente();

        // test case 1: do two equal nonempty board states have the same hash code
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertEquals(game1.hashCode(), game2.hashCode());

        // test case 2: non-equal board states should be very unlikely to have the
        // same hash code.
        game3.makeMove(new Position(0, 0));
        assertNotEquals(game1.hashCode(), game3.hashCode());

        // test case 3: equal board with different positions should be very unlikely
        // to have the same hash code.
        game4.makeMove(new Position(4, 3));
        game5.makeMove(new Position(3, 4));
        assertNotEquals(game4.hashCode(), game5.hashCode());

        // test case 4: do two equal empty board states have the same hash code
        assertEquals(game6.hashCode(), game7.hashCode());

        // test case 5: two completely different board states should be very unlikely
        // to have the same hash code.
        assertNotEquals(game1.hashCode(), game6.hashCode());
        // TODO 3: write at least 3 test cases
    }

    @Test
    void makeMove() {
        // test case 1: a simple move
        Pente game = new Pente();
        Position p = new Position(2, 2);
        game.makeMove(p); // a move by the first player
        assertEquals(PlayerRole.SECOND_PLAYER, game.currentPlayer());
        assertEquals(2, game.currentTurn());
        assertFalse(game.hasEnded());
        assertEquals(0, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.capturedPairsNo(PlayerRole.SECOND_PLAYER));
        assertEquals(PlayerRole.FIRST_PLAYER.boardValue(), game.board().get(p));

        // test case 2: try a capture
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(2, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(1, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game.board().get(new Position(2, 3))); // the stone should be removed
        assertEquals(0, game.board().get(new Position(2, 4))); // the stone should be removed

        // test case 3: try a general case
        Pente game1 = new Pente();
        game1.makeMove(new Position(2, 3)); // a move by the first player
        game1.makeMove(new Position(3, 2)); // a move by the second player
        game1.makeMove(new Position(2, 4)); // a move by the first player
        game1.makeMove(new Position(2, 5)); // a move by the second player
        game1.makeMove(new Position(2, 2)); // a move by the first player
        game1.makeMove(new Position(2, 1)); // a move by the second player
        assertEquals(game1.capturedPairsNo(PlayerRole.FIRST_PLAYER), 2);
        assertEquals(game1.capturedPairsNo(PlayerRole.SECOND_PLAYER), 1);
        assertFalse(game1.hasEnded());

        // test case 4: try a corner case
        Pente game2 = new Pente();
        Position p1 = new Position(0, 0);
        game2.makeMove(p1); // a move by the first player
        Position p2 = new Position(3, 7);
        game2.makeMove(p2); // a move by the second player
        assertEquals(game2.currentPlayer(), PlayerRole.FIRST_PLAYER);
        game2.makeMove(new Position(1, 1)); // a move by the first player
        game2.makeMove(new Position(3, 2)); // a move by the second player
        assertFalse(game2.hasEnded());
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);

        // test case 5: try a remove case
        Pente game3 = new Pente();
        assertEquals(game3.currentPlayer(), PlayerRole.FIRST_PLAYER);
        game3.makeMove(new Position(1, 1)); // a move by the first player
        game3.makeMove(new Position(3, 2)); // a move by the second player
        assertFalse(game3.hasEnded());
        game3.makeMove(new Position(3, 3)); // a move by the first player
        assertEquals(game3.currentPlayer(), PlayerRole.SECOND_PLAYER);
        game3.makeMove(new Position(3, 1)); // a move by the second player
        assertEquals(game3.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game3.capturedPairsNo(PlayerRole.SECOND_PLAYER), 1);
        // TODO 4: write at least 3 test cases
    }

    @Test
    void capturedPairsNo() {
        // test case 1: are captured pairs registered?
        Pente game = new Pente();
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(3, 3)); // a move by the second player
        game.makeMove(new Position(4, 2)); // a move by the first player
        game.makeMove(new Position(3, 4)); // a move by the second player
        game.makeMove(new Position(3, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(1, game.capturedPairsNo(PlayerRole.FIRST_PLAYER));

        // test case 2: are captured pairs in different directions correctly calculated?
        Pente game1 = new Pente();
        game1.makeMove(new Position(1, 1)); // a move by the first player
        game1.makeMove(new Position(3, 3)); // a move by the second player
        game1.makeMove(new Position(2, 2)); // a move by the first player
        game1.makeMove(new Position(1, 2)); // a move by the second player
        game1.makeMove(new Position(2, 1)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(2, game1.capturedPairsNo(PlayerRole.FIRST_PLAYER));
        assertEquals(0, game1.capturedPairsNo(PlayerRole.SECOND_PLAYER));

        // test case 3: are captured pairs in the same directions correctly calculated?
        Pente game2 = new Pente();
        game2.makeMove(new Position(1, 1)); // a move by the first player
        game2.makeMove(new Position(1, 2)); // a move by the second player
        game2.makeMove(new Position(2, 2)); // a move by the first player
        game2.makeMove(new Position(2, 3)); // a move by the second player
        game2.makeMove(new Position(3, 3)); // a move by the first player
        game2.makeMove(new Position(3, 4)); // a move by the second player
        game2.makeMove(new Position(4, 4)); // a move by the first player
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 3);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 2);

        // test case 4: are captured pairs work good to the end?
        Pente game3 = new Pente();
        game3.makeMove(new Position(1, 1)); // a move by the first player
        game3.makeMove(new Position(1, 2)); // a move by the second player
        game3.makeMove(new Position(2, 2)); // a move by the first player
        game3.makeMove(new Position(2, 3)); // a move by the second player
        game3.makeMove(new Position(3, 3)); // a move by the first player
        game3.makeMove(new Position(3, 4)); // a move by the second player
        game3.makeMove(new Position(4, 4)); // a move by the first player
        game3.makeMove(new Position(6, 1)); // a move by the second player
        game3.makeMove(new Position(5, 5)); // a move by the first player
        assertEquals(game3.capturedPairsNo(PlayerRole.FIRST_PLAYER), 4);
        assertEquals(game3.capturedPairsNo(PlayerRole.SECOND_PLAYER), 2);
        // TODO 5: write at least 3 test cases
    }

    @Test
    void hasEnded() {
        // test case 1: is a board with 5 in a row an ended game?
        Pente game = new Pente();
        assertFalse(game.hasEnded());
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(2, 1)); // a move by the second player
        game.makeMove(new Position(1, 2)); // a move by the first player
        game.makeMove(new Position(2, 2)); // a move by the second player
        game.makeMove(new Position(1, 3)); // a move by the first player
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(1, 4)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(1, 5)); // a move by the first player
        assertTrue(game.hasEnded());

        // test case 2: is a board with 5 in a column an ended game?
        Pente game1 = new Pente();
        assertFalse(game1.hasEnded());
        game1.makeMove(new Position(1, 1)); // a move by the first player
        game1.makeMove(new Position(2, 1)); // a move by the second player
        game1.makeMove(new Position(2, 1)); // a move by the first player
        game1.makeMove(new Position(2, 2)); // a move by the second player
        game1.makeMove(new Position(3, 1)); // a move by the first player
        game1.makeMove(new Position(2, 6)); // a move by the second player
        game1.makeMove(new Position(4, 1)); // a move by the first player
        game1.makeMove(new Position(3, 7)); // a move by the second player
        game1.makeMove(new Position(5, 1)); // a move by the first player
        game1.makeMove(new Position(0, 0)); // a move by the second player
        assertTrue(game1.hasEnded());

        // test case 3: is a board with 5 slanted an ended game?
        Pente game2 = new Pente();
        assertFalse(game2.hasEnded());
        game2.makeMove(new Position(1, 1)); // a move by the first player
        game2.makeMove(new Position(2, 1)); // a move by the second player
        game2.makeMove(new Position(2, 2)); // a move by the first player
        game2.makeMove(new Position(2, 2)); // a move by the second player
        game2.makeMove(new Position(3, 3)); // a move by the first player
        game2.makeMove(new Position(2, 6)); // a move by the second player
        game2.makeMove(new Position(4, 4)); // a move by the first player
        game2.makeMove(new Position(3, 7)); // a move by the second player
        game2.makeMove(new Position(5, 5)); // a move by the first player
        game2.makeMove(new Position(0, 0)); // a move by the second player
        assertTrue(game2.hasEnded());

        // test case 4: is a board an ended game when it is not supposed to be?
        Pente game3 = new Pente();
        assertFalse(game3.hasEnded());
        game3.makeMove(new Position(1, 1)); // a move by the first player
        game3.makeMove(new Position(2, 1)); // a move by the second player
        game3.makeMove(new Position(2, 2)); // a move by the first player
        game3.makeMove(new Position(2, 2)); // a move by the second player
        assertFalse(game3.hasEnded());
        // TODO 6: write at least 3 test cases
    }

    @Test
    void stateEqual() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();
        Pente game4 = new Pente();
        Pente game5 = new Pente();

        // test 1: games with equal board states should be stateEqual()
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertTrue(game1.stateEqual(game2));
        assertTrue(game2.stateEqual(game1));

        // test 2: games with unequal board states should not be stateEqual()
        game3.makeMove(new Position(0, 0));
        assertFalse(game1.stateEqual(game3));

        // test 3: games with equal empty board should be stateEqual()
        assertTrue(game4.stateEqual(game5));
        assertTrue(game5.stateEqual(game4));

        // test 4: games with unequal board states should not be stateEqual()
        game4.makeMove(new Position(4, 3));
        game5.makeMove(new Position(3, 4));
        assertFalse(game4.stateEqual(game5));
        assertFalse(game5.stateEqual(game4));

        // test 5: games with unequal board states should not be stateEqual()
        game3.makeMove(new Position(3, 3));
        assertFalse(game1.stateEqual(game3));
        assertFalse(game2.stateEqual(game3));

        // TODO 7: write at least 3 test cases
    }
}