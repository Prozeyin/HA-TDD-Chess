package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Rook extends ChessPieceBase {

    public Rook(Color color, Square location) {
        super(PieceType.ROOK, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Rooks can move horizontally or vertically, so one of the coordinates must be the same
        boolean isHorizontalMove = this.location.getY() == destination.getY();
        boolean isVerticalMove = this.location.getX() == destination.getX();

        if (!(isHorizontalMove ^ isVerticalMove)) {
            // If it's neither purely horizontal nor purely vertical, it's not a legal move for a rook
            return false;
        }

        // Determine if there are any pieces in the path (horizontal or vertical)
        if (isPathClear(chessboard, destination)) {
            // The path is clear, the rook can move to the destination
            return true;
        }

        // The path is not clear, another piece is blocking the way
        return false;
    }

    private boolean isPathClear(Chessboard chessboard, Square destination) {
        // Determine the direction of the movement
        int directionX = Integer.compare(destination.getX(), this.location.getX());
        int directionY = Integer.compare(destination.getY(), this.location.getY());

        // Start checking from the next square in the direction of the destination
        int checkX = this.location.getX() + directionX;
        int checkY = this.location.getY() + directionY;

        while (checkX != destination.getX() || checkY != destination.getY()) {
            // If there's a piece in the way, the path is not clear
            if (chessboard.getPieceAt(new Square(checkX, checkY)) != null) {
                return false;
            }

            // Move to the next square in the direction
            checkX += directionX;
            checkY += directionY;
        }

        // No pieces are blocking the path
        return true;
    }
}
