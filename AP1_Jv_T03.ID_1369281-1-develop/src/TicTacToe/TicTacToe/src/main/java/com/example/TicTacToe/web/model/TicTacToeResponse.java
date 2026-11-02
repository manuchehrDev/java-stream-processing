package com.example.TicTacToe.web.model;

import com.example.TicTacToe.domain.model.TicTacToeArea;

import java.util.UUID;

//@Component
public class TicTacToeResponse {
    private UUID uuid;
    private int[][] gameField;
    private String message;
    private boolean gameOver;

    public TicTacToeResponse(){

    }

    public TicTacToeResponse(UUID uuid, TicTacToeArea ticTacToeArea, boolean isOver) {
        this.uuid=uuid;
        this.gameField = ticTacToeArea.getMatrix();
        this.gameOver = isOver;
    }

    public TicTacToeResponse(String message) {
        this.message = message;
    }

    public TicTacToeResponse(UUID uuid, int[][] gameField, String message, boolean gameOver) {
        this.uuid = uuid;
        this.gameField = gameField;
        this.message = message;
        this.gameOver = gameOver;
    }

    public TicTacToeResponse(UUID uuid, int[][] matrix, boolean isOver) {
        this.uuid = uuid;
        this.gameField = matrix;
        this.gameOver = isOver;
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

    public void setGameField(int[][] field) {
        this.gameField = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
