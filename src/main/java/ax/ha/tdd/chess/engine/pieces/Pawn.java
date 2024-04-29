package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Pawn extends ChessPieceBase {

    public Pawn(Color color, Square location) {
        super(PieceType.PAWN, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Determine direction of movement based on the pawn's color
        int verticalDirection = (this.color == Color.WHITE) ? -1 : 1;
        // Determine starting row based on the pawn's color
        int startRow = (this.color == Color.WHITE) ? 6 : 1;
        // Calculate target positions
        int forwardOne = this.location.getY() + verticalDirection;
        int forwardTwo = this.location.getY() + (2 * verticalDirection);

        // Check for normal one-square advance
        if (destination.getY() == forwardOne && destination.getX() == this.location.getX()) {
            // Must not be blocked
            return chessboard.getPieceAt(destination) == null;
        }

        // Check for initial two-square advance from start position
        if (this.location.getY() == startRow && destination.getY() == forwardTwo && destination.getX() == this.location.getX()) {
            // Must not be blocked in both the immediate square and the destination square
            Square oneSquareForward = new Square(this.location.getX(), forwardOne);
            return chessboard.getPieceAt(oneSquareForward) == null && chessboard.getPieceAt(destination) == null;
        }

        // Check for diagonal capture
        if (destination.getY() == forwardOne && (destination.getX() == this.location.getX() + 1 || destination.getX() == this.location.getX() - 1)) {
            // There must be an enemy piece to capture
            ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
            return pieceAtDestination != null && pieceAtDestination.getColor() != this.color;
        }

        // If none of the conditions are met, it's an illegal move for a pawn
        return false;
    }
}
