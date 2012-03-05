package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;
import com.ncallaway.schess.backend.movement.KnightMovementComponent;

public class KnightDefinition extends PieceDefinition {

    public KnightDefinition() {
        movementComponents.add(new KnightMovementComponent());
    }

    @Override
    public void accept(PieceDefinitionVisitor visitor) {
        visitor.visit(this);
    }
}
