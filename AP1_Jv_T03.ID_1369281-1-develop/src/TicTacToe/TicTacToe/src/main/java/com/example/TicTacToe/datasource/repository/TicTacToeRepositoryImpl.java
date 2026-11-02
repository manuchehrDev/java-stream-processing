package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.datasource.model.TicTacToeEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class TicTacToeRepositoryImpl implements InterfaceTicTacToeRepository{

    private final ConcurrentHashMap<UUID,TicTacToeEntity> games = new ConcurrentHashMap<>();

    @Override
    public void saveGame(TicTacToeEntity ticTacToeEntity) {
        this.games.put(ticTacToeEntity.getUuid(),ticTacToeEntity);
    }

    @Override
    public TicTacToeEntity getTicTacToeEntity(UUID uuid) {
        return games.get(uuid);
    }
}
