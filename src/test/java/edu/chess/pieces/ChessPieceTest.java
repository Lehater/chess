package test.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.ChessPiece;
import main.java.edu.chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ChessPieceTest {

    private ChessPiece piece;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        // Инициализация объекта Pawn и доски ChessBoard для тестирования базовой функциональности ChessPiece
        piece = new Pawn("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[1][4] = piece; // Устанавливаем белую пешку на начальную позицию (1, 4)
    }

    @Test
    public void testPieceNotNull() {
        assertNotNull("Объект ChessPiece не должен быть null", piece);
    }

    @Test
    public void testPieceColor() {
        assertEquals("Цвет фигуры должен быть белым",
                "White", piece.getColor());
    }

    @Test
    public void testPiecePosition() {
        // Проверяем, что фигура находится на ожидаемой позиции
        assertEquals("Фигура должна быть на позиции (1, 4)",
                piece, chessBoard.board[1][4]);
    }
}
