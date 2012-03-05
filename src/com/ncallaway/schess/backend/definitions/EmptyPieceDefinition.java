package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;

public class EmptyPieceDefinition extends PieceDefinition {

  public EmptyPieceDefinition() {
  }

  @Override
  public void accept(PieceDefinitionVisitor visitor) {
    visitor.visit(this);
  }
}
