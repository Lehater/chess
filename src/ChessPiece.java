public abstract class ChessPiece {
    private String color;
    protected boolean check = true;

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

}