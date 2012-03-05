package com.ncallaway.schess.backend.data.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ncallaway.schess.backend.data.Game;

public class GameStorage {
  private Map<String, Game> gameMap = new HashMap<String, Game>();
  
  public Game lookupGame(String guid) {
    return gameMap.get(guid);
  }
  
  public void storeGame(String guid, Game game) {
    gameMap.put(guid, game);
  }
  
  public String generateGameGuid() {
    String guid = UUID.randomUUID().toString();
    
    while (gameMap.containsKey(guid)) {
      guid = UUID.randomUUID().toString();
    }
    
    return guid;
  }
}
