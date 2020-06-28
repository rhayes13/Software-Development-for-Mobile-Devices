package com.cosc426.sixthapp;

import java.util.Random;

public class MathGenerator {

    static String operator;
    static String equals;
    static int num1;
    static int num2;
    int correctAnswer;
    static int numCorrect;
    static int numTotal;
    static int op;
    static Random r = new Random();

    public MathGenerator() {}

    //Gets the key and passes to value in MainActivity
    //Following classes switch based on chosen key
    public int generateRand(){
        return op = r.nextInt(4) + 1;
    }

    // RNG 1-4 for +, -, *, or /
    public String generateOperator(int key) {
        switch(key) {
            case 1: key = 1;
                return operator = "+";
            case 2: key = 2;
                return operator = "-";
            case 3: key = 3;
                return operator = "*";
            case 4: key = 4;
                return operator = "/";
        } return null;
    }

    // Generates the two random numbers 1-999 for +, numerator, or -
    // 1-99 for * or denominator   BASED ON key VALUE
    public int generateNum1(int key) {
        switch(key) {
            case 1: key = 1;
                return num1 = r.nextInt(999) + 1;
            case 2: key = 2;
                return num1 = r.nextInt(999) + 1;
            case 3: key = 3;
                return num1 = r.nextInt(99) + 1;
            case 4: key = 4;
                return num1 = r.nextInt(999) + 1;
        } return 0;
    }

    public int generateNum2(int key){
        switch(key) {
            case 1: key = 1;
                return num2 = r.nextInt(999) + 1;
            case 2: key = 2;
                return num2 = r.nextInt(999) + 1;
            case 3: key = 3;
                return num2 = r.nextInt(99) + 1;
            case 4: key = 4;
                return num2 = r.nextInt(99) + 1;
        } return 0;
    }

    public int generateCorrectAnswer(int key) {
        switch(key) {
            case 1: key = 1;
                return correctAnswer = num1 + num2;
            case 2: key = 2;
                return correctAnswer = num1 - num2;
            case 3: key = 3;
                return correctAnswer = num1 * num2;
            case 4: key = 4;
                return correctAnswer = num1 / num2;
        } return 0;
    }

    public String getOperator() {
        return operator;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getNum1() {
        return Integer.toString(num1);
    }

    public String getNum2() {
        return Integer.toString(num2);
    }

    public String getEquals() {
        return equals = "=";
    }

    public void incrementNumCorrect() {
        numCorrect++;
    }

    public void incrementNumTotal() {
        numTotal++;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public int getNumTotal() {
        return numTotal;
    }

}
