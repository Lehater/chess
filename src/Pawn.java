public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine < 0 || toLine > 7 || toColumn < 0 || toColumn > 7) {
            return false; // Проверяем, находится ли целевая позиция на доске
        }
        if (line == toLine && column == toColumn) {
            return false; // Пешка не может переместиться в свою текущую позицию
        }

        int direction = this.getColor().equals("White") ? 1 : -1; // Белые движутся вверх, черные вниз
        int startLine = this.getColor().equals("White") ? 1 : 6; // Начальная линия для белых и черных пешек

        // Проверка обычного хода (на одно поле вперед)
        if (column == toColumn) {
            if (toLine - line == direction && chessBoard.board[toLine][toColumn] == null) {
                return true; // Проверяем, что целевая позиция пуста
            }
            if (line == startLine && toLine - line == 2 * direction && chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + direction][column] == null) {
                return true; // Первый ход на два поля вперед
            }
        }

        // Проверка взятия фигуры
        if (Math.abs(toColumn - column) == 1 && toLine - line == direction) {
            return chessBoard.board[toLine][toColumn] != null && !chessBoard.board[toLine][toColumn].getColor().equals(this.getColor());
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
