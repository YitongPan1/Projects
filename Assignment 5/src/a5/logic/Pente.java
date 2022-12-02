package a5.logic;

import a5.util.PlayerRole;
import a5.util.GameType;
import a5.util.GameResult;


/**
 * A Pente game, where players take turns to place stones on board.
 * When consecutive two stones are surrounded by the opponent's stones on two ends,
 * these two stones are removed (captured).
 * A player wins by placing 5 consecutive stones or capturing stones 5 times.
 */
public class Pente extends MNKGame {


    /**
     * Create an 8-by-8 Pente game.
     */
    private int ACaptured;
    private int BCaptured;

    private int player1 = capturedPairsNo(PlayerRole.FIRST_PLAYER);
    private int player2 = capturedPairsNo(PlayerRole.SECOND_PLAYER);

    public Pente() {
        // TODO 1
        super(8, 8, 5);
        ACaptured = 0;
        BCaptured = 0;
    }

    /**
     * Creates: a copy of the game state.
     */
    public Pente(Pente game) {
        // TODO 2
        super(game);
        ACaptured = game.ACaptured;
        BCaptured = game.BCaptured;
    }

    @Override
    public boolean makeMove(Position p) {
        // TODO 3
        if(!board().validPos(p)){
            return false;
        } else {
            Position n1 = new Position(p.row()-1, p.col());
            Position n2 = new Position(p.row()-2, p.col());
            Position n3 = new Position(p.row()-3, p.col());
            Position ne1 = new Position(p.row()-1, p.col()+1);
            Position ne2 = new Position(p.row()-2, p.col()+2);
            Position ne3 = new Position(p.row()-3, p.col()+3);
            Position e1 = new Position(p.row(), p.col()+1);
            Position e2 = new Position(p.row(), p.col()+2);
            Position e3 = new Position(p.row(), p.col()+3);
            Position se1 = new Position(p.row()+1, p.col()+1);
            Position se2 = new Position(p.row()+2, p.col()+2);
            Position se3 = new Position(p.row()+3, p.col()+3);
            Position s1 = new Position(p.row()+1, p.col());
            Position s2 = new Position(p.row()+2, p.col());
            Position s3 = new Position(p.row()+3, p.col());
            Position sw1 = new Position(p.row()+1, p.col()-1);
            Position sw2 = new Position(p.row()+2, p.col()-2);
            Position sw3 = new Position(p.row()+3, p.col()-3);
            Position w1 = new Position(p.row(), p.col()-1);
            Position w2 = new Position(p.row(), p.col()-2);
            Position w3 = new Position(p.row(), p.col()-3);
            Position nw1 = new Position(p.row()-1, p.col()-1);
            Position nw2 = new Position(p.row()-2, p.col()-2);
            Position nw3 = new Position(p.row()-3, p.col()-3);

            if(board().onBoard(n1) && board().onBoard(n2) && board().get(n1) != currentPlayer().boardValue() && board().get(n1) != 0 && board().get(n2) != currentPlayer().boardValue() && board().get(n2) != 0 && board().get(n3) == currentPlayer().boardValue()){
                board().erase(n1);
                board().erase(n2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(ne1) && board().onBoard(ne2) && board().get(ne1) != currentPlayer().boardValue() && board().get(ne1) != 0 && board().get(ne2) != currentPlayer().boardValue() && board().get(ne2) != 0 && board().get(ne3) == currentPlayer().boardValue()){
                board().erase(ne1);
                board().erase(ne2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(e1) && board().onBoard(e2) && board().get(e1) != currentPlayer().boardValue() && board().get(e1) != 0 && board().get(e2) != currentPlayer().boardValue() && board().get(e2) != 0 && board().get(e3) == currentPlayer().boardValue()){
                board().erase(e1);
                board().erase(e2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(se1) && board().onBoard(se2) && board().get(se1) != currentPlayer().boardValue() && board().get(se1) != 0 && board().get(se2) != currentPlayer().boardValue() && board().get(se2) != 0 && board().get(se3) == currentPlayer().boardValue()){
                board().erase(se1);
                board().erase(se2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(s1) && board().onBoard(s2) && board().get(s1) != currentPlayer().boardValue() && board().get(s1) != 0 && board().get(s2) != currentPlayer().boardValue() && board().get(s2) != 0 && board().get(s3) == currentPlayer().boardValue()){
                board().erase(s1);
                board().erase(s2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(sw1) && board().onBoard(sw2) && board().get(sw1) != currentPlayer().boardValue() && board().get(sw1) != 0 && board().get(sw2) != currentPlayer().boardValue() && board().get(sw2) != 0 && board().get(sw3) == currentPlayer().boardValue()){
                board().erase(sw1);
                board().erase(sw2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(w1) && board().onBoard(w2) && board().get(w1) != currentPlayer().boardValue() && board().get(w1) != 0 && board().get(w2) != currentPlayer().boardValue() && board().get(w2) != 0 && board().get(w3) == currentPlayer().boardValue()){
                board().erase(w1);
                board().erase(w2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            if(board().onBoard(nw1) && board().onBoard(nw2) && board().get(nw1) != currentPlayer().boardValue() && board().get(nw1) != 0 && board().get(nw2) != currentPlayer().boardValue() && board().get(nw2) != 0 && board().get(nw3) == currentPlayer().boardValue()){
                board().erase(nw1);
                board().erase(nw2);
                if(currentPlayer().equals(PlayerRole.FIRST_PLAYER)){
                    player1++;
                } else {
                    player2++;
                }
            }
            changePlayer();
            advanceTurn();
        }
        return true;
    }

    /**
     * Returns: a new game state representing the state of the game after the current player takes a
     * move {@code p}.
     */
    public Pente applyMove(Position p) {
        Pente newGame = new Pente(this);
        newGame.makeMove(p);
        return newGame;
    }

    /**
     * Returns: the number of captured pairs by {@code playerRole}.
     */
    public int capturedPairsNo(PlayerRole playerRole) {
        // TODO 4
        if(playerRole.boardValue() == 1){
            return player1;
        } else if (playerRole.boardValue() == 2){
            return player2;
        }
        return -1;
    }

    @Override
    public boolean hasEnded() {
        // TODO 5
        if((capturedPairsNo(currentPlayer()) == 4) || (capturedPairsNo(currentPlayer().nextPlayer()) == 4)){
            return true;
        } else {
            return super.hasEnded();
        }
    }

    @Override
    public GameType gameType() {
        return GameType.PENTE;
    }

    @Override
    public String toString() {
        String board = super.toString();
        return board + System.lineSeparator() + "Captured pairs: " +
                "first: " + capturedPairsNo(PlayerRole.FIRST_PLAYER) + ", " +
                "second: " + capturedPairsNo(PlayerRole.SECOND_PLAYER);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Pente p = (Pente) o;
        return stateEqual(p);
    }

    /**
     * Returns: true if the two games have the same state.
     */
    protected boolean stateEqual(Pente p) {
        // TODO 6
        if (p.hashCode() == this.hashCode() && p.colSize() == this.colSize() && p.rowSize() == this.rowSize()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // TODO 7
        int hash;
        hash = super.hashCode();
        Position n = new Position(1,1);
        if(player1 != 0 || player2 != 0){
            return hash * (11 - ACaptured) * (323 - ACaptured);
        }
        else return hash * (493 - ACaptured) * (47 - BCaptured) + currentPlayer().boardValue();
    }
}
