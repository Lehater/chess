package main.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false; // Проверяем, находится ли целевая позиция на доске
        }
        if (line == toLine && column == toColumn) {
            return false; // Ладья не может переместиться в свою текущую позицию
        }

        // Проверка, что ход вертикальный или горизонтальный
        if (line == toLine || column == toColumn) {
            int stepX = Integer.compare(toLine - line, 0);
            int stepY = Integer.compare(toColumn - column, 0);
            int currentLine = line + stepX;
            int currentColumn = column + stepY;

            // Проверка, что путь свободен
            while (currentLine != toLine || currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // Путь не свободен
                }
                currentLine += stepX;
                currentColumn += stepY;
            }

            // Проверка, есть ли на целевой позиции фигура противника
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(
                    this.getColor()
            );
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}