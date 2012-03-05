package com.ncallaway.schess.backend.data;

import com.ncallaway.schess.backend.data.pieces.Piece;

public class Board {
  public static final int NUMBER_RANKS = 8;
  public static final int NUMBER_FILES = 8;
  public static int BOARD_SIZE = NUMBER_RANKS * NUMBER_FILES;

  private static BoardPosition[] boardPositions = new BoardPosition[BOARD_SIZE];

  /**
   * Represents the board. Squares are stored in the board by rank, and then by file. Pieces in squares in white's first rank
   * are stored in 0..NUMBER_FILES-1. Pieces in squares in white's second rank are stored in NUMBER_FILES..2*NUM_FILES-1.
   * Pieces in squares in black's first rank are stored in (NUMBER_RANKS-1)*(NUMBER_FILES)..(NUMBER_FILES)*(NUMBER_RANKS)-1.
   * 
   * Pieces in squares stored at an arbitrary rank and file are stored at index: (rank*NUMBER_FILES)+file.
   * 
   * Ranks are numbered in the board by white's perspective. That is, white rank 0 is the board's rank 0. Black's rank 0 is
   * the board's rank 7.
   */
  private Piece[] board;

  public Board() {
    board = new Piece[BOARD_SIZE];
  }

  public Piece getPieceAt(final int index) {
    if (index < 0 || index >= BOARD_SIZE) {
      throw new IllegalArgumentException("index was out of bounds: " + index);
    }

    return board[index];
  }

  public Piece getPieceAt(final int whiteRank, final int file) {
    if (whiteRank < 0 || whiteRank >= NUMBER_RANKS) {
      throw new IllegalArgumentException("whiteRank was out of bounds: " + whiteRank);
    }

    if (file < 0 || file >= NUMBER_FILES) {
      throw new IllegalArgumentException("file was out of bounds: " + file);
    }

    return getPieceAt(indexFromBoardPosition(whiteRank, file));
  }

  public Piece getPieceAt(final BoardPosition position) {
    return getPieceAt(position.getWhiteRank(), position.getFile());
  }

  public static int indexFromBoardPosition(final BoardPosition position) {
    return indexFromBoardPosition(position.getWhiteRank(), position.getFile());
  }

  public static int indexFromBoardPosition(final int whiteRank, final int file) {
    return (whiteRank * NUMBER_FILES) + file;
  }

  public static BoardPosition boardPositionFromIndex(final int index) {
    if (index < 0 || index > BOARD_SIZE) {
      throw new IllegalArgumentException("index was out of bounds: " + index);
    }

    if (boardPositions[index] == null) {
      boardPositions[index] = new BoardPosition(index / NUMBER_FILES, index % NUMBER_FILES);
    }

    return boardPositions[index];
  }

  public static BoardPosition boardPositionFrom(final int whiteRank, final int file) {
    final int index = indexFromBoardPosition(whiteRank, file);
    return boardPositionFromIndex(index);
  }

  public static class BoardPosition {
    private final int whiteRank;
    private final int file;

    public BoardPosition(int whiteRank, int file) {
      this.whiteRank = whiteRank;
      this.file = file;
    }

    public int getWhiteRank() {
      return whiteRank;
    }

    public int getBlackRank() {
      return NUMBER_RANKS - whiteRank - 1;
    }

    public int getFile() {
      return file;
    }
  }
}
