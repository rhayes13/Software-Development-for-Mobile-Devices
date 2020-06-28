package com.cosc426.gradecalculator;

public class Grade {

    static int program;
    static int midterm;
    static int finalExam;
    static int score;

    public Grade() {}

    // Max val of 200
    public void setProgram(String s) {
        program = Integer.parseInt(s);
        if(program > 200) {
            program = 200;
        }
    }

    public int getProgram() {
        return program;
    }

    // Max val of 100
    public void setMidterm(String s) {
        midterm = Integer.parseInt(s);
        if(midterm > 100) {
            midterm = 100;
        }
    }

    public int getMidterm() {
        return midterm;
    }

    // Sets finalExam to max value of 100
    public void setFinal(String s) {
        finalExam = Integer.parseInt(s);
        if(finalExam > 100) {
            finalExam = 100;
        }
    }

    public int getFinal() {
        return finalExam;
    }

    // Score formula
    public int getScore() {
        return score = 60*getProgram()/200 + 20*getMidterm()/100 + 20*getFinal()/100;
    }

    // Returns letter grade based on score
    public String getLetterGrade() {
        if(90 <= score && score <= 100) {
            return "A";
        }
        if(80 <= score && score < 90) {
            return "B";
        }
        if(70 <= score && score < 80) {
            return "C";
        }
        if(60 <= score && score < 70) {
            return "D";
        }

        return "FAIL";
    }

}
