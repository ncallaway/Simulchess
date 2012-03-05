package com.ncallaway.schess.backend.data.pieces;

import java.util.HashSet;
import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Board.BoardPosition;
import com.ncallaway.schess.backend.interfaces.PieceVisitor;

public class Rook extends Piece {

  public Rook(Color color) {
    super(color);
  }

  @Override
  public void accept(PieceVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public Set<Integer> generatePossibleMoves(Board board, int position) {
    final HashSet<Integer> moves = new HashSet<Integer>();

    final BoardPosition rookPosition = Board.boardPositionFromIndex(position);

    /* Add all horizontal moves */
    for (int i = rookPosition.getFile() + 1; i < Board.NUMBER_FILES; i++) {
      final BoardPosition possibleMovePosition = Board.boardPositionFrom(rookPosition.getWhiteRank(), rookPosition.getFile());
      final Piece at = board.getPieceAt(possibleMovePosition);
      if (at.getColor() == Color.NO_COLOR) {
        moves.add(Board.indexFromBoardPosition(possibleMovePosition));
      } else if (at.getColor() != getColor()) {
        moves.add(Board.indexFromBoardPosition(possibleMovePosition));
        break;
      } else {
        break;
      }
    }

    return moves;
  }
}
