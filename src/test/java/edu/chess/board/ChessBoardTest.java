package test.java.edu.chess.board;


import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.King;
import main.java.edu.chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard("White");
        chessBoard.board[0][4] = new King("White"); // Устанавливаем короля на позицию (0, 4)
        chessBoard.board[0][0] = new Rook("White"); // Устанавливаем ладью на позицию (0, 0)
        chessBoard.board[0][7] = new Rook("White"); // Устанавливаем ладью на позицию (0, 7)
    }

    @Test
    public void testCastlingKingSide() {
        // Проверка рокировки по королевской стороне (с правой ладьей)
        assertTrue("Рокировка по королевской стороне должна быть возможна",
                chessBoard.castling7());
    }

    @Test
    public void testCastlingQueenSide() {
        // Проверка рокировки по ферзевой стороне (с левой ладьей)
        assertTrue("Рокировка по ферзевой стороне должна быть возможна",
                chessBoard.castling0());
    }

    @Test
    public void testInvalidCastlingAfterMove() {
        // Имитируем движение короля, что делает рокировку невозможной
        chessBoard.board[0][4].setWasMoved(true); // Помечаем, что король уже двигался
        assertFalse("Рокировка не должна быть возможна после движения короля",
                chessBoard.castling0());
        assertFalse("Рокировка не должна быть возможна после движения короля",
                chessBoard.castling7());
    }

    @Test
    public void testBlockedPathCastling() {
        // Добавляем фигуру между королем и ладьей
        chessBoard.board[0][5] = new Rook("Black");
        assertFalse("Рокировка не должна быть возможна при наличии фигуры между королем и ладьей",
                chessBoard.castling7());
    }

    @Test
    public void testCastlingUnderCheck() {
        // Имитация шаха для короля
        chessBoard.board[1][4] = new Rook("Black");
        assertFalse("Рокировка не должна быть возможна, если король находится под шахом",
                chessBoard.castling0());
        assertFalse("Рокировка не должна быть возможна, если король находится под шахом",
                chessBoard.castling7());
    }
}
