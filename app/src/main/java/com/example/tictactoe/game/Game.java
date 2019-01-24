package com.example.tictactoe.game;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
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

    boolean checkIfWon(Player player) {
        // todo check if all possible moves are done
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

    // always call this method after checkIfWon()
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
        // TODO: I don't like the current solution. Try to make this in a loop?
        final ImageButton cell_00 = findViewById(R.id.cell_00);
        final ImageButton cell_01 = findViewById(R.id.cell_01);
        final ImageButton cell_02 = findViewById(R.id.cell_02);
        final ImageButton cell_10 = findViewById(R.id.cell_10);
        final ImageButton cell_11 = findViewById(R.id.cell_11);
        final ImageButton cell_12 = findViewById(R.id.cell_12);
        final ImageButton cell_20 = findViewById(R.id.cell_20);
        final ImageButton cell_21 = findViewById(R.id.cell_21);
        final ImageButton cell_22 = findViewById(R.id.cell_22);

        cell_00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[0][0] == null) {
                    move(cell_00, 0, 0);
                }
            }
        });
        cell_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[0][1] == null) {
                    move(cell_01, 0, 1);
                }
            }
        });
        cell_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[0][2] == null) {
                    move(cell_02, 0, 2);
                }
            }
        });
        cell_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[1][0] == null) {
                    move(cell_10, 1, 0);
                }
            }
        });
        cell_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[1][1] == null) {
                    move(cell_11, 1, 1);
                }
            }
        });
        cell_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[1][2] == null) {
                    move(cell_12, 1, 2);
                }
            }
        });
        cell_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[2][0] == null) {
                    move(cell_20, 2, 0);
                }
            }
        });
        cell_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[2][1] == null) {
                    move(cell_21, 2, 1);
                }
            }
        });

        cell_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (score[2][2] == null) {
                    move(cell_22, 2, 2);
                }
            }
        });
    }
}
