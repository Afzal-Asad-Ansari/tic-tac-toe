package org.example.strategies.botPlayingStrategies;

import org.example.models.Board;
import org.example.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);

}
