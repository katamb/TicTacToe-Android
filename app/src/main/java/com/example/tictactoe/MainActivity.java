package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tictactoe.game.MultiPlayer;
import com.example.tictactoe.game.SinglePlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toMultiPlayerMode();
        toSinglePlayerMode();
    }

    public void toMultiPlayerMode() {
        Button gameButton = findViewById(R.id.multi_player);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MultiPlayer.class));
                finish();
            }
        });
    }

    public void toSinglePlayerMode() {
        Button gameButton = findViewById(R.id.single_player);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SinglePlayer.class));
                finish();
            }
        });
    }
}
