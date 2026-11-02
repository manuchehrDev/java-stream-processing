package com.example.TicTacToe.web.mapper;

import com.example.TicTacToe.domain.model.TicTacToeArea;
import com.example.TicTacToe.domain.model.TicTacToeGame;
import com.example.TicTacToe.web.model.TicTacToeRequest;
import com.example.TicTacToe.web.model.TicTacToeResponse;

public class TicTacToeWebMapper {

    public static TicTacToeGame makeTicTacToeDomain(TicTacToeRequest ticTacToeRequest) {
        TicTacToeArea ticTacToe = new TicTacToeArea(ticTacToeRequest.getGameField());
        if(ticTacToeRequest.getGameField()==null){
            System.out.println("matrix Null");
        }
        ticTacToe.setMatrix(ticTacToeRequest.getGameField());
        return new TicTacToeGame(ticTacToeRequest.getUuid(), ticTacToe);
    }


    public static TicTacToeResponse makeResponse(TicTacToeGame ticTacToeGame, String message, boolean isOver) {
        TicTacToeResponse ticTacToeResponse = new TicTacToeResponse(ticTacToeGame.getUuid(), ticTacToeGame.getTicTacToeArea(), isOver);
        ticTacToeResponse.setMessage(message);
        return ticTacToeResponse;
    }

}
