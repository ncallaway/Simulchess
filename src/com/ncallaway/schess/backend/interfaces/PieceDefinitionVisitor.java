package com.ncallaway.schess.backend.interfaces;

import com.ncallaway.schess.backend.definitions.BishopDefinition;
import com.ncallaway.schess.backend.definitions.EmptyPieceDefinition;
import com.ncallaway.schess.backend.definitions.KingDefinition;
import com.ncallaway.schess.backend.definitions.KnightDefinition;
import com.ncallaway.schess.backend.definitions.PawnDefinition;
import com.ncallaway.schess.backend.definitions.QueenDefinition;
import com.ncallaway.schess.backend.definitions.RookDefinition;

public interface PieceDefinitionVisitor {

    void visit(KingDefinition king);

    void visit(QueenDefinition queen);

    void visit(RookDefinition rook);

    void visit(BishopDefinition bishop);
    
    void visit(KnightDefinition knight);

    void visit(PawnDefinition pawn);
    
    void visit(EmptyPieceDefinition empty);
}
