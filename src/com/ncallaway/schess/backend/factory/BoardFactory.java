package com.ncallaway.schess.backend.factory;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;

public class BoardFactory {

  private static final String STANDARD_BOARD = "rnbqkbnrpppppppp                                PPPPPPPPRNBQKBNR";

  public static Board createStandardBoard() {
    return createBoardFromCanonicalNotation(STANDARD_BOARD);
  }

  public static Board createBoardFromCanonicalNotation(String canonicalNotation) {
    final Board board = new Board();
    
    for (int i=0; i < Board.BOARD_SIZE; i++) {
      final char charAt = canonicalNotation.charAt(i);
      final Piece pieceAt = PieceFactory.createPieceFromCharacter(charAt, i, board);
      board.putPiece(pieceAt, i);
    }
    
    return board;
  }
  
  public static Board createBoardFromOther(Board other) {
    final Board board = new Board();
    
    for (int i=0; i < Board.BOARD_SIZE; i++) {
      final char charAt = PieceFactory.charFromPiece(other.getPieceAt(i));
      final Piece pieceAt = PieceFactory.createPieceFromCharacter(charAt, i, board);
      board.putPiece(pieceAt, i);
    }
    
    return board;
  }
}
