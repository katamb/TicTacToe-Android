package com.example.tictactoe.player;

public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, String substituteName) {
        if (name != null && !(name.length() < 1) && !(name.length() > 12)) {
            this.name = name;
        } else {
            this.name = substituteName;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
