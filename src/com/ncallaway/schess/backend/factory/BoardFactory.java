package com.ncallaway.schess.backend.factory;

import com.ncallaway.schess.backend.data.Board;

public class BoardFactory {

  private static final String STANDARD_BOARD = "rnbqkbnrpppppppp                                PPPPPPPPRNBQKBNR";

  public static Board createStandardBoard() {
    return createBoardFromCanonicalNotation(STANDARD_BOARD);
  }

  public static Board createBoardFromCanonicalNotation(String canonicalNotation) {
    return new Board();
  }
}
