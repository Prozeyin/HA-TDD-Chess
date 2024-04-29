package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Queen;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KingTests {

    @Test
    public void kingMovesOneSquare() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e2"));
        chessboard.addPiece(king);

        // Test movement in all eight possible directions from "e2"
        assertTrue(king.canMove(chessboard, new Square("e3"))); // Forward
        assertTrue(king.canMove(chessboard, new Square("d2"))); // Left
        assertTrue(king.canMove(chessboard, new Square("f2"))); // Right
        assertTrue(king.canMove(chessboard, new Square("d3"))); // Diagonally left forward
        assertTrue(king.canMove(chessboard, new Square("f3"))); // Diagonally right forward
        assertTrue(king.canMove(chessboard, new Square("e1"))); // Backward
        assertTrue(king.canMove(chessboard, new Square("d1"))); // Diagonally left backward
        assertTrue(king.canMove(chessboard, new Square("f1"))); // Diagonally right backward
    }

    @Test
    public void kingCannotMoveIntoOccupiedSquare() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        Pawn friendPawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(king);
        chessboard.addPiece(friendPawn);

        assertFalse(king.canMove(chessboard, new Square("e2"))); // Blocked by friendly pawn
    }

    @Test
    public void kingCannotCaptureAnotherKing() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        King opponentKing = new King(Color.BLACK, new Square("e3"));
        chessboard.addPiece(king);
        chessboard.addPiece(opponentKing);

        assertFalse(king.canMove(chessboard, new Square("e2"))); // A king should not move into the square adjacent to another king
    }

    @Test
    public void kingCapturesOpponentsPiece() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        Rook opponentRook = new Rook(Color.BLACK, new Square("e2"));
        chessboard.addPiece(king);
        chessboard.addPiece(opponentRook);

        assertTrue(king.canMove(chessboard, new Square("e2"))); // Capturing opponent rook
    }
    @Test
    public void kingCannotMoveIntoCheck() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        Queen threateningQueen = new Queen(Color.BLACK, new Square("e3"));
        chessboard.addPiece(king);
        chessboard.addPiece(threateningQueen);

        assertFalse(king.canMove(chessboard, new Square("e2"))); // Would move into check
    }

    @Test
    public void kingMovesOutOfCheck() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("d1"));
        Queen threateningQueen = new Queen(Color.BLACK, new Square("d3"));
        chessboard.addPiece(king);
        chessboard.addPiece(threateningQueen);

        assertTrue(king.canMove(chessboard, new Square("c1"))); // Move left to get out of check
    }
    @Test
    public void kingCannotMoveOffBoard() {
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("h1"));
        chessboard.addPiece(king);
        assertThrows(IllegalArgumentException.class, () -> {
            king.canMove(chessboard, new Square("i1")); // Invalid move off the board
        });
    }

}
