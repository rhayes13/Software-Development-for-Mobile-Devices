package com.cosc426.seventhapp;

// Hard-coded questions and answers; with correct answer key
public class Question {
    static String question;
    static String ansOne;
    static String ansTwo;
    static String ansThree;
    static String ansFour;
    static int questionKey;
    static int answerKey;
    static int correctKey;
    static int correctAns;
    static int correct = 0;
    static int total = 0;

    public Question() {}

    public String getQuestion(int key) {
        questionKey = key;
        switch(questionKey) {
            case 1: questionKey=1;
                return question = "Q1: What is the oldest programming language?";
            case 2: questionKey=2;
                return question = "Q2: What is the second oldest programming language?";
            case 3: questionKey=3;
                return question = "Q3: Which has the biggest capacity?";
            case 4: questionKey=4;
                return question = "Q4: Which is not an example of an Operating System?";
            case 5: questionKey=5;
                return question = "Q5: What is ROM composed of?";
            case 6: questionKey=6;
                return question = "Q6: What is the code for standard information interchange?";
            case 7: questionKey=7;
                return question = "Q7: Which of the following is not a programming language?";
            case 8: questionKey=8;
                return question = "Q8: Which part of the computer is considered the 'brain'?";
            case 9: questionKey=9;
                return question = "Q9: What does USB stand for?";
            case 10: questionKey=10;
                return question = "Q10: Which is arguably the worst (slowest) sorting algorithm?";
        } return null;
    }

    public String getAnsOneText(int key) {
        answerKey = key;
        switch(answerKey) {
            case 1: answerKey=1;
                return ansOne = "LISP";
            case 2: answerKey=2;
                return ansOne = "Fortran";
            case 3: answerKey=3;
                return ansOne = "bit";
            case 4: answerKey=4;
                return ansOne = "Windows 98";
            case 5: answerKey=5;
                return ansOne = "Magnetic cores";
            case 6: answerKey=6;
                return ansOne = "ASCII";
            case 7: answerKey=7;
                return ansOne = "Basic";
            case 8: answerKey=8;
                return ansOne = "Random Access Memory";
            case 9: answerKey=9;
                return ansOne = "Unique Synchronised Bus";
            case 10: answerKey=10;
                return ansOne = "bogosort";

        } return null;
    }

    public String getAnsTwoText(int key) {
        answerKey = key;
        switch(answerKey) {
            case 1: answerKey=1;
                return ansTwo = "Fortran";
            case 2: answerKey=2;
                return ansTwo = "Javascript";
            case 3: answerKey=3;
                return ansTwo = "byte";
            case 4: answerKey=4;
                return ansTwo = "Red Hat Linux";
            case 5: answerKey=5;
                return ansTwo = "Micro-processors";
            case 6: answerKey=6;
                return ansTwo = "BCD";
            case 7: answerKey=7;
                return ansTwo = "Fortran";
            case 8: answerKey=8;
                return ansTwo = "Central Processing Unit";
            case 9: answerKey=9;
                return ansTwo = "Universal Synchronised Bus";
            case 10: answerKey=10;
                return ansTwo = "heapsort";

        } return null;
    }

    public String getAnsThreeText(int key) {
        answerKey = key;
        switch(answerKey) {
            case 1: answerKey=1;
                return ansThree = "Smalltalk";
            case 2: answerKey=2;
                return ansThree = "Ruby";
            case 3: answerKey=3;
                return ansThree = "megabyte";
            case 4: answerKey=4;
                return ansThree = "Microsoft Office XP";
            case 5: answerKey=5;
                return ansThree = "Photoelectric cells";
            case 6: answerKey=6;
                return ansThree = "EBCDIC";
            case 7: answerKey=7;
                return ansThree = "Laser";
            case 8: answerKey=8;
                return ansThree = "Read Only Memory";
            case 9: answerKey=9;
                return ansThree = "Unlimited Sending Buffer";
            case 10: answerKey=10;
                return ansThree = "quicksort";
        }

        return null;
    }

    public String getAnsFourText(int key) {
        answerKey = key;
        switch(answerKey) {
            case 1: answerKey=1;
                return ansFour = "Ruby";
            case 2: answerKey=2;
                return ansFour = "LISP";
            case 3: answerKey=3;
                return ansFour = "kilobyte";
            case 4: answerKey=4;
                return ansFour = "BSD Unix";
            case 5: answerKey=5;
                return ansFour = "Floppy disks";
            case 6: answerKey=6;
                return ansFour = "Hollerith";
            case 7: answerKey=7;
                return ansFour = "Pascal";
            case 8: answerKey=8;
                return ansFour = "Hard Disk";
            case 9: answerKey=9;
                return ansFour = "Universal Serial Bus";
            case 10: answerKey=10;
                return ansFour = "mergesort";
        }

        return null;
    }

    // correctAns value returned to check if selected answer is correct
    public int getCorrectAns(int key) {
        correctKey = key;
        switch(correctKey) {
            case 1: correctKey=1;
                return correctAns=2;
            case 2: correctKey=2;
                return correctAns = 4;
            case 3: correctKey=3;
                return correctAns = 3;
            case 4: correctKey=4;
                return correctAns = 3;
            case 5: correctKey=5;
                return correctAns = 2;
            case 6: correctKey=6;
                return correctAns = 1;
            case 7: correctKey=7;
                return correctAns = 3;
            case 8: correctKey=8;
                return correctAns = 2;
            case 9: correctKey=9;
                return correctAns = 4;
            case 10: correctKey=10;
                return correctAns = 1;
        }
        return correctAns;
    }

    public int getTotalQuestions() {
        return total;
    }

    public int getTotalCorrect() {
        return correct;
    }

    public void incrementTotal() {
        total++;
    }

    public void incrementCorrect() {
        correct++;
    }
}
