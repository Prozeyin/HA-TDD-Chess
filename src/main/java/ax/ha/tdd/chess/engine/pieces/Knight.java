package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Color;
import ax.ha.tdd.chess.engine.Square;

public class Knight extends ChessPieceBase {

    public Knight(Color color, Square location) {
        super(PieceType.KNIGHT, color, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        // Calculate the differences in x and y directions
        int dx = Math.abs(destination.getX() - this.location.getX());
        int dy = Math.abs(destination.getY() - this.location.getY());

        // Check for the L-shape movement: 2 squares along one direction and 1 square perpendicular
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
