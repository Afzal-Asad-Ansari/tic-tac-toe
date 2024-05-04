package org.example.models;

import org.example.strategies.winningStrategies.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<WinningStrategies> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    public static Builder getBuilder() {
        return new Builder();

    }

    public Game(int dimension, List<Player> players, List<WinningStrategies> winningStrategies) {
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.players = players;
        this.currentMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
        //this.winner=null;

    }


    public List<Move> getMove() {
        return moves;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setMove(List<Move> move) {
        this.moves = move;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void printResult() {
        if (gameStatus.equals(GameStatus.ENDED)) {
            System.out.println("Game has ended.");
            System.out.println("Winner is:: " + winner.getName());

        } else {
            System.out.println(("Game is Drawn"));
        }
    }

    public void printWinner() {
        System.out.println(winner);
    }


    public void printBoard() {
        this.board.print();
    }

    private boolean validateMove(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();

        if(row<0 || col<0 || row>=board.getSize() || col>=board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
}
    public void makeMove(){
        Player currentPlayer=players.get(currentMovePlayerIndex);
        System.out.println("It is "+currentPlayer.getName()+"'s turn.");


        Cell proposedCell=currentPlayer.makeMove(board);

        System.out.println("Move made at row:"+proposedCell.getRow()+"col:"+ proposedCell.getCol()+".");
        if(!validateMove(proposedCell)){
            System.out.println("Invalid move, Please try again");
            return;
        }
        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellState(CellState.FILLED);
        cellInBoard.setPlayer(currentPlayer);
        Move move =new Move(currentPlayer,cellInBoard);
        moves.add(move);
        for (WinningStrategies winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus=GameStatus.ENDED;
                winner=currentPlayer;
                return;
            }
        }
        if(moves.size() == board.getSize() * board.getSize()){
            gameStatus=GameStatus.DRAW;
            return;
        }
        currentMovePlayerIndex+=1;
        currentMovePlayerIndex%=players.size();


    }
    public static class Builder{

        private List<Player> players;
        private List<WinningStrategies> winningStrategies;

        private int dimension;
        private Builder(){}

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;

        }

        public Builder setWinningStrategies(List<WinningStrategies> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        private boolean valid(){
            if(this.players.size()<2){
                return false;
            }
            if (this.players.size() !=this.dimension -1){
                return false;
            }
            int botCount =0;
            for(Player player:this.players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
            }
            if (botCount>=2){
                return false;
            }
            Set<Character>existingSymbols = new HashSet<>();
            for(Player player:players){
                if(existingSymbols.contains(player.getSymbol().getaChar())){
                    return false;
                }

            }

            return true;

        }
        public Game build(){
            if(!valid()){
                throw new RuntimeException("Invalid params for game");
            }
            return new Game(dimension,players, winningStrategies);
        }
    }

}


//1- All attributes must be private
//2- Have getter & setter for that.
//3- Ensure all attributes are initialized in constructor..
