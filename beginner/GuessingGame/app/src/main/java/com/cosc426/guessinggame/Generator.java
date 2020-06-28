package com.cosc426.guessinggame;

import java.util.Random;

public class Generator {

    public Random r = new Random();
    public int actual;
    public int guess;
    public int chances = 8;

    public Generator() {}

    // Randomly select number from 1-100
    public void setActual() {
        actual = r.nextInt(100) + 1;
    }

    public int getActual() {
        return actual;
    }

    public void setGuess(int n) {
        guess = n;
    }

    public int getGuess() {
        return guess;
    }

    // Cannot be below 0
    public void decreaseChances() {
        chances = chances - 1;
        if(chances < 0) {
            chances = 0;
        }
    }

    public int getChancesLeft() {
        return chances;
    }
}
