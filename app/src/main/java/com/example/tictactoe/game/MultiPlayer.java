package com.example.tictactoe.game;

import android.widget.ImageButton;

import com.example.tictactoe.R;

import static com.example.tictactoe.MainActivity.player1;

public class MultiPlayer extends Game {

    public MultiPlayer() {
        super();
        gameInfo.setGameStatus(turn.getName() + " turn");
    }

    @Override
    void move(ImageButton button, int cellRow, int cellCol) {
        if (gameOver) {
            return;
        }

        if (turn == player1) {
            makeMove(button, cellRow, cellCol);
        } else {
            makeMove(button, cellRow, cellCol);
        }

        if (checkIfDraw()) {
            gameOver = true;
            gameInfo.setGameStatus("Draw");
        }
    }

    private void makeMove(ImageButton button, int cellRow, int cellCol) {
        if (turn == player1) {
            button.setImageResource(R.drawable.cross);
        } else {
            button.setImageResource(R.drawable.circle);
        }

        score[cellRow][cellCol] = turn;
        if (checkIfCurrentPlayerWon(turn)) {
            gameOver = true;
            gameInfo.setGameStatus(turn.getName() + " won");
            return;
        }
        turn = getOtherPlayer(turn);
        gameInfo.setGameStatus(turn.getName() + " turn");
    }

}
