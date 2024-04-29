package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Knight;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {
    @Test
    public void knightMovesInLShapes() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e4"));
        chessboard.addPiece(knight);

        // Test all possible L-shaped moves from e4
        assertTrue(knight.canMove(chessboard, new Square("d6")));
        assertTrue(knight.canMove(chessboard, new Square("f6")));
        assertTrue(knight.canMove(chessboard, new Square("c5")));
        assertTrue(knight.canMove(chessboard, new Square("g5")));
        assertTrue(knight.canMove(chessboard, new Square("c3")));
        assertTrue(knight.canMove(chessboard, new Square("g3")));
        assertTrue(knight.canMove(chessboard, new Square("d2")));
        assertTrue(knight.canMove(chessboard, new Square("f2")));
    }

    @Test
    public void knightCannotMoveOutsideOfLShapes() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e4"));
        chessboard.addPiece(knight);

        // Test invalid moves
        assertFalse(knight.canMove(chessboard, new Square("e5")));
        assertFalse(knight.canMove(chessboard, new Square("e3")));
        assertFalse(knight.canMove(chessboard, new Square("d4")));
        assertFalse(knight.canMove(chessboard, new Square("f4")));
    }

    @Test
    public void knightCanJumpOverPieces() {
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("b1"));
        // Placing pieces that would block a non-knight piece
        chessboard.addPiece(new Pawn(Color.WHITE, new Square("b2")));
        chessboard.addPiece(new Pawn(Color.WHITE, new Square("c2")));
        chessboard.addPiece(new Pawn(Color.WHITE, new Square("c3")));

        assertTrue(knight.canMove(chessboard, new Square("c3"))); // Jump over pieces
    }
}
