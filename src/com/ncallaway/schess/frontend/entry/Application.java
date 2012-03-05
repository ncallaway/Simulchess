package com.ncallaway.schess.frontend.entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ncallaway.schess.backend.api.GameApi;
import com.ncallaway.schess.backend.api.GameResponse;
import com.ncallaway.schess.backend.api.GameType;
import com.ncallaway.schess.backend.api.IllegalMoveException;
import com.ncallaway.schess.backend.data.Board;
import com.ncallaway.schess.backend.data.Board.BoardPosition;
import com.ncallaway.schess.frontend.output.BoardOutput;

public class Application {
  public static void main(String[] args) throws IOException {
    BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    GameResponse response = GameApi.startGame(GameType.STANDARD);
    System.out.println("Started game: " + response.getGameId());

    while (!response.isCheckmate() && !response.isStalemate()) {
      System.out.println("TURNING!!!");
      System.out.println(response.isCheck() + ";" + response.isCheckmate() + ";" + response.isStalemate() + ";" + response.getWinner());
      BoardOutput.printBoard(response.getBoard());

      System.out.print("Enter " + response.getTurn() + " start/destination coordinates: ");

      boolean invalidInput = true;
      while (invalidInput) {
        try {
          int first = Integer.parseInt( Character.toString((char)buffer.read()) );
          int second = Integer.parseInt( Character.toString((char)buffer.read()) );

          int third = Integer.parseInt( Character.toString((char)buffer.read()) );
          int fourth = Integer.parseInt( Character.toString((char)buffer.read()) );

          if (Board.isOnBoard(first, second) && Board.isOnBoard(third, fourth)) {
            invalidInput = false;

            final BoardPosition start = Board.boardPositionFrom(first, second);
            final BoardPosition end = Board.boardPositionFrom(third, fourth);

            try {
              response = GameApi.performTurn(response.getGameId(), start, end);
            } catch (IllegalMoveException e) {
              System.out.println("You tried to perform an illegal move!");
            }
          }

          buffer.readLine();
        } catch (NumberFormatException e) {
          buffer.readLine();
        }
      }
    }

    if (response.isCheckmate()) {
      System.out.println("Checkmate! " + response.getWinner() + " wins!");
    } else {
      System.out.println("Stalemate!");
    }
  }
}
