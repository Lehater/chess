public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn)) {
            if (board[startLine][startColumn] != null) {
                if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                    return false; // Ensure the piece belongs to the current player
                }
                if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                    board[endLine][endColumn] = board[startLine][startColumn]; // Move the piece
                    board[startLine][startColumn] = null; // Clear the starting position

                    // Update the check status after the move
                    if (board[endLine][endColumn] instanceof King || board[endLine][endColumn] instanceof Rook) {
                        board[endLine][endColumn].check = false;
                    }

                    // Switch player turn
                    nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    public void printBoard() {  //print board in console
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
        System.out.println("Turn " + nowPlayer + "! (Enter command:)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }


    public boolean castling(int row, int kingCol, int rookCol, int[] emptyCols, int[] checkCols) {
        if (nowPlayer.equals(board[row][kingCol].getColor()) &&
                board[row][kingCol] instanceof King king &&
                board[row][rookCol] instanceof Rook rook
        ) {
            if (king.check && rook.check) {
                for (int col : emptyCols) { // Проверяем, что все промежуточные клетки пусты
                    if (board[row][col] != null) {
                        return false;
                    }
                }
                for (int col : checkCols) { // Проверяем, что клетки не находятся под атакой
                    if (king.isUnderAttack(this, row, col)) {
                        return false;
                    }
                }
                // Выполняем рокировку
                board[row][rookCol < kingCol ? kingCol - 2 : kingCol + 2] = king;
                board[row][kingCol] = null;
                board[row][rookCol < kingCol ? kingCol - 1 : kingCol + 1] = rook;
                board[row][rookCol] = null;
                king.check = false;
                rook.check = false;
                nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    public boolean castling0() {
        return castling(
                nowPlayer.equals("White") ? 0 : 7, 4, 0,
                new int[]{1, 2, 3},
                new int[]{4, 3, 2}
        );
    }

    public boolean castling7() {
        return castling(
                nowPlayer.equals("White") ? 0 : 7, 4, 7,
                new int[]{5, 6},
                new int[]{4, 5, 6}
        );
    }
}
