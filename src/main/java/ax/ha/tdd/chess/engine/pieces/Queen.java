package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Queen extends ChessPieceBase {

    public Queen(Color color, Square location) {
        super(PieceType.QUEEN, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Calculate the differences in x and y directions
        int dx = destination.getX() - this.location.getX();
        int dy = destination.getY() - this.location.getY();
        // Check for valid queen move (horizontal, vertical, or diagonal)
        if (Math.abs(dx) != Math.abs(dy) && dx != 0 && dy != 0) {
            return false;
        }

        // Check for obstacles in the path
        int xStep = Integer.compare(dx, 0);
        int yStep = Integer.compare(dy, 0);
        int x = this.location.getX() + xStep;
        int y = this.location.getY() + yStep;
        while (x != destination.getX() || y != destination.getY()) {
            if (chessboard.getPieceAt(new Square(x, y)) != null) {
                return false; // Path is blocked
            }
            x += xStep;
            y += yStep;
        }

        // Check for capturing a piece
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        return pieceAtDestination == null || pieceAtDestination.getColor() != this.color;
    }
}
