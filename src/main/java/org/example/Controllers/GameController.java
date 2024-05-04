package org.example.Controllers;

import org.example.models.Game;
import org.example.models.GameStatus;
import org.example.models.Player;
import org.example.strategies.winningStrategies.WinningStrategies;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player>players,
                           List<WinningStrategies> winningStrategies){
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players).
                setWinningStrategies(winningStrategies)
                .build();

    }
    public void displayBoard(Game game){
        game.printBoard();

    }
    public void undo(Game game){

    }
    public void makeMove(Game game){

    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();

    }


    public void printResult(Game game){
        game.printResult();

    }
}
