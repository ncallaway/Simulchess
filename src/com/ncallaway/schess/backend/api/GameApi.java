package com.ncallaway.schess.backend.api;

import com.ncallaway.schess.backend.controller.GameManager;
import com.ncallaway.schess.backend.data.Board.BoardPosition;

public class GameApi {
  
  private static GameManager manager = new GameManager();
  
  public static GameResponse startGame(GameType type) {
    return manager.startGame(type);
  }
  
  public static GameResponse performTurn(String gameId, BoardPosition start, BoardPosition end) throws IllegalMoveException {
    return manager.performTurn(gameId, start, end);
  }
}
