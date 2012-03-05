package com.ncallaway.schess.backend.controller;

import com.ncallaway.schess.backend.api.GameResponse;
import com.ncallaway.schess.backend.api.GameType;
import com.ncallaway.schess.backend.api.IllegalMoveException;
import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Board.BoardPosition;
import com.ncallaway.schess.backend.data.Game;
import com.ncallaway.schess.backend.data.Piece;
import com.ncallaway.schess.backend.data.Piece.Color;
import com.ncallaway.schess.backend.data.storage.GameStorage;
import com.ncallaway.schess.backend.definitions.KingDefinition;
import com.ncallaway.schess.backend.factory.GameFactory;

public class GameManager {

  private GameStorage storage = new GameStorage();

  public GameResponse startGame(GameType type) {
    String guid = storage.generateGameGuid();

    Game result;

    switch (type) {
    case STANDARD:
      result = GameFactory.createStandardGame(guid);
      break;
    default:
      throw new IllegalArgumentException("Unknown GameType: " + type);
    }
    
    storage.storeGame(guid, result);

    return createResponseFromGame(result);

  }

  public GameResponse performTurn(String gameId, BoardPosition start, BoardPosition end) throws IllegalMoveException {
    final Game game = storage.lookupGame(gameId);

    if (game == null) {
      throw new IllegalArgumentException("No game found with gameId: " + gameId);
    }
    
    if (!isLegalMove(game.getBoard(), game.getTurn(), start, end)) {
      throw new IllegalMoveException(game.getTurn() + " attempted an illegal move.");
    }
    
    final Board board = game.getBoard();
    final int startIndex = Board.indexFromBoardPosition(start);
    final int endIndex = Board.indexFromBoardPosition(end);
    
    final Piece movedPiece = board.getPieceAt(startIndex);
    board.putPiece(movedPiece, endIndex);
    board.removePiece(startIndex);
    movedPiece.setPosition(endIndex);
    
    if (game.getTurn() == Color.WHITE) {
      game.setColor(Color.BLACK);
    } else {
      game.setColor(Color.WHITE);
    }
    
    storage.storeGame(gameId, game);
    
    return createResponseFromGame(game);
  }

  public static boolean isCheck(Board board, Color threatenedSide) {
    final Piece king = getKing(board,threatenedSide);

    for (int i=0; i<Board.BOARD_SIZE; i++) {
      Piece p = board.getPieceAt(i);

      if (p.getColor() != Color.NO_COLOR && p.getColor() != threatenedSide) {
        if (isThreateningSquare(p, king.getPosition())) {
          return true;
        }
      }
    }

    return false;
  }

  private GameResponse createResponseFromGame(Game input) {

    boolean isCheck = isCheck(input.getBoard(), input.getTurn());
    boolean legalMovesAvailable = areLegalMovesAvailable(input.getBoard(), input.getTurn());

    boolean isCheckmate = isCheck && !legalMovesAvailable;
    boolean isStalemate = !isCheck && !legalMovesAvailable;
    
    Color winner = Color.NO_COLOR;
    
    if (isCheckmate) {
      if (input.getTurn() == Color.WHITE) {
        winner = Color.BLACK;
      } else {
        winner = Color.WHITE;
      }
    }

    return new GameResponse(input.getId(), input.getBoard(), input.getTurn(), isCheck, isCheckmate, isStalemate, winner);
  }
  
  private static boolean isLegalMove(Board board, Color turnTaker, BoardPosition start, BoardPosition end) {
    final Piece movedPiece = board.getPieceAt(start);
    
    if (movedPiece.getColor() != turnTaker) {
      return false;
    }
    
    if (!movedPiece.generateLegalMoves().contains(Board.indexFromBoardPosition(end))) {
      return false;
    }
    
    return true;
  }

  private static boolean areLegalMovesAvailable(Board board, Color turnTaker) {
    for (int i=0; i<Board.BOARD_SIZE; i++) {
      Piece p = board.getPieceAt(i);

      if (p.getColor() != Color.NO_COLOR && p.getColor() == turnTaker) {
        if (!p.generateLegalMoves().isEmpty()) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean isThreateningSquare(Piece piece, int position) {
    return piece.generateAllMoves().contains(position);
  }

  private static Piece getKing(Board board, Color kingColor) {
    for (int i=0; i<Board.BOARD_SIZE; i++) {
      Piece p = board.getPieceAt(i);

      /* TODO: Using getClass() to check the piece's type?? What a HACK!!! Fix this. */
      if (p.getColor() == kingColor && p.getDefinition().getClass() == KingDefinition.class) {
        return p;
      }
    }

    return null;
  }

  //  Color color = Color.WHITE;
  //
  //  while (color != Color.NO_COLOR) {
  //    BoardOutput.printBoard(std);
  //    System.out.print("Enter " + color + " piece coordinates: ");
  //
  //    boolean invalidInput = true;
  //    Piece selectedPiece = null;
  //    while (invalidInput) {
  //      try {
  //        int first = Integer.parseInt( Character.toString((char)buffer.read()) );
  //        int second = Integer.parseInt( Character.toString((char)buffer.read()) );
  //
  //        if (Board.isOnBoard(first, second)) {
  //          selectedPiece = std.getPieceAt(first, second);
  //          if (selectedPiece.getColor() == color) {
  //            invalidInput = false;
  //          }
  //        }
  //
  //        buffer.readLine();
  //      } catch (NumberFormatException e) {
  //        buffer.readLine();
  //      }
  //    }
  //    
  //    if (selectedPiece.generatePossibleMoves().isEmpty()) {
  //      System.out.println("No valid moves from that piece");
  //      continue;
  //    }
  //
  //    BoardOutput.printAvailbleMoves(std, selectedPiece);
  //
  //    System.out.print("Enter destination coordinates: ");
  //
  //    invalidInput = true;
  //    int index = -1;
  //    while (invalidInput) {
  //      try {
  //        char fChar = (char)buffer.read();
  //        char sChar = (char)buffer.read();
  //
  //        if (fChar == 'x' && sChar == 'x') {
  //          index = -1;
  //          invalidInput = false;
  //          break;
  //        }
  //
  //        int first = Integer.parseInt( Character.toString(fChar) );
  //        int second = Integer.parseInt( Character.toString(sChar) );
  //
  //        if (Board.isOnBoard(first, second)) {
  //          index = Board.indexFromBoardPosition(first, second);
  //          if (selectedPiece.generatePossibleMoves().contains(index)) {
  //            invalidInput = false;
  //          }
  //        }
  //        buffer.readLine();
  //      } catch (NumberFormatException e) {
  //        buffer.readLine();
  //      }
  //    }
  //
  //    if (index != -1) {
  //      int previousIndex = selectedPiece.getPosition();
  //
  //      selectedPiece.setPosition(index);
  //      std.putPiece(selectedPiece, index);
  //      std.removePiece(previousIndex);
  //      
  //      if (color == Color.WHITE) {
  //        color = Color.BLACK;
  //      } else {
  //        color = Color.WHITE;
  //      }
  //    }


}
