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
//        System.out.println();
        System.out.println("Turn " + nowPlayer +"! (Enter command:)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        // Проверка условий для рокировки по 0 столбцу
        if (nowPlayer.equals("White")) {
            if (board[0][4] instanceof King && board[0][0] instanceof Rook) {
                King king = (King) board[0][4];
                Rook rook = (Rook) board[0][0];
                if (king.check && rook.check && board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                    if (!king.isUnderAttack(this, 0, 4) && !king.isUnderAttack(this, 0, 3) && !king.isUnderAttack(this, 0, 2)) {
                        // Выполняем рокировку
                        board[0][2] = king;
                        board[0][4] = null;
                        board[0][3] = rook;
                        board[0][0] = null;
                        king.check = false;
                        rook.check = false;
                        nowPlayer = "Black";
                        return true;
                    }
                }
            }
        } else {
            // Проверка условий для черных фигур
            if (board[7][4] instanceof King && board[7][0] instanceof Rook) {
                King king = (King) board[7][4];
                Rook rook = (Rook) board[7][0];
                if (king.check && rook.check && board[7][1] == null && board[7][2] == null && board[7][3] == null) {
                    if (!king.isUnderAttack(this, 7, 4) && !king.isUnderAttack(this, 7, 3) && !king.isUnderAttack(this, 7, 2)) {
                        // Выполняем рокировку
                        board[7][2] = king;
                        board[7][4] = null;
                        board[7][3] = rook;
                        board[7][0] = null;
                        king.check = false;
                        rook.check = false;
                        nowPlayer = "White";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean castling7() {
        // Проверка условий для рокировки по 7 столбцу
        if (nowPlayer.equals("White")) {
            if (board[0][4] instanceof King && board[0][7] instanceof Rook) {
                King king = (King) board[0][4];
                Rook rook = (Rook) board[0][7];
                if (king.check && rook.check && board[0][5] == null && board[0][6] == null) {
                    if (!king.isUnderAttack(this, 0, 4) && !king.isUnderAttack(this, 0, 5) && !king.isUnderAttack(this, 0, 6)) {
                        // Выполняем рокировку
                        board[0][6] = king;
                        board[0][4] = null;
                        board[0][5] = rook;
                        board[0][7] = null;
                        king.check = false;
                        rook.check = false;
                        nowPlayer = "Black";
                        return true;
                    }
                }
            }
        } else {
            // Проверка условий для черных фигур
            if (board[7][4] instanceof King && board[7][7] instanceof Rook) {
                King king = (King) board[7][4];
                Rook rook = (Rook) board[7][7];
                if (king.check && rook.check && board[7][5] == null && board[7][6] == null) {
                    if (!king.isUnderAttack(this, 7, 4) && !king.isUnderAttack(this, 7, 5) && !king.isUnderAttack(this, 7, 6)) {
                        // Выполняем рокировку
                        board[7][6] = king;
                        board[7][4] = null;
                        board[7][5] = rook;
                        board[7][7] = null;
                        king.check = false;
                        rook.check = false;
                        nowPlayer = "White";
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
