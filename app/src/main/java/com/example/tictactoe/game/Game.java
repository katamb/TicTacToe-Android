package com.example.tictactoe.game;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;
import com.example.tictactoe.databinding.GameViewBinding;
import com.example.tictactoe.player.Player;

public abstract class Game extends Activity {

    GameInfo gameInfo;
    Player player1;
    Player player2;
    Player turn;
    Player[][] score = new Player[3][3];
    boolean gameOver = false;

    public Game() {
        gameInfo = new GameInfo();
        turn = player1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameViewBinding gBinding = DataBindingUtil.setContentView(this, R.layout.game_view);
        gBinding.setGame(this);

        newGame();
        setup();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    abstract void move(ImageButton button, int cellRow, int cellCol);

    boolean checkIfCurrentPlayerWon(Player player) {
        for (int i = 0; i < 3; i++) {
            // check all rows
            if (allEqual(player, score[i][0], score[i][1], score[i][2])) {
                return true;
            }
            // check all columns
            if (allEqual(player, score[0][i], score[1][i], score[2][i])) {
                return true;
            }
        }

        // check diagonals
        if (allEqual(player, score[0][0], score[1][1], score[2][2])) {
            return true;
        }
        if (allEqual(player, score[2][0], score[1][1], score[0][2])) {
            return true;
        }

        return false;
    }

    // always call this method after checkIfCurrentPlayerWon()
    boolean checkIfDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (score[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean allEqual(Player currentPlayer, Player... players) {
        for (Player player : players) {
            if (player != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    public void setup() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                final int r = row;
                final int c = col;
                final ImageButton cell = getImageButton(row, col);

                cell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (score[r][c] == null) {
                            move(cell, r, c);
                        }
                    }
                });
            }
        }
    }

    public abstract void newGame();

    ImageButton getImageButton(int row, int col) {
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
