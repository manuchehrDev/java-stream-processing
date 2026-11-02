package com.example.TicTacToe.domain.service;

import com.example.TicTacToe.domain.model.TicTacToeArea;
import com.example.TicTacToe.domain.model.TicTacToeGame;

public interface TicTacToeServiceInterface {

    TicTacToeGame getNextMove(TicTacToeGame game);

    boolean isMoveValid(TicTacToeArea newGameField, TicTacToeArea previousGameField);

    String isGameOver(TicTacToeGame game);
}
