package com.ncallaway.schess.backend.data;

import java.util.Set;

import com.ncallaway.schess.backend.definitions.PieceDefinition;

public class Piece {

    public enum Color {
        WHITE, BLACK, NO_COLOR
    }

    private final Color color;
    private final PieceDefinition definition;
    private final Board board;
    private int position;
    
    public Piece(final Color color, final int position, final PieceDefinition definition, final Board board) {
        this.color = color;
        this.position = position;
        this.definition = definition;
        this.board = board;
    }

    public Color getColor() {
        return this.color;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int index) {
      if (index < 0 || index >= Board.BOARD_SIZE) {
        throw new IllegalArgumentException("Invalid board position: " + index);
      }
      
      position = index;
    }
    
    public PieceDefinition getDefinition() {
        return definition;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public Set<Integer> generateAllMoves() {
      return definition.generateAllMoves(this);
    }
    
    public Set<Integer> generateLegalMoves() {
      return definition.generateLegalMoves(this);
    }
}
