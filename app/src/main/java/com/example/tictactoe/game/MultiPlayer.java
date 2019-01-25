package com.example.tictactoe.game;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tictactoe.R;
import com.example.tictactoe.player.Player;

public class MultiPlayer extends Game {

    public MultiPlayer() {
        super();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        gameInfo.setGameStatus(player1.getName() + " turn");
    }

    @Override
    void move(ImageButton button, int cellRow, int cellCol) {
        if (gameOver) {
            return;
        }

        if (this.turn == this.player1) {
            button.setImageResource(R.drawable.cross);
            score[cellRow][cellCol] = player1;
            if (checkIfCurrentPlayerWon(player1)) {
                gameOver = true;
                gameInfo.setGameStatus("Player 1 won");
                return;
            }
            turn = player2;
            gameInfo.setGameStatus("Player 2 turn");
        } else {
            button.setImageResource(R.drawable.circle);
            score[cellRow][cellCol] = player2;
            if (checkIfCurrentPlayerWon(player2)) {
                gameOver = true;
                gameInfo.setGameStatus("Player 2 won");
                return;
            }
            turn = player1;
            gameInfo.setGameStatus("Player 1 turn");
        }

        if (checkIfDraw()) {
            gameOver = true;
            gameInfo.setGameStatus("Draw");
        }
    }

    public void newGame() {
        Button gameButton = findViewById(R.id.new_game);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MultiPlayer.class));
                finish();
            }
        });
    }
}
