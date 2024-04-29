package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTests {

    @Test
    public void testMovePawnBackwardsShouldBeIllegal() {
        //Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        //Assert
        assertFalse(pawn.canMove(chessboard, new Square("e1")));

        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void pawnMovesForwardOneSquare() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        assertTrue(pawn.canMove(chessboard, new Square("e3")));
    }

    @Test
    public void pawnMovesForwardTwoSquaresFromStart() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);

        assertTrue(pawn.canMove(chessboard, new Square("e4")));
    }

    @Test
    public void pawnCapturesDiagonally() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        ChessPiece enemyPiece = new Pawn(Color.BLACK, new Square("d3"));
        chessboard.addPiece(pawn);
        chessboard.addPiece(enemyPiece);

        assertTrue(pawn.canMove(chessboard, new Square("d3")));
    }

    @Test
    public void pawnBlockedByAnotherPiece() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        ChessPiece blockingPiece = new Pawn(Color.WHITE, new Square("e3"));
        chessboard.addPiece(pawn);
        chessboard.addPiece(blockingPiece);

        assertFalse(pawn.canMove(chessboard, new Square("e3")));
    }
}
