package com.example.TicTacToe.datasource.model;


import java.util.UUID;

public class TicTacToeEntity {
    private UUID uuid;
    private TicTacToeAreaEntity ticTacToeAreaEntity;

    public TicTacToeEntity() {

    }

    public TicTacToeEntity(UUID uuid, TicTacToeAreaEntity ticTacToeAreaEntity) {
        this.ticTacToeAreaEntity = ticTacToeAreaEntity;
        this.uuid = uuid;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TicTacToeAreaEntity getTicTacToeArea() {
        return ticTacToeAreaEntity;
    }

    public void setTicTacToeArea(TicTacToeAreaEntity ticTacToeAreaEntity) {
        this.ticTacToeAreaEntity = ticTacToeAreaEntity;
    }
}
