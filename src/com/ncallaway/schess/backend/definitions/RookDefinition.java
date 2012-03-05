package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;

public class RookDefinition extends PieceDefinition {

  public RookDefinition() {
    addHorizontalMovementComponents();
  }

  @Override
  public void accept(PieceDefinitionVisitor visitor) {
    visitor.visit(this);
  }
}
