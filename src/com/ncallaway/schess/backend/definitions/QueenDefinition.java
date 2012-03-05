package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;

public class QueenDefinition extends PieceDefinition {

    public QueenDefinition() {        
        addHorizontalMovementComponents();
        addDiagonalMovementComponents();
    }

    @Override
    public void accept(PieceDefinitionVisitor visitor) {
        visitor.visit(this);
    }

}
