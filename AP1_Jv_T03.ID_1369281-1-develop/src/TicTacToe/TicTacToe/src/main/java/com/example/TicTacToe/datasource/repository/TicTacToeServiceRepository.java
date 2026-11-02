package com.example.TicTacToe.datasource.repository;

import com.example.TicTacToe.datasource.model.TicTacToeEntity;
import com.example.TicTacToe.datasource.repository.TicTacToeRepositoryImpl;
import com.example.TicTacToe.domain.model.TicTacToeArea;
import com.example.TicTacToe.domain.model.TicTacToeGame;
import com.example.TicTacToe.domain.service.TicTacToeServiceInterface;
import com.example.TicTacToe.datasource.mapper.TicTacToeEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicTacToeServiceRepository implements TicTacToeServiceInterface {

    private static final int EMPTY = 0;
    private static final int PLAYER = 1;
    private static final int COMPUTER = 2;
    private static final int MAX_RATING = 10;
    private static final int MAX_ROWS_IN_MATRIX = 3;
    private static final int MAX_COLUMNS_IN_MATRIX = 3;
    private final TicTacToeRepositoryImpl repositoryImpl;

    @Autowired
    public TicTacToeServiceRepository(TicTacToeRepositoryImpl ticTacToeRepository) {
        this.repositoryImpl = ticTacToeRepository;
    }

    public TicTacToeServiceRepository() {
        this.repositoryImpl = new TicTacToeRepositoryImpl();
    }

    @Override
    public TicTacToeGame getNextMove(TicTacToeGame game) {
        Optional<int[]> optional = findNextBestMove(game.getTicTacToeArea());
        if (optional.isPresent()) {
            int[] posCell = optional.get();
            game.getTicTacToeArea().setCell(posCell[0], posCell[1], COMPUTER);
            TicTacToeEntity entity = TicTacToeEntityMapper.ticTacToeEntity(game);
            repositoryImpl.saveGame(entity);
        }
        return game;
    }

    @Override
    public boolean isMoveValid(TicTacToeArea newGameField, TicTacToeArea previousGameField) {
        int count = 0;
        for (int i = 0; i < MAX_ROWS_IN_MATRIX; i++) {
            for (int j = 0; j < MAX_COLUMNS_IN_MATRIX; j++) {
                if (newGameField.getCell(i, j) != previousGameField.getCell(i, j)) {
                    count++;
                    if (previousGameField.getCell(i, j) != EMPTY || newGameField.getCell(i, j) != PLAYER) {
                        return false;
                    }
                }
            }
        }
        return count == 1;
    }

    @Override
    public String isGameOver(TicTacToeGame game) {
        if (game.getTicTacToeArea().isWhoWin(PLAYER)) {
            return "win player";
        } else if (game.getTicTacToeArea().isWhoWin(COMPUTER)) {
            return "win computer";
        } else if (game.getTicTacToeArea().isNoMovesLeft()) {
            return "not winner";
        } else {
            return "continues";
        }
    }

    private Optional<int[]> findNextBestMove(TicTacToeArea gameField) {
        int[] posCell = new int[2];
        int ratingMove = Integer.MIN_VALUE;
        int ratingTemp;
        for (int i = 0; i < MAX_ROWS_IN_MATRIX; i++) {
            for (int j = 0; j < MAX_COLUMNS_IN_MATRIX; j++) {
                if (gameField.getCell(i, j) == EMPTY) {
                    gameField.setCell(i, j, COMPUTER);
                    ratingTemp = getMinimax(gameField, false, 0);
                    gameField.setCell(i, j, EMPTY);
                    if (ratingTemp > ratingMove) {
                        ratingMove = ratingTemp;
                        posCell[0] = i;
                        posCell[1] = j;
                    }
                }
            }
        }
        if (ratingMove != Integer.MIN_VALUE) {
            return Optional.of(new int[]{posCell[0], posCell[1]});
        } else {
            return Optional.empty();
        }
    }

    private int getMinimax(TicTacToeArea gameField, boolean typeMove, int depth) {
        if (gameField.isWhoWin(COMPUTER)) return MAX_RATING - depth;
        if (gameField.isWhoWin(PLAYER)) return -MAX_RATING + depth;
        if (gameField.isNoMovesLeft()) return 0;
        int tmp;
        if (typeMove) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < MAX_ROWS_IN_MATRIX; i++) {
                for (int j = 0; j < MAX_COLUMNS_IN_MATRIX; j++) {
                    if (gameField.getCell(i, j) == EMPTY) {
                        gameField.setCell(i, j, COMPUTER);
                        tmp = getMinimax(gameField, false, depth + 1);
                        max = Math.max(max, tmp);
                        gameField.setCell(i, j, EMPTY);
                    }
                }
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < MAX_ROWS_IN_MATRIX; i++) {
                for (int j = 0; j < MAX_COLUMNS_IN_MATRIX; j++) {
                    if (gameField.getCell(i, j) == EMPTY) {
                        gameField.setCell(i, j, PLAYER);
                        tmp = getMinimax(gameField, true, depth + 1);
                        min = Math.min(min, tmp);
                        gameField.setCell(i, j, EMPTY);
                    }
                }
            }
            return min;
        }
    }

    public TicTacToeRepositoryImpl getRepository() {
        return repositoryImpl;
    }

    public void save(TicTacToeGame game) {
        TicTacToeEntity entity = TicTacToeEntityMapper.ticTacToeEntity(game);
        repositoryImpl.saveGame(entity);
    }

    public static void showGameArea(int[][] matrix) {
        for (int i = 0; i < MAX_ROWS_IN_MATRIX; i++) {
            for (int j = 0; j < MAX_COLUMNS_IN_MATRIX; j++) {
                switch (matrix[i][j]) {
                    case 1 -> System.out.print("X|");
                    case 2 -> System.out.print("O|");
                    default -> System.out.print(".|");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}