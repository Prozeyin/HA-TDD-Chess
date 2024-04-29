package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RookTests {

    @Test
    public void rookMovesVertically() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook);
        assertTrue(rook.canMove(chessboard, new Square("d7")));
    }

    @Test
    public void rookMovesHorizontally() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook);
        assertTrue(rook.canMove(chessboard, new Square("a4")));
    }

    @Test
    public void rookIsBlockedByAnotherPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        Rook blockingRook = new Rook(Color.WHITE, new Square("d6"));
        chessboard.addPiece(rook);
        chessboard.addPiece(blockingRook);
        assertFalse(rook.canMove(chessboard, new Square("d8")));
    }

    @Test
    public void rookCannotMoveDiagonally() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        chessboard.addPiece(rook);
        assertFalse(rook.canMove(chessboard, new Square("f6")));
    }

    @Test
    public void rookCapturesOpponentsPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("d4"));
        Rook opponentRook = new Rook(Color.BLACK, new Square("d5"));
        chessboard.addPiece(rook);
        chessboard.addPiece(opponentRook);
        assertTrue(rook.canMove(chessboard, new Square("d5")));
    }
}
