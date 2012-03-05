package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;

public class BishopDefinition extends PieceDefinition {

    public BishopDefinition() {
        addDiagonalMovementComponents();
    }

    @Override
    public void accept(PieceDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}
