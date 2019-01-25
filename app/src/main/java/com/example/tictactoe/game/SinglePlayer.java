package com.example.tictactoe.game;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tictactoe.R;
import com.example.tictactoe.player.Player;

import java.util.Random;

import static com.example.tictactoe.MainActivity.player1;
import static com.example.tictactoe.MainActivity.player2;

public class SinglePlayer extends Game {

    public SinglePlayer() {
        super();
        gameInfo.setGameStatus("Your turn");
    }

    @Override
    void move(ImageButton button, int cellRow, int cellCol) {
        if (gameOver) {
            return;
        }

        humanMove(button, cellRow, cellCol);
        if (gameOver) {
            return;
        }
        if (checkIfDraw()) {
            gameOver = true;
            gameInfo.setGameStatus("Draw");
        }

        computerMove();
        if (gameOver) {
            return;
        }
        if (checkIfDraw()) {
            gameOver = true;
            gameInfo.setGameStatus("Draw");
        }
    }

    private void humanMove(ImageButton button, int cellRow, int cellCol) {
        button.setImageResource(R.drawable.cross);
        score[cellRow][cellCol] = turn;
        if (checkIfCurrentPlayerWon(turn)) {
            gameOver = true;
            gameInfo.setGameStatus("You won");
            return;
        }
        turn = getOtherPlayer(turn);
        gameInfo.setGameStatus("Computers turn");
    }

    private void computerMove() {
        // TODO: better computer player
        Random rand = new Random();
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);

        while (score[row][col] != null) {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        }

        ImageButton button = getImageButton(row, col);
        button.setImageResource(R.drawable.circle);
        score[row][col] = turn;
        if (checkIfCurrentPlayerWon(turn)) {
            gameOver = true;
            gameInfo.setGameStatus("Computer won");
            return;
        }
        turn = getOtherPlayer(turn);
        gameInfo.setGameStatus("Your turn");
    }

    @Override
    public void newGame() {
        Button gameButton = findViewById(R.id.new_game);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = new Player[3][3];
                gameOver = false;
                turn = getOtherPlayer(previousStarter);
                previousStarter = getOtherPlayer(previousStarter);
                gameInfo.setGameStatus(turn.getName() + " turn");

                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        final ImageButton cell = getImageButton(row, col);
                        cell.setImageResource(R.drawable.empty);
                    }
                }

                if (turn == player2) {
                    computerMove();
                }

            }
        });
    }
}
