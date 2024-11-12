package test.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.King;
import main.java.edu.chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class KingTest {

    private King king;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        // Инициализация объекта King и доски ChessBoard
        king = new King("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[4][4] = king; // Устанавливаем короля на позицию (4, 4)
    }

    @Test
    public void testGetColor() {
        // Проверка, что цвет короля правильно возвращается
        assertEquals("Цвет короля должен быть белым", "White", king.getColor());
    }

    @Test
    public void testValidMoveOneStep() {
        // Проверка, что король может ходить на одну клетку во всех направлениях
        assertTrue("Король должен уметь ходить на одну клетку вверх",
                king.canMoveToPosition(chessBoard, 4, 4, 5, 4));
        assertTrue("Король должен уметь ходить на одну клетку вниз",
                king.canMoveToPosition(chessBoard, 4, 4, 3, 4));
        assertTrue("Король должен уметь ходить на одну клетку вправо",
                king.canMoveToPosition(chessBoard, 4, 4, 4, 5));
        assertTrue("Король должен уметь ходить на одну клетку влево",
                king.canMoveToPosition(chessBoard, 4, 4, 4, 3));
        assertTrue("Король должен уметь ходить по диагонали вверх вправо",
                king.canMoveToPosition(chessBoard, 4, 4, 5, 5));
        assertTrue("Король должен уметь ходить по диагонали вверх влево",
                king.canMoveToPosition(chessBoard, 4, 4, 5, 3));
        assertTrue("Король должен уметь ходить по диагонали вниз вправо",
                king.canMoveToPosition(chessBoard, 4, 4, 3, 5));
        assertTrue("Король должен уметь ходить по диагонали вниз влево",
                king.canMoveToPosition(chessBoard, 4, 4, 3, 3));
    }

    @Test
    public void testInvalidMoveMoreThanOneStep() {
        // Проверка, что король не может ходить больше чем на одну клетку
        assertFalse("Король не должен ходить больше чем на одну клетку",
                king.canMoveToPosition(chessBoard, 4, 4, 6, 4));
        assertFalse("Король не должен ходить больше чем на одну клетку",
                king.canMoveToPosition(chessBoard, 4, 4, 4, 6));
        assertFalse("Король не должен ходить больше чем на одну клетку",
                king.canMoveToPosition(chessBoard, 4, 4, 6, 6));
    }

    @Test
    public void testMoveOutOfBoard() {
        // Проверка, что король не может выйти за границы доски
        assertFalse("Король не должен ходить за границы доски"
                , king.canMoveToPosition(chessBoard, 4, 4, 8, 4));
        assertFalse("Король не должен ходить за границы доски"
                , king.canMoveToPosition(chessBoard, 4, 4, -1, 4));
    }

    @Test
    public void testSamePosition() {
        // Проверка, что король не может остаться на той же позиции
        assertFalse("Король не должен оставаться на той же позиции"
                , king.canMoveToPosition(chessBoard, 4, 4, 4, 4));
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Добавляем черную фигуру на соседнюю клетку и проверяем, что захват возможен
        chessBoard.board[5][4] = new Pawn("Black");
        assertTrue("Король должен захватывать фигуры противника"
                , king.canMoveToPosition(chessBoard, 4, 4, 5, 4));
    }

    @Test
    public void testCaptureSameColorPiece() {
        // Добавляем белую фигуру на соседнюю клетку и проверяем, что захват невозможен
        chessBoard.board[5][4] = new Pawn("White");
        assertFalse("Король не должен захватывать фигуры того же цвета"
                , king.canMoveToPosition(chessBoard, 4, 4, 5, 4));
    }
}
