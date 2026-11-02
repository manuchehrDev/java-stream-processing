package com.example.TicTacToe.domain.model;

import java.rmi.server.UID;
import java.util.UUID;

public class TicTacToeGame {
    private UUID uuid;
    private TicTacToeArea ticTacToeArea;

    public TicTacToeGame() {
        this.uuid = UUID.randomUUID();
        this.ticTacToeArea = new TicTacToeArea();
    }

    public TicTacToeGame(UUID uuid, TicTacToeArea ticTacToeArea) {
        this.ticTacToeArea = ticTacToeArea;
        this.uuid = uuid;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TicTacToeArea getTicTacToeArea() {
        return ticTacToeArea;
    }

    public void setTicTacToeArea(TicTacToeArea ticTacToeArea) {
        this.ticTacToeArea = ticTacToeArea;
    }


}
