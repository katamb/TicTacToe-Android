package com.example.tictactoe.game;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tictactoe.BR;

public class GameInfo extends BaseObservable {

    private String gameStatus = "Your turn!";

    @Bindable
    public String getGameStatus() {
        notifyPropertyChanged(BR.gameStatus);
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

}
