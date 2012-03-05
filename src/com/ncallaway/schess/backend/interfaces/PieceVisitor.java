package com.ncallaway.schess.backend.interfaces;

import com.ncallaway.schess.backend.data.pieces.EmptyPiece;
import com.ncallaway.schess.backend.data.pieces.Rook;

public interface PieceVisitor {
  void visit(EmptyPiece empty);

  void visit(Rook rook);
}
