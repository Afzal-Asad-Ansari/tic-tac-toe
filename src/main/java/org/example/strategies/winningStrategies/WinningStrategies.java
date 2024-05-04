package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategies {
    boolean checkWinner(Board board, Move move);
}
