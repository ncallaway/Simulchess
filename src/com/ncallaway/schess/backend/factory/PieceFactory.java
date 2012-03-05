package com.ncallaway.schess.backend.factory;

import java.util.HashMap;
import java.util.Map;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.data.Piece.Color;
import com.ncallaway.schess.backend.definitions.BishopDefinition;
import com.ncallaway.schess.backend.definitions.EmptyPieceDefinition;
import com.ncallaway.schess.backend.definitions.KingDefinition;
import com.ncallaway.schess.backend.definitions.KnightDefinition;
import com.ncallaway.schess.backend.definitions.PawnDefinition;
import com.ncallaway.schess.backend.definitions.PieceDefinition;
import com.ncallaway.schess.backend.definitions.QueenDefinition;
import com.ncallaway.schess.backend.definitions.RookDefinition;

public class PieceFactory {

  private static Map<Character, PieceDefinition> charToDefinitionMap = new HashMap<Character, PieceDefinition>();
  private static Map<PieceDefinition, Character> definitionToCharMap = new HashMap<PieceDefinition, Character>();
  private static final char EMPTY_PIECE_KEY = ' ';

  static {
    final PieceDefinition pawn = new PawnDefinition();
    final PieceDefinition rook = new RookDefinition();
    final PieceDefinition knight = new KnightDefinition();
    final PieceDefinition bishop = new BishopDefinition();
    final PieceDefinition queen = new QueenDefinition();
    final PieceDefinition king = new KingDefinition();
    final PieceDefinition emptyPiece = new EmptyPieceDefinition();

    charToDefinitionMap.put('p', pawn);
    charToDefinitionMap.put('r', rook);
    charToDefinitionMap.put('n', knight);
    charToDefinitionMap.put('b', bishop);
    charToDefinitionMap.put('q', queen);
    charToDefinitionMap.put('k', king);
    charToDefinitionMap.put(EMPTY_PIECE_KEY, emptyPiece);
    
    definitionToCharMap.put(pawn, 'p');
    definitionToCharMap.put(rook, 'r');
    definitionToCharMap.put(knight, 'n');
    definitionToCharMap.put(bishop, 'b');
    definitionToCharMap.put(queen, 'q');
    definitionToCharMap.put(king, 'k');
    definitionToCharMap.put(emptyPiece, EMPTY_PIECE_KEY);
  }
  
  public static char charFromPiece(Piece piece) {
    char pieceChar = definitionToCharMap.get(piece.getDefinition());
    
    if (piece.getColor() == Color.BLACK) {
      pieceChar = Character.toUpperCase(pieceChar);
    }
    
    return pieceChar;
  }

  public static Piece createPieceFromCharacter(char pieceCharacter, int position, Board board) {
    final Color color = colorFromCharacter(pieceCharacter);
    final PieceDefinition definition = definitonFromCharacter(pieceCharacter);

    return new Piece(color, position, definition, board);
  }

  private static Color colorFromCharacter(char pieceCharacter) {

    final char lowerPieceCharacter = Character.toLowerCase(pieceCharacter);
    
    if (!charToDefinitionMap.containsKey(lowerPieceCharacter) || pieceCharacter == EMPTY_PIECE_KEY) {
      return Color.NO_COLOR;
    } else if (Character.isUpperCase(pieceCharacter)) {
      return Color.BLACK;
    } else {
      return Color.WHITE;
    }
  }

  private static PieceDefinition definitonFromCharacter(char pieceCharacter) {
    final char lowerPieceCharacter = Character.toLowerCase(pieceCharacter);
    if (charToDefinitionMap.containsKey(lowerPieceCharacter)) {
      return charToDefinitionMap.get(lowerPieceCharacter);
    }

    return charToDefinitionMap.get(EMPTY_PIECE_KEY);
  }
}
