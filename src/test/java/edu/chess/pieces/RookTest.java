package test.java.edu.chess.pieces;


import main.java.edu.chess.board.ChessBoard;
import main.java.edu.chess.pieces.Pawn;
import main.java.edu.chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class RookTest {

    private Rook rook;
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        rook = new Rook("White");
        chessBoard = new ChessBoard("White");
        chessBoard.board[4][4] = rook; // Устанавливаем ладью на позицию (4, 4)
    }

    @Test
    public void testGetColor() {
        assertEquals("Цвет ладьи должен быть белым",
                "White", rook.getColor());
    }

    @Test
    public void testValidMoveStraight() {
        assertTrue("Ладья должна уметь ходить по вертикали",
                rook.canMoveToPosition(chessBoard, 4, 4, 7, 4));
        assertTrue("Ладья должна уметь ходить по горизонтали",
                rook.canMoveToPosition(chessBoard, 4, 4, 4, 1));
    }

    @Test
    public void testInvalidMoveDiagonal() {
        assertFalse("Ладья не должна ходить по диагонали",
                rook.canMoveToPosition(chessBoard, 4, 4, 6, 6));
    }

    @Test
    public void testMoveOutOfBoard() {
        assertFalse("Ладья не должна выходить за границы доски",
                rook.canMoveToPosition(chessBoard, 4, 4, 8, 4));
        assertFalse("Ладья не должна выходить за границы доски",
                rook.canMoveToPosition(chessBoard, 4, 4, -1, 4));
    }

    @Test
    public void testSamePosition() {
        assertFalse("Ладья не должна оставаться на той же позиции",
                rook.canMoveToPosition(chessBoard, 4, 4, 4, 4));
    }

    @Test
    public void testCaptureOpponentPiece() {
        chessBoard.board[7][4] = new Pawn("Black");
        assertTrue("Ладья должна захватывать фигуры противника",
                rook.canMoveToPosition(chessBoard, 4, 4, 7, 4));
    }

    @Test
    public void testCaptureSameColorPiece() {
        chessBoard.board[7][4] = new Pawn("White");
        assertFalse("Ладья не должна захватывать фигуры того же цвета",
                rook.canMoveToPosition(chessBoard, 4, 4, 7, 4));
    }

    @Test
    public void testBlockedPath() {
        chessBoard.board[5][4] = new Pawn("White");
        assertFalse("Ладья не должна проходить через другие фигуры",
                rook.canMoveToPosition(chessBoard, 4, 4, 7, 4));
    }
}
