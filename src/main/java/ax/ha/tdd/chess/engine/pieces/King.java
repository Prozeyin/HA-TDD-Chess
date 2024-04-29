package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class King extends ChessPieceBase {

    public King(Color color, Square location) {
        super(PieceType.KING, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Calculate the distance in x and y directions
        int dx = Math.abs(destination.getX() - this.location.getX());
        int dy = Math.abs(destination.getY() - this.location.getY());

        // King moves only one square in any direction
        if (dx > 1 || dy > 1) {
            return false;
        }

        // Check for any piece at the destination square
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        // Ensure the destination is either empty or occupied by an opponent's piece
        if (pieceAtDestination != null && pieceAtDestination.getColor() == this.color) {
            return false; // Cannot capture your own piece
        }

        // Check if the move puts the king in check
        ChessPiece originalPiece = pieceAtDestination;  // Store the original piece at the destination
        chessboard.removePieceAt(this.location); // Temporarily remove the king from the current location
        chessboard.addPiece(new King(this.color, destination)); // Temporarily move the king to the destination
        boolean isInCheck = chessboard.isKingInCheck(this.color); // Check if the king is in check at the new location
        chessboard.removePieceAt(destination); // Remove the king from the temporary location
        chessboard.addPiece(this); // Re-add the king to the original location
        if (originalPiece != null) {
            chessboard.addPiece(originalPiece); // Re-add the original piece if it was captured
        }

        return !isInCheck;
    }
}
