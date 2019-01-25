package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tictactoe.game.MultiPlayer;
import com.example.tictactoe.game.SinglePlayer;
import com.example.tictactoe.player.Player;

public class MainActivity extends AppCompatActivity {

    public static Player player1 = null;
    public static Player player2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toSinglePlayerMode();
        toMultiPlayerMode();
    }

    public void toSinglePlayerMode() {
        Button gameButton = findViewById(R.id.single_player);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText player1Name = (EditText) findViewById(R.id.player_1_name);
                if (player1Name != null) {
                    player1 = new Player(player1Name.getText().toString(), "Player 1");
                } else {
                    player1 = new Player("Player 1");
                }

                player2 = new Player("Computer");

                startActivity(new Intent(getApplicationContext(), SinglePlayer.class));
                finish();
            }
        });
    }

    public void toMultiPlayerMode() {
        Button gameButton = findViewById(R.id.multi_player);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText player1Name = (EditText) findViewById(R.id.player_1_name);
                if (player1Name != null) {
                    player1 = new Player(player1Name.getText().toString(), "Player 1");
                } else {
                    player1 = new Player("Player 1");
                }

                EditText player2Name = (EditText) findViewById(R.id.player_2_name);
                if (player2Name != null) {
                    player2 = new Player(player2Name.getText().toString(), "Player 2");
                } else {
                    player2 = new Player("Player 2");
                }

                startActivity(new Intent(getApplicationContext(), MultiPlayer.class));
                finish();
            }
        });
    }
}
