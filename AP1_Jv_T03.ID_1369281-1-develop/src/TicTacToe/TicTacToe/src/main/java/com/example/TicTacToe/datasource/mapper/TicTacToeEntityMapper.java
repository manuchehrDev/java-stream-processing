package com.example.TicTacToe.datasource.mapper;

import com.example.TicTacToe.datasource.model.TicTacToeAreaEntity;
import com.example.TicTacToe.datasource.model.TicTacToeEntity;
import com.example.TicTacToe.domain.model.TicTacToeArea;
import com.example.TicTacToe.domain.model.TicTacToeGame;

public class TicTacToeEntityMapper {
    public static TicTacToeAreaEntity toeAreaEntity(TicTacToeArea ticTacToeArea) {
        return new TicTacToeAreaEntity(ticTacToeArea.getMatrix());
    }

    public static TicTacToeEntity ticTacToeEntity(TicTacToeGame ticTacToeGame) {
        return new TicTacToeEntity(ticTacToeGame.getUuid(), toeAreaEntity(ticTacToeGame.getTicTacToeArea()));
    }

    public static TicTacToeArea ticTacToeArea(TicTacToeAreaEntity ticTacToeArea) {
        return new TicTacToeArea(ticTacToeArea.getMatrix());
    }

    public static TicTacToeGame ticTacToeGame(TicTacToeEntity ticTacToeArea) {
        return new TicTacToeGame(ticTacToeArea.getUuid(), ticTacToeArea(ticTacToeArea.getTicTacToeArea()));
    }
}
