package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Queen;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTests {

    @Test
    public void queenMovesHorizontally() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        assertTrue(queen.canMove(chessboard, new Square("d1")));
        assertTrue(queen.canMove(chessboard, new Square("d8")));
    }

    @Test
    public void queenMovesVertically() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        assertTrue(queen.canMove(chessboard, new Square("a4")));
        assertTrue(queen.canMove(chessboard, new Square("h4")));
    }

    @Test
    public void queenMovesDiagonally() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        assertTrue(queen.canMove(chessboard, new Square("a1")));
        assertTrue(queen.canMove(chessboard, new Square("g7")));
    }

    @Test
    public void queenIsBlockedByAnotherPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        Pawn blockingPawn = new Pawn(Color.WHITE, new Square("d6"));
        chessboard.addPiece(queen);
        chessboard.addPiece(blockingPawn);
        assertFalse(queen.canMove(chessboard, new Square("d7"))); // Blocked by pawn
    }

    @Test
    public void queenCapturesOpponentsPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("d6"));
        chessboard.addPiece(queen);
        chessboard.addPiece(opponentPawn);
        assertTrue(queen.canMove(chessboard, new Square("d6"))); // Capturing black pawn
    }
}
