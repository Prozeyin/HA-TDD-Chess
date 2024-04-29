package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Bishop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTests {

    @Test
    public void bishopMovesDiagonally() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);
        assertTrue(bishop.canMove(chessboard, new Square("e3")));
    }

    @Test
    public void bishopIsBlockedByAnotherPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        Bishop blockingBishop = new Bishop(Color.WHITE, new Square("b2"));
        chessboard.addPiece(bishop);
        chessboard.addPiece(blockingBishop);
        assertFalse(bishop.canMove(chessboard, new Square("a3")));
    }

    @Test
    public void bishopCannotMoveHorizontally() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);
        assertFalse(bishop.canMove(chessboard, new Square("c3")));
    }

    @Test
    public void bishopCannotMoveVertically() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);
        assertFalse(bishop.canMove(chessboard, new Square("e1")));
    }

    @Test
    public void bishopCapturesOpponentsPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        Bishop opponentBishop = new Bishop(Color.BLACK, new Square("e3"));
        chessboard.addPiece(bishop);
        chessboard.addPiece(opponentBishop);
        assertTrue(bishop.canMove(chessboard, new Square("e3")));
    }
}
