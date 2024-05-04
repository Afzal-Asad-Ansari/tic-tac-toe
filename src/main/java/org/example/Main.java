package org.example;

import org.example.Controllers.GameController;
import org.example.models.*;
import org.example.strategies.winningStrategies.OrderOneColumnWinningStrategy;
import org.example.strategies.winningStrategies.OrderOneDiagonalWinningStrategy;
import org.example.strategies.winningStrategies.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create A game
        GameController gameController=new GameController();
        Scanner scanner=new Scanner(System.in);

        Game game;
        try{
            game= gameController.createGame(3,
                    List.of(new Player(new Symbol('X'),"Afzal", PlayerType.HUMAN),
                    new Bot(new Symbol('0'),"Bot",PlayerType.BOT,BotDifficultyLevel.EASY)),
            List.of(new OrderOneDiagonalWinningStrategy(),
                    new OrderOneRowWinningStrategy(),
                    new OrderOneColumnWinningStrategy()
            )
                    );


        }
        catch (Exception e){
            System.out.println("Somthing Went Wrong");
            return;
        }
        //=gameController.createGame();
        // Whle game status is in progress
        System.out.println(("--------------------Game is starting---------"));
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            System.out.println("This is how board looks like");
            // print board
            gameController.displayBoard(game);
            // print if undo
            System.out.println("Does any one want to undo? (Y/N");
            // id yes-> call undu
            String input = scanner.next();

            if(input.equalsIgnoreCase("y")){
                gameController.undo(game);

            }
            else{
                // move next player
                gameController.makeMove(game);

            }

        }
        //check status of game
        GameStatus gameStatus=gameController.getGameStatus(game);

        //if winner -> print winner
        gameController.printResult(game);


        //else print draw
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }

}