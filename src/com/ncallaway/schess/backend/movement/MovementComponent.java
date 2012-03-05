package com.ncallaway.schess.backend.movement;

import java.util.Set;

import com.ncallaway.schess.backend.data.Piece;

public abstract class MovementComponent {
    
    public abstract void addPossibleMoves(final Piece piece, Set<Integer> possibleMoves); 
}
