public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false; // Проверяем, находится ли целевая позиция на доске
        }
        if (line == toLine && column == toColumn) {
            return false; // Слон не может переместиться в свою текущую позицию
        }

        int deltaX = Math.abs(toLine - line);
        int deltaY = Math.abs(toColumn - column);

        // Проверка, что ход по диагонали
        if (deltaX == deltaY) {
            int stepX = (toLine - line) > 0 ? 1 : -1;
            int stepY = (toColumn - column) > 0 ? 1 : -1;
            int currentLine = line + stepX;
            int currentColumn = column + stepY;

            // Проверка, что путь свободен
            while (currentLine != toLine && currentColumn != toColumn) {
                if (chessBoard.board[currentLine][currentColumn] != null) {
                    return false; // Путь не свободен
                }
                currentLine += stepX;
                currentColumn += stepY;
            }

            // Проверка, есть ли на целевой позиции фигура противника
            return chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor());
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
