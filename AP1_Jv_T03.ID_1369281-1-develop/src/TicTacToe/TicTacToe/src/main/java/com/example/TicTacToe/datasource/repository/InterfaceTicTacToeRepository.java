package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.datasource.model.TicTacToeEntity;

import java.util.UUID;

public interface InterfaceTicTacToeRepository {
    void saveGame(TicTacToeEntity ticTacToeEntity);
    TicTacToeEntity getTicTacToeEntity(UUID uuid);
}
