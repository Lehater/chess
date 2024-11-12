package test.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.Horse;
import main.java.edu.chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class HorseTest {

    private Horse horse;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        // Инициализация объекта Horse и доски ChessBoard
        horse = new Horse("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[4][4] = horse; // Устанавливаем коня на позицию (4, 4)
    }

    @Test
    public void testGetColor() {
        // Проверка, что цвет коня правильно возвращается
        assertEquals("Цвет коня должен быть белым", "White", horse.getColor());
    }

    @Test
    public void testValidMoveLShape() {
        // Проверка, что конь может ходить буквой "Г"
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 6, 5));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 6, 3));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 2, 5));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 2, 3));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 5, 6));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 5, 2));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 3, 6));
        assertTrue("Конь должен уметь ходить буквой 'Г'", horse.canMoveToPosition(chessBoard, 4, 4, 3, 2));
    }

    @Test
    public void testInvalidMove() {
        // Проверка, что конь не может ходить по прямой или диагонали
        assertFalse("Конь не должен ходить по прямой", horse.canMoveToPosition(chessBoard, 4, 4, 4, 6));
        assertFalse("Конь не должен ходить по диагонали", horse.canMoveToPosition(chessBoard, 4, 4, 5, 5));
        assertFalse("Конь не должен оставаться на той же позиции", horse.canMoveToPosition(chessBoard, 4, 4, 4, 4));
    }

    @Test
    public void testMoveOutOfBoard() {
        // Проверка, что конь не может выйти за границы доски
        assertFalse("Конь не должен ходить за границы доски", horse.canMoveToPosition(chessBoard, 4, 4, 8, 5));
        assertFalse("Конь не должен ходить за границы доски", horse.canMoveToPosition(chessBoard, 4, 4, -1, 3));
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Добавляем черную фигуру на пути коня и проверяем, что захват возможен
        chessBoard.board[6][5] = new Pawn("Black");
        assertTrue("Конь должен захватывать фигуры противника", horse.canMoveToPosition(chessBoard, 4, 4, 6, 5));
    }

    @Test
    public void testCaptureSameColorPiece() {
        // Добавляем белую фигуру на пути коня и проверяем, что захват невозможен
        chessBoard.board[6][5] = new Pawn("White");
        assertFalse("Конь не должен захватывать фигуры того же цвета", horse.canMoveToPosition(chessBoard, 4, 4, 6, 5));
    }
}