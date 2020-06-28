package com.cosc426.slidingpuzzle;

public class Game
{
    private char[][] board;
    private char[][] goal;
    private int x, y;

    public Game()
    {
        //create Slide object
        Slide s = new Slide();

        //create initial and goal boards
        board = s.generateInitialBoard();
        goal = s.generateGoalBoard();

        determineBlank();
    }

    //can only move if blank tile is included
    public void check(int x1, int y1, int x2, int y2) {
        //check if tiles can be swapped
        //horizontal neighbors, vertical neighbors
        if((x1 == x || x2 == x) && (y1 == y || y2 ==y)) {
            if (x2 == x1) {
                if (y2 == y1 - 1 || y2 == y1 + 1)
                    swap(x1, y1, x2, y2);
            } else if (y2 == y1) {
                if (x2 == x1 - 1 || x2 == x1 + 1)
                    swap(x1, y1, x2, y2);
            }
        }
    }

    public void swap(int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2]; // replace the value
        board[x2][y2] = temp;
        determineBlank(); //Find new location of blank tile
    }

    public void determineBlank() {
        //determine the location of blank
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    x = i;
                    y = j;
                }
            }
        }
    }

    public char[][] getBoard()
    {
        //return current board
        return board;
    }

    public char[][] getGoal()
    {
        //return goal board
        return goal;
    }

    public boolean gameWon() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] != goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
