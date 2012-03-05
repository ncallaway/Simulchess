package com.ncallaway.schess.backend.movement;

import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.data.Board.BoardPosition;

public class KnightMovementComponent extends MovementComponent {

  @Override
  public void addPossibleMoves(Piece piece, Set<Integer> possibleMoves) {
    tryToAddMove(1, 2, piece, possibleMoves);
    tryToAddMove(2, 1, piece, possibleMoves);
    tryToAddMove(2, -1, piece, possibleMoves);
    tryToAddMove(1, -2, piece, possibleMoves);
    
    tryToAddMove(-1, -2, piece, possibleMoves);
    tryToAddMove(-2, -1, piece, possibleMoves);
    tryToAddMove(-2, 1, piece, possibleMoves);
    tryToAddMove(-1, 2, piece, possibleMoves);
  }
  
  private void tryToAddMove(int rankDelta, int fileDelta, Piece piece, Set<Integer> possibleMoves) {
    final BoardPosition position = Board.boardPositionFromIndex(piece.getPosition());

    final int rank = position.getWhiteRank() + rankDelta;
    final int file = position.getFile() + fileDelta;

    if (!Board.isOnBoard(rank, file)) {
      return;
    }
    
    if (piece.getBoard().getPieceAt(rank, file).getColor() != piece.getColor()) {
      possibleMoves.add(Board.indexFromBoardPosition(rank, file));
    }
  }
}
