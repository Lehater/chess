package test.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.Bishop;
import main.java.edu.chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class BishopTest {

    private Bishop bishop;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        // Инициализация объекта Bishop и доски ChessBoard
        bishop = new Bishop("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[3][3] = bishop; // Устанавливаем слона на позицию (3, 3)
    }

    @Test
    public void testGetColor() {
        // Проверка, что цвет слона правильно возвращается
        assertEquals("Цвет слона должен быть белым", "White", bishop.getColor());
    }

    @Test
    public void testValidMoveDiagonal() {
        // Проверка, что слон может ходить по диагонали
        assertTrue("Слон должен уметь ходить по диагонали (вверх вправо)"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 5));
        assertTrue("Слон должен уметь ходить по диагонали (вниз влево)"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 1, 1));
        assertTrue("Слон должен уметь ходить по диагонали (вверх влево)"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 1));
        assertTrue("Слон должен уметь ходить по диагонали (вниз вправо)"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 1, 5));
    }

    @Test
    public void testInvalidMoveNonDiagonal() {
        // Проверка, что слон не может ходить по прямой линии
        assertFalse("Слон не должен ходить по горизонтали"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 3, 5));
        assertFalse("Слон не должен ходить по вертикали"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 3));
    }

    @Test
    public void testMoveOutOfBoard() {
        // Проверка, что слон не может выйти за границы доски
        assertFalse("Слон не должен ходить за границы доски"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 8, 8));
        assertFalse("Слон не должен ходить за границы доски"
                , bishop.canMoveToPosition(chessBoard, 3, 3, -1, -1));
    }

    @Test
    public void testSamePosition() {
        // Проверка, что слон не может остаться на той же позиции
        assertFalse("Слон не должен оставаться на той же позиции"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 3, 3));
    }

    @Test
    public void testBlockedPath() {
        // Добавляем фигуру на пути слона и проверяем, что ход невозможен
        chessBoard.board[4][4] = new Pawn("White");
        assertFalse("Слон не должен проходить через другие фигуры"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 5));
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Добавляем черную фигуру на пути слона и проверяем, что захват возможен
        chessBoard.board[5][5] = new Pawn("Black");
        assertTrue("Слон должен захватывать фигуры противника"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 5));
    }

    @Test
    public void testCaptureSameColorPiece() {
        // Добавляем белую фигуру на пути слона и проверяем, что захват невозможен
        chessBoard.board[5][5] = new Pawn("White");
        assertFalse("Слон не должен захватывать фигуры того же цвета"
                , bishop.canMoveToPosition(chessBoard, 3, 3, 5, 5));
    }
}
