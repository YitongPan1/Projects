package a5.ai;

import static org.junit.jupiter.api.Assertions.*;

import a5.ai.TranspositionTable.StateInfo;
import a5.logic.Pente;
import a5.logic.Position;
import a5.logic.TicTacToe;
import cms.util.maybe.NoMaybeValue;
import org.junit.jupiter.api.Test;

class TranspositionTableTest {

    @Test
    void testConstructor() {
        // TODO 1: write at least 1 test case
        TranspositionTable<Pente> table = new TranspositionTable<>();
        Pente state = new Pente();
        table.add(state, 0, GameModel.WIN);
    }


    @Test
    void getInfo() throws NoMaybeValue {
        // test case 1: look for a state that is in the table
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // test case 2: look for a state not in the table
        TicTacToe state2 = state.applyMove(new Position(0, 0));
        assertThrows(NoMaybeValue.class, () -> table.getInfo(state2).get());

        // test case 3: look for a state that is in the table
        TranspositionTable<Pente> Pable = new TranspositionTable<>();
        Pente Pstate = new Pente();
        Pable.add(Pstate, 0, GameModel.WIN);

        StateInfo Pinfo = Pable.getInfo(Pstate).get();
        assertEquals(GameModel.WIN, Pinfo.value());
        assertEquals(0, Pinfo.depth());

        // test case 4: look for a state not in the table
        Pente Pstate1 = Pstate.applyMove(new Position(0, 0));
        assertThrows(NoMaybeValue.class, () -> Pable.getInfo(Pstate1).get());

        // test case 5: look for a state that is in the table
        TranspositionTable<Pente> Pable2 = new TranspositionTable<>();
        Pente Pstate2 = new Pente();
        Pable.add(Pstate2, 0, GameModel.WIN);
        Pable.add(Pstate2, 1, GameModel.WIN);
        Pable.add(Pstate2, 2, GameModel.WIN);
        StateInfo Pinfo2 = Pable2.getInfo(Pstate2).get();
        assertEquals(GameModel.WIN, Pinfo2.value());
        assertEquals(2, Pinfo2.depth());

        // TODO 2: write at least 3 more test cases
    }

    @Test
    void add() throws NoMaybeValue {
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();

        // test case 1: add a state and check it is in there
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // test case 2: add a state and check it is in there
        TranspositionTable<Pente> Pable = new TranspositionTable<>();
        Pente Pstate = new Pente();
        Pable.add(Pstate, 0, GameModel.WIN);

        StateInfo Pinfo = Pable.getInfo(Pstate).get();
        assertEquals(GameModel.WIN, Pinfo.value());
        assertEquals(0, Pinfo.depth());

        // test case 3: add a state and check it is in there
        TranspositionTable<Pente> Pable1 = new TranspositionTable<>();
        Pente Pstate1 = new Pente();
        Pable.add(Pstate1, 0, GameModel.WIN);
        Pable.add(Pstate1, 1, GameModel.WIN);
        Pable.add(Pstate1, 2, GameModel.WIN);
        StateInfo Pinfo1 = Pable1.getInfo(Pstate1).get();
        assertEquals(GameModel.WIN, Pinfo1.value());
        assertEquals(2, Pinfo1.depth());
        // TODO 3: write at least 3 more test cases
    }
}