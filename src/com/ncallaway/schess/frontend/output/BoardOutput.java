package com.ncallaway.schess.frontend.output;

import java.util.Set;

import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.factory.PieceFactory;

public class BoardOutput {

  public static void printBoard(Board board) {
    for (int rank=Board.NUMBER_RANKS-1; rank >= 0; rank--) {
      printHorizontalDivider();
      for (int file=0; file < Board.NUMBER_FILES; file++) {
        printVerticalDivider();
        printPiece(board.getPieceAt(rank, file));
      }
      printVerticalDivider();
      System.out.print("\n");
    }
    printHorizontalDivider();
  }
  
  public static void printAvailbleMoves(Board board, Piece piece) {
    final Set<Integer> slots = piece.generateLegalMoves();
    
    for (int rank=Board.NUMBER_RANKS-1; rank >= 0; rank--) {
      printHorizontalDivider();
      for (int file=0; file < Board.NUMBER_FILES; file++) {
        printVerticalDivider();
        
        if (slots.contains(Board.indexFromBoardPosition(rank, file))) {
          System.out.print("x ");
        } else {
          printPiece(board.getPieceAt(rank, file));
        }
      }
      printVerticalDivider();
      System.out.print("\n");
    }
    printHorizontalDivider();
  }

  private static void printHorizontalDivider() {
    for (int i=0; i<=4*Board.NUMBER_FILES; i++) {
      System.out.print("-");
    }

    System.out.print("\n");
  }

  private static void printVerticalDivider() {
    System.out.print("| ");
  }

  private static void printPiece(Piece piece) {
    System.out.print(PieceFactory.charFromPiece(piece));
    System.out.print(" ");
  }
}
