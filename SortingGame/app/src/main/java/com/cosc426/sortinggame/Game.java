package com.cosc426.sortinggame;

import java.util.Random;

public class Game {

    private final int SIZE = 10;
    private Random r;
    private int[] solution;
    private int[] problem;
    private int[] current;

    public Game() {
        r = new Random();
        //generate 10 random numbers for problem, set current
        solution = new int[SIZE];
        problem = new int[SIZE];
        current = new int[SIZE];
        generateProblem();
        generateSolution();
    }

    private void generateProblem() {
        for(int i = 0; i < SIZE; i++) {
            problem[i] = r.nextInt(100) + 1;
            current[i] = problem[i];
            solution[i] = problem[i];
        }
    }

    private void generateSolution() {
        int temp = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = i+1; j < SIZE; j++) {
                if(solution[i] > solution[j]) {
                    temp = solution[i];
                    solution[i] = solution[j];
                    solution[j] = temp;
                }
            }
        }
    }

    //Get current array
    public int[] getCurrent() {
        return current;
    }

    //Swap within window
    public void exchange(int i, int j) {
        int temp = current[i];
        current[i] = current[j];
        current[j] = temp;
    }

    //Check if solved
    public boolean solved() {
        for(int i = 0; i < SIZE; i++) {
            if(current[i] != solution[i])
                return false;
        }
        return true;
    }
}
