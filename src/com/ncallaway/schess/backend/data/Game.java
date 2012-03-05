package com.ncallaway.schess.backend.data;

import com.ncallaway.schess.backend.data.Piece.Color;

public class Game {
  private final String id;
  private final Board board;
  private Color turn;
  
  /* en-passant positions */
  /* isCheck */
  /* isCheckmate */
  /* isStalemate */
  
  public Game(String id, Board board, Color turn) {
    this.id = id;
    this.board = board;
    this.turn = turn;
  }
  
  public String getId() {
    return id;
  }
  
  public Board getBoard() {
    return board;
  }
  
  public Color getTurn() {
    return turn;
  }
  
  public void setColor(Color turn) {
    this.turn = turn;
  }
}
