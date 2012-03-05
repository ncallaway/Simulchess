package com.ncallaway.schess.backend.definitions;

import com.ncallaway.schess.backend.interfaces.PieceDefinitionVisitor;
import com.ncallaway.schess.backend.movement.LinearMovementComponent;

public class KingDefinition extends PieceDefinition {

    public KingDefinition() {
        movementComponents.add(new LinearMovementComponent(0, 1, 1));
        movementComponents.add(new LinearMovementComponent(1, 0, 1));
        movementComponents.add(new LinearMovementComponent(0, -1, 1));
        movementComponents.add(new LinearMovementComponent(-1, 0, 1));
    
        movementComponents.add(new LinearMovementComponent(1, 1, 1));
        movementComponents.add(new LinearMovementComponent(1, -1, 1));
        movementComponents.add(new LinearMovementComponent(-1, -1, 1));
        movementComponents.add(new LinearMovementComponent(-1, 1, 1));
    }

    @Override
    public void accept(PieceDefinitionVisitor visitor) {
        visitor.visit(this);

    }

}
