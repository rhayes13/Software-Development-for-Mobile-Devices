package com.cosc426.sixthapp;

import java.util.Random;

public class MathGeneratorV2 {
    static int firstNumber;
    static int secondNumber;
    static int answer;
    static int upperLimit = 999;



    public MathGeneratorV2() {

    }

    public void generateNumbers() {
        Random r = new Random();
        this.firstNumber = r.nextInt(upperLimit) + 1;
        this.secondNumber = r.nextInt(upperLimit) + 1;
        this.answer = firstNumber + secondNumber;
    }

    public static String getFirstNumber() {
        return Integer.toString(firstNumber);
    }

    public static void setFirstNumber(int firstNumber) {
        MathGeneratorV2.firstNumber = firstNumber;
    }

    public static String getSecondNumber() {
        return Integer.toString(secondNumber);
    }

    public static void setSecondNumber(int secondNumber) {
        MathGeneratorV2.secondNumber = secondNumber;
    }

    public static int getAnswer() {
        return answer;
    }

    public static void setAnswer(int answer) {
        MathGeneratorV2.answer = answer;
    }
}
