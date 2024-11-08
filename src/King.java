public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false; // Проверяем, находится ли целевая позиция на доске
        }
        if (line == toLine && column == toColumn) {
            return false; // Король не может переместиться в свою текущую позицию
        }

        // Король ходит на одно поле в любом направлении
        if (Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1) {
            // Проверка, есть ли на целевой позиции фигура противника
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor());
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = chessBoard.board[i][j];
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    if (piece.canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true; // Позиция находится под атакой
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
