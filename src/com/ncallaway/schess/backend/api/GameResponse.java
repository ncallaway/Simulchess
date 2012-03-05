package com.ncallaway.schess.backend.api;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece.Color;

public class GameResponse {
  private final String gameId;
  private final Board board;
  private final Color turn;
  private final Color winner;
  
  private final boolean isCheck;
  private final boolean isCheckmate;
  private final boolean isStalemate;
  
  public GameResponse(final String gameId, final Board board, final Color turn, final boolean isCheck, final boolean isCheckmate, final boolean isStalemate, final Color winner) {
    this.gameId = gameId;
    this.board = board;
    this.turn = turn;
    this.winner = winner;
    
    this.isCheck = isCheck;
    this.isCheckmate = isCheckmate;
    this.isStalemate = isStalemate;
    
  }
  
  public String getGameId() {
    return gameId;
  }
  
  public Board getBoard() {
    return this.board;
  }
  
  public Color getTurn() {
    return this.turn;
  }
  
  public Color getWinner() {
    return winner;
  }

  public boolean isCheck() {
    return isCheck;
  }

  public boolean isCheckmate() {
    return isCheckmate;
  }

  public boolean isStalemate() {
    return isStalemate;
  }
}
