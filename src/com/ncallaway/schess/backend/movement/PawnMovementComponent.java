package com.ncallaway.schess.backend.movement;

import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.data.Board.BoardPosition;
import com.ncallaway.schess.backend.data.Piece.Color;

public class PawnMovementComponent extends MovementComponent {

  @Override
  public void addPossibleMoves(Piece piece, Set<Integer> possibleMoves) {
    final BoardPosition position = Board.boardPositionFromIndex(piece.getPosition());
    if (piece.getColor() == Color.BLACK) {
      if (tryToAddMove(-1, 0, piece, possibleMoves)) {
        if (position.getBlackRank() == 1) {
          tryToAddMove(-2, 0, piece, possibleMoves);
        }
      }

      tryToAddAttack(-1, -1, piece, possibleMoves);
      tryToAddAttack(-1, 1, piece, possibleMoves);
    } else {
      if (tryToAddMove(1, 0, piece, possibleMoves)) {
        if (position.getWhiteRank() == 1) {
          tryToAddMove(2, 0, piece, possibleMoves);
        }
      }

      tryToAddAttack(1, -1, piece, possibleMoves);
      tryToAddAttack(1, 1, piece, possibleMoves);
    }
  }

  private boolean tryToAddMove(int rankDelta, int fileDelta, Piece piece, Set<Integer> possibleMoves) {
    final BoardPosition position = Board.boardPositionFromIndex(piece.getPosition());

    final int rank = position.getWhiteRank() + rankDelta;
    final int file = position.getFile() + fileDelta;

    if (!Board.isOnBoard(rank, file)) {
      return false;
    }

    if (piece.getBoard().getPieceAt(rank, file).getColor() == Color.NO_COLOR) {
      possibleMoves.add(Board.indexFromBoardPosition(rank, file));
      return true;
    }

    return false;
  }

  private void tryToAddAttack(int rankDelta, int fileDelta, Piece piece, Set<Integer> possibleMoves) {
    final BoardPosition position = Board.boardPositionFromIndex(piece.getPosition());

    final int rank = position.getWhiteRank() + rankDelta;
    final int file = position.getFile() + fileDelta;

    if (!Board.isOnBoard(rank, file)) {
      return;
    }

    final Color targetColor = piece.getBoard().getPieceAt(rank, file).getColor();

    if (targetColor != Color.NO_COLOR && targetColor != piece.getColor()) {
      possibleMoves.add(Board.indexFromBoardPosition(rank, file));
    }
  }

}
