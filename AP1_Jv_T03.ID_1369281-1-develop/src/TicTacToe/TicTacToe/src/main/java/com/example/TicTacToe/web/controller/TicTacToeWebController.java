package com.example.TicTacToe.web.controller;


import com.example.TicTacToe.datasource.mapper.TicTacToeEntityMapper;
import com.example.TicTacToe.datasource.model.TicTacToeEntity;
import com.example.TicTacToe.datasource.repository.TicTacToeServiceRepository;
import com.example.TicTacToe.domain.model.TicTacToeArea;
import com.example.TicTacToe.domain.model.TicTacToeGame;
import com.example.TicTacToe.web.mapper.TicTacToeWebMapper;
import com.example.TicTacToe.web.model.TicTacToeRequest;
import com.example.TicTacToe.web.model.TicTacToeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping({"/game", "/game/"})
public class TicTacToeWebController {

//    http://localhost:8080/game

    private final TicTacToeServiceRepository service;

    //    @Autowired
    public TicTacToeWebController(TicTacToeServiceRepository ticTacToeServiceRepository) {
        this.service = ticTacToeServiceRepository;
    }

    @PostMapping()
    public ResponseEntity<TicTacToeResponse> initGame() {
        UUID uuid = UUID.randomUUID();
        int[][] matrix = new int[3][3];
        TicTacToeArea area = new TicTacToeArea(matrix);
        TicTacToeGame ticTacToeGame = new TicTacToeGame(uuid, area);
        service.save(ticTacToeGame);
        TicTacToeResponse response = new TicTacToeResponse(uuid, matrix, false);
        response.setMessage("continues");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TicTacToeResponse> makeMove(@PathVariable UUID id, @RequestBody TicTacToeRequest request) {
        if (!id.equals(request.getUuid())) {
            TicTacToeResponse errorResponse = new TicTacToeResponse("UUID in path does not match UUID in request body");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        TicTacToeGame game = TicTacToeWebMapper.makeTicTacToeDomain(request);
        TicTacToeEntity previousEntity = service.getRepository().getTicTacToeEntity(id);
        if (previousEntity == null) {
            TicTacToeResponse errorResponse = new TicTacToeResponse("Game not found");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        TicTacToeGame previousGame = TicTacToeEntityMapper.ticTacToeGame(previousEntity);
        String gameStatus = service.isGameOver(previousGame);
        boolean isGameOver = !"continues".equals(gameStatus);
        if (isGameOver) {
            TicTacToeResponse errorResponse = new TicTacToeResponse("Game is already over: " + gameStatus);
            errorResponse.setGameOver(true);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        if (!service.isMoveValid(game.getTicTacToeArea(), previousGame.getTicTacToeArea())) {
            TicTacToeResponse errorResponse = new TicTacToeResponse("Invalid move: previous moves have been altered");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        TicTacToeGame updatedGame = service.getNextMove(game);
        gameStatus = service.isGameOver(updatedGame);
        isGameOver = !"continues".equals(gameStatus);
        TicTacToeResponse response = TicTacToeWebMapper.makeResponse(updatedGame, gameStatus, isGameOver);
        TicTacToeServiceRepository.showGameArea(response.getGameField());
        return ResponseEntity.ok(response);
    }

}
