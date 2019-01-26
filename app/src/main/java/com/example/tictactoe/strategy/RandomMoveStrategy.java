package com.example.tictactoe.strategy;

import com.example.tictactoe.player.Player;

import java.util.Random;

public class RandomMoveStrategy implements Strategy {

    @Override
    public int[] moveStrategy(Player[][] score) {
        Random rand = new Random();
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);

        while (score[row][col] != null) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        }

        return new int[]{row, col};
    }
}
