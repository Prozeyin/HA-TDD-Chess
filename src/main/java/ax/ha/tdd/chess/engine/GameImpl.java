package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;

import java.util.regex.Pattern;

public class GameImpl implements Game{

    final ChessboardImpl board = ChessboardImpl.startingBoard();
    boolean isNewGame = true;
    private Color currentPlayer = Color.WHITE; // Start the game with White by convention

    private String lastMoveResult = "Game hasn't begun";

    @Override
    public Color getPlayerToMove() {
        return currentPlayer;
    }

    @Override
    public Chessboard getBoard() {
        return board;
    }

    @Override
    public String getLastMoveResult() {
        if (isNewGame) {
            return "Game hasn't begun";
        }
        return lastMoveResult;
    }

    @Override
    public void move(String move) {
        if(isNewGame){
            isNewGame = false;
        }
        String moveRegex = "^[a-h][1-8][a-h][1-8]$";
        if (move == null || !Pattern.matches(moveRegex, move)) {
            lastMoveResult = "Invalid move format: " + move;
            return;
        }
        try {
            Square fromSquare = new Square(move.substring(0, 2));
            Square toSquare = new Square(move.substring(3, 5));
            ChessPiece piece = board.getPieceAt(fromSquare);

            if (piece == null || piece.getColor() != currentPlayer) {
                lastMoveResult = "No piece at source or not your turn. " + move;
                return;
            }

            if (piece.canMove(board, toSquare)) {
                board.movePiece(fromSquare, toSquare);
                if (board.isKingInCheck(currentPlayer)) {
                    board.movePiece(toSquare, fromSquare); // Undo the move
                    lastMoveResult = "Move results in check. " + move;
                } else {
                    currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
                    if(board.isKingInCheck(currentPlayer))
                    {
                        lastMoveResult = move + ": " + currentPlayer.toString().toLowerCase() + " king is in check";
                    }
                    else {
                        lastMoveResult = move;
                    }

                }
            } else {
                lastMoveResult = "Illegal move. " + move;
            }
        } catch (IllegalArgumentException e) {
            lastMoveResult = "Invalid coordinates provided. " + move;
        }
    }
}
