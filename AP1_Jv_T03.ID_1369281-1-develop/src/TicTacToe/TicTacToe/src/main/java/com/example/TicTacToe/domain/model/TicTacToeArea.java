package com.example.TicTacToe.domain.model;


public class TicTacToeArea {
    private int[][] matrix = new int[3][3];

    public TicTacToeArea() {

    }

    public TicTacToeArea(int[][] matrix) {
        for (int i = 0; i < 3; i++){
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, 3);
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setCell(int i, int j, int value) {
        this.matrix[i][j] = value;
    }

    public int getCell(int i, int j) {
        return this.matrix[i][j];
    }


    private boolean isLineWin(int row1, int col1, int row2, int col2, int row3, int col3, int player) {
        return matrix[row1][col1] == player &&
                matrix[row1][col1] == matrix[row2][col2] &&
                matrix[row2][col2] == matrix[row3][col3];
    }

    public boolean isWhoWin(int player) {
        return isLineWin(0, 0, 1, 1, 2, 2, player) ||  // главная диагональ
                isLineWin(2, 0, 1, 1, 0, 2, player) ||  // побочная диагональ
                isLineWin(0, 0, 0, 1, 0, 2, player) ||  // первая строка
                isLineWin(1, 0, 1, 1, 1, 2, player) ||  // вторая строка
                isLineWin(2, 0, 2, 1, 2, 2, player) ||  // третья строка
                isLineWin(0, 0, 1, 0, 2, 0, player) ||  // первый столбец
                isLineWin(0, 1, 1, 1, 2, 1, player) ||  // второй столбец
                isLineWin(0, 2, 1, 2, 2, 2, player);    // третий столбец
    }

    public boolean isNoMovesLeft() {
        for (int[] m : matrix) {
            for (int e : m) {
                if (e == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
