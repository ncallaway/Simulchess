package com.ncallaway.schess.backend.movement;

import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Board.BoardPosition;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.data.Piece.Color;

public class LinearMovementComponent extends MovementComponent {

    public static final int MAXIMUM_MOVEMENT = Integer.MAX_VALUE;

    private final int maximumDistance;
    
    private final int rankIncrementor;
    private final int fileIncrementor;

    public LinearMovementComponent(final int rankIncrementor, final int fileIncrementor) {
        this(rankIncrementor, fileIncrementor, MAXIMUM_MOVEMENT);
    }

    public LinearMovementComponent(final int rankIncrementor, final int fileIncrementor, int maximumDistance) {
        if (Math.abs(rankIncrementor) > 1) {
            throw new IllegalArgumentException("rankIncrementor must be -1, 0, or 1");
        }
        
        if (Math.abs(fileIncrementor) > 1) {
            throw new IllegalArgumentException("fileIncrementor must be -1, 0, or 1");
        }
        
        this.maximumDistance = maximumDistance;
        this.rankIncrementor = rankIncrementor;
        this.fileIncrementor = fileIncrementor;
    }

    @Override
    public void addPossibleMoves(final Piece piece, Set<Integer> possibleMoves) {

        final BoardPosition piecePosition = Board.boardPositionFromIndex(piece.getPosition());
        
        int rank = piecePosition.getWhiteRank() + rankIncrementor;
        int file = piecePosition.getFile() + fileIncrementor;
        
        for (int count = 0; count < maximumDistance; rank += rankIncrementor, file += fileIncrementor, count++) {
            if (rank < 0 || rank >= Board.NUMBER_RANKS || file < 0 || file >= Board.NUMBER_FILES) {
                break;
            }
            
            final Piece at = piece.getBoard().getPieceAt(rank, file);
            
            if (at.getColor() == Color.NO_COLOR || at.getColor() != piece.getColor()) {
                possibleMoves.add(Board.indexFromBoardPosition(rank, file));
            }
            
            if (at.getColor() != Color.NO_COLOR) {
                break;
            }
        }
    }
}
