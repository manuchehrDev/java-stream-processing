package com.example.TicTacToe.datasource.model;

public class TicTacToeAreaEntity {
    private int[][] matrix;

    public TicTacToeAreaEntity(){
        this.matrix = new int[3][3];
    }

    public TicTacToeAreaEntity(int[][] matrix){
        this.matrix = matrix.clone();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
