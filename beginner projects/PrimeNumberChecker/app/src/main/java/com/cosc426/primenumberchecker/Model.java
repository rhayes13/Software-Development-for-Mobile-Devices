package com.cosc426.primenumberchecker;

public class Model {

    public int input;

    public Model() {}

    public void setInput(int in) {
        input = in;
    }

    // Check if number is prime
    public boolean checkPrime() {
        if(input <= 1) {
            return false;
        }

        for(int i = 2; i < input; i++) {
            if(input % i == 0) {
                return false;
            }
        }
        return true;
    }
}
