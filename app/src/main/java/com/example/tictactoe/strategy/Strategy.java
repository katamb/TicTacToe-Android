package com.example.tictactoe.strategy;

import com.example.tictactoe.player.Player;

public interface Strategy {
    int[] moveStrategy(Player[][] score);
}
