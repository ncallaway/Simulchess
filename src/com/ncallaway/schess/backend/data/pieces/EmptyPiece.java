package com.ncallaway.schess.backend.data.pieces;

import java.util.HashSet;
import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.interfaces.PieceVisitor;

public class EmptyPiece extends Piece {

  public EmptyPiece() {
    super(Color.NO_COLOR);
  }

  @Override
  public void accept(PieceVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public Set<Integer> generatePossibleMoves(Board board, int position) {
    return new HashSet<Integer>();
  }

}
