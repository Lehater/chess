package main.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }


    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false; // Проверяем, находится ли целевая позиция на доске
        }
        if (line == toLine && column == toColumn) {
            return false; // Конь не может переместиться в свою текущую позицию
        }

        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        // Проверка хода буквой \"Г\"
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            // Проверка, есть ли на целевой позиции фигура противника
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor());
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
