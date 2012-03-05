package com.ncallaway.schess.backend.definitions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ncallaway.schess.backend.controller.GameManager;
import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.factory.BoardFactory;
import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;
import com.ncallaway.schess.backend.movement.LinearMovementComponent;
import com.ncallaway.schess.backend.movement.MovementComponent;

public abstract class PieceDefinition {

  protected final List<MovementComponent> movementComponents = new ArrayList<MovementComponent>();
 
  public abstract void accept(PieceDefinitionVisitor visitor);
  
  public Set<Integer> generateAllMoves(Piece piece) {
    final Set<Integer> possibleMoves = new HashSet<Integer>();
    
    for (MovementComponent movement: movementComponents) {
        movement.addPossibleMoves(piece, possibleMoves);
    }
    
    return possibleMoves;
}

  public Set<Integer> generateLegalMoves(Piece piece) {
      final Set<Integer> possibleMoves = generateAllMoves(piece);
      
      /* Now filter out moves that put us in check */
      final Board current = piece.getBoard();
      
      for (Iterator<Integer> it = possibleMoves.iterator(); it.hasNext();) {
        int move = it.next();
        
        final Board hypothetical = BoardFactory.createBoardFromOther(current);
        final Piece hypotheticalPiece = hypothetical.getPieceAt(piece.getPosition());
        hypothetical.putPiece(hypotheticalPiece, move);
        hypothetical.removePiece(piece.getPosition());
        hypotheticalPiece.setPosition(move);
        
        if (GameManager.isCheck(hypothetical, piece.getColor())) {
          it.remove();
        }
      }
      
      return possibleMoves;
  }
  
  protected void addHorizontalMovementComponents() {
      movementComponents.add(new LinearMovementComponent(0, 1));
      movementComponents.add(new LinearMovementComponent(1, 0));
      movementComponents.add(new LinearMovementComponent(0, -1));
      movementComponents.add(new LinearMovementComponent(-1, 0));
  }
  
  protected void addDiagonalMovementComponents() {
      movementComponents.add(new LinearMovementComponent(1, 1));
      movementComponents.add(new LinearMovementComponent(1, -1));
      movementComponents.add(new LinearMovementComponent(-1, -1));
      movementComponents.add(new LinearMovementComponent(-1, 1));
  }
}
