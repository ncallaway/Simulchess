package com.ncallaway.schess.backend.factory;

import com.ncallaway.schess.backend.data.Game;
import com.ncallaway.schess.backend.data.Piece.Color;

public class GameFactory {
  public static Game createStandardGame(final String guid) {
    return new Game(guid, BoardFactory.createStandardBoard(), Color.WHITE);
  }
}
