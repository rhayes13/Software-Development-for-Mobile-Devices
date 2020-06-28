package com.cosc426.sudokuv2;

public class Game
{
    private int[][] board;
    Sudoku s = new Sudoku();

    public Game()
    {
        //generate initial board
        this.board = s.generate();
    }

    public int[][] getBoard()
    {
        //return current board
        return board;
    }

    public boolean check(int value, int x, int y)
    {
        //check whether value can be placed at x, y

        return true; //TODO
    }

    public void set(int value, int x, int y)
    {
        //set value at x, y
        board[x][y] = value;
    }
}