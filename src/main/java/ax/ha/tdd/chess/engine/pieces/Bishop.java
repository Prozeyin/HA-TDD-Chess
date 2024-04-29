package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Bishop extends ChessPieceBase {

    public Bishop(Color color, Square location) {
        super(PieceType.BISHOP, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Bishops can only move diagonally, so the absolute change for x and y must be the same
        int deltaX = Math.abs(destination.getX() - this.location.getX());
        int deltaY = Math.abs(destination.getY() - this.location.getY());

        if (deltaX != deltaY) {
            // If the number of squares moved in x and y direction are not equal, it's not a legal move for a bishop
            return false;
        }

        // Determine the direction of the movement
        int directionX = Integer.compare(destination.getX(), this.location.getX());
        int directionY = Integer.compare(destination.getY(), this.location.getY());

        // Check for obstacles in the path
        int checkX = this.location.getX() + directionX;
        int checkY = this.location.getY() + directionY;

        while (checkX != destination.getX() && checkY != destination.getY()) {
            if (chessboard.getPieceAt(new Square(checkX, checkY)) != null) {
                // If there's a piece in the way, the bishop cannot move to the destination
                return false;
            }

            // Move to the next square in the direction
            checkX += directionX;
            checkY += directionY;
        }

        // If we reached this point, the path is clear, and the bishop can move to the destination
        return true;
    }
}
