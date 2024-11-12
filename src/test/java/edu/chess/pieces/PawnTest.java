package test.java.edu.chess.pieces;

import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        // Инициализация объектов Pawn и доски ChessBoard
        whitePawn = new Pawn("White");
        blackPawn = new Pawn("Black");
        chessBoard = new ChessBoard("White");
        chessBoard.board[1][4] = whitePawn; // Устанавливаем белую пешку на позицию (1, 4)
        chessBoard.board[6][4] = blackPawn; // Устанавливаем черную пешку на позицию (6, 4)
    }

    @Test
    public void testGetColor() {
        // Проверка, что метод getColor() возвращает правильный цвет
        assertEquals("Цвет белой пешки должен быть белым", "White", whitePawn.getColor());
        assertEquals("Цвет черной пешки должен быть черным", "Black", blackPawn.getColor());
    }

    @Test
    public void testValidMoveForwardOneStep() {
        // Проверка, что пешка может двигаться вперед на одну клетку
        assertTrue("Белая пешка должна уметь ходить на одну клетку вперед",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 2, 4));
        assertTrue("Черная пешка должна уметь ходить на одну клетку вперед",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 5, 4));
    }

    @Test
    public void testValidMoveForwardTwoSteps() {
        // Проверка, что пешка может двигаться вперед на две клетки с начальной позиции
        assertTrue("Белая пешка должна уметь ходить на две клетки вперед с начальной позиции",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 3, 4));
        assertTrue("Черная пешка должна уметь ходить на две клетки вперед с начальной позиции",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 4, 4));
    }

    @Test
    public void testInvalidMoveBackward() {
        // Проверка, что пешка не может двигаться назад
        assertFalse("Белая пешка не должна двигаться назад",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 0, 4));
        assertFalse("Черная пешка не должна двигаться назад",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 7, 4));
    }

    @Test
    public void testInvalidMoveSideways() {
        // Проверка, что пешка не может двигаться вбок
        assertFalse("Белая пешка не должна двигаться вбок",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 1, 5));
        assertFalse("Черная пешка не должна двигаться вбок",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 6, 3));
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Проверка, что пешка может захватывать фигуры противника по диагонали
        chessBoard.board[2][5] = new Pawn("Black");
        assertTrue("Белая пешка должна уметь захватывать фигуры противника по диагонали",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 2, 5));

        chessBoard.board[5][3] = new Pawn("White");
        assertTrue("Черная пешка должна уметь захватывать фигуры противника по диагонали",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 5, 3));
    }

    @Test
    public void testCaptureSameColorPiece() {
        // Проверка, что пешка не может захватывать фигуры своего цвета
        chessBoard.board[2][5] = new Pawn("White");
        assertFalse("Белая пешка не должна захватывать фигуры своего цвета",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 2, 5));

        chessBoard.board[5][3] = new Pawn("Black");
        assertFalse("Черная пешка не должна захватывать фигуры своего цвета",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, 5, 3));
    }

    @Test
    public void testMoveOutOfBoard() {
        // Проверка, что пешка не может выйти за границы доски
        assertFalse("Пешка не должна выходить за границы доски",
                whitePawn.canMoveToPosition(chessBoard, 1, 4, 8, 4));
        assertFalse("Пешка не должна выходить за границы доски",
                blackPawn.canMoveToPosition(chessBoard, 6, 4, -1, 4));
    }
}
