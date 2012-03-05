package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;
import com.ncallaway.schess.backend.movement.PawnMovementComponent;

public class PawnDefinition extends PieceDefinition {

    public PawnDefinition() {
        movementComponents.add(new PawnMovementComponent());
    }

    @Override
    public void accept(PieceDefinitionVisitor visitor) {
        visitor.visit(this);
    }

}
