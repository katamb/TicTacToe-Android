package com.example.tictactoe.game;

import android.widget.ImageButton;

import com.example.tictactoe.R;
import com.example.tictactoe.player.Player;

import java.util.Random;

public class SinglePlayer extends Game {

    public SinglePlayer() {
        super();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameInfo.setGameStatus("Your turn");
    }

    @Override
    void move(ImageButton button, int cellRow, int cellCol) {
        if (gameOver) {
            return;
        }

        button.setImageResource(R.drawable.cross);
        score[cellRow][cellCol] = player1;
        if (checkIfWon(player1)) {
            gameOver = true;
            gameInfo.setGameStatus("You won");
            return;
        }
        turn = player2;
        gameInfo.setGameStatus("Computers turn");

        // TODO: better computer player
        Random rand = new Random();
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);

        while (score[row][col] != null) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        }

        button = getImageButton(row, col);
        button.setImageResource(R.drawable.circle);
        score[row][col] = player2;
        if (checkIfWon(player2)) {
            gameOver = true;
            gameInfo.setGameStatus("Computer won");
            return;
        }
        turn = player1;
        gameInfo.setGameStatus("Your turn");

        if (checkIfDraw()) {
            gameOver = true;
            gameInfo.setGameStatus("Draw");
        }
    }

    private ImageButton getImageButton(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0: return findViewById(R.id.cell_00);
                    case 1: return findViewById(R.id.cell_01);
                    case 2: return findViewById(R.id.cell_02);
                }
            case 1:
                switch (col) {
                    case 0: return findViewById(R.id.cell_10);
                    case 1: return findViewById(R.id.cell_11);
                    case 2: return findViewById(R.id.cell_12);
                }
            case 2:
                switch (col) {
                    case 0: return findViewById(R.id.cell_20);
                    case 1: return findViewById(R.id.cell_21);
                    case 2: return findViewById(R.id.cell_22);
                }
        }
        return null;
    }

}
