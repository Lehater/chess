package main.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;

public abstract class ChessPiece {
    private final String color;
    private boolean wasMoved = false;

    public ChessPiece(String color) {
        this.color = color;
    }

    public abstract boolean canMoveToPosition(
            ChessBoard chessBoard,
            int line, int column, int toLine, int toColumn
    );

    public abstract String getSymbol();

    public String getColor() {
        return color;
    }

    public boolean wasMoved() {
        return wasMoved;
    }

    public void setWasMoved(boolean wasMoved) {
        this.wasMoved = wasMoved;
    }
}
