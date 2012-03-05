package com.ncallaway.schess.backend.data.pieces;

import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.interfaces.PieceVisitor;

public abstract class Piece {

  public enum Color {
    WHITE, BLACK, NO_COLOR
  }

  private final Color color;

  public Piece(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public abstract void accept(PieceVisitor visitor);

  public abstract Set<Integer> generatePossibleMoves(Board board, int position);
}
