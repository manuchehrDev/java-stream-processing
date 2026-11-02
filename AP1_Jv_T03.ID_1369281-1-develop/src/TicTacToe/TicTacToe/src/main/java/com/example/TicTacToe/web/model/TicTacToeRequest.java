package com.example.TicTacToe.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;


import java.util.UUID;


@Component
public class TicTacToeRequest {
    private UUID uuid;
    private int[][] gameField;

    public TicTacToeRequest() {

    }

    @JsonCreator
    public TicTacToeRequest(@JsonProperty UUID uuid, @JsonProperty int[][] gameField) {
        this.uuid = uuid;
        this.gameField = gameField;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int[][] getGameField() {
        return gameField;
    }

    public void setGameField(int[][] gameField) {
        this.gameField = gameField;
    }
}
