package test.java.edu.chess.pieces;


import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.Pawn;
import main.java.edu.chess.pieces.Queen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class QueenTest {

    private Queen queen;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        queen = new Queen("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[4][4] = queen; // Устанавливаем ферзя на позицию (4, 4)
    }

    @Test
    public void testGetColor() {
        assertEquals("Цвет ферзя должен быть белым", "White", queen.getColor());
    }

    @Test
    public void testValidMoveDiagonal() {
        assertTrue("Ферзь должен уметь ходить по диагонали",
                queen.canMoveToPosition(chessBoard, 4, 4, 6, 6));
        assertTrue("Ферзь должен уметь ходить по диагонали",
                queen.canMoveToPosition(chessBoard, 4, 4, 2, 2));
    }

    @Test
    public void testValidMoveStraight() {
        assertTrue("Ферзь должен уметь ходить по вертикали",
                queen.canMoveToPosition(chessBoard, 4, 4, 7, 4));
        assertTrue("Ферзь должен уметь ходить по горизонтали",
                queen.canMoveToPosition(chessBoard, 4, 4, 4, 1));
    }

    @Test
    public void testInvalidMove() {
        assertFalse("Ферзь не должен ходить в недопустимую позицию",
                queen.canMoveToPosition(chessBoard, 4, 4, 5, 6));
    }

    @Test
    public void testMoveOutOfBoard() {
        assertFalse("Ферзь не должен выходить за границы доски",
                queen.canMoveToPosition(chessBoard, 4, 4, 8, 8));
        assertFalse("Ферзь не должен выходить за границы доски",
                queen.canMoveToPosition(chessBoard, 4, 4, -1, 4));
    }

    @Test
    public void testSamePosition() {
        assertFalse("Ферзь не должен оставаться на той же позиции",
                queen.canMoveToPosition(chessBoard, 4, 4, 4, 4));
    }

    @Test
    public void testCaptureOpponentPiece() {
        chessBoard.board[6][6] = new Pawn("Black");
        assertTrue("Ферзь должен захватывать фигуры противника",
                queen.canMoveToPosition(chessBoard, 4, 4, 6, 6));
    }

    @Test
    public void testCaptureSameColorPiece() {
        chessBoard.board[6][6] = new Pawn("White");
        assertFalse("Ферзь не должен захватывать фигуры того же цвета",
                queen.canMoveToPosition(chessBoard, 4, 4, 6, 6));
    }

    @Test
    public void testBlockedPath() {
        chessBoard.board[5][5] = new Pawn("White");
        assertFalse("Ферзь не должен проходить через другие фигуры",
                queen.canMoveToPosition(chessBoard, 4, 4, 6, 6));
    }
}
