package com.cosc426.fourthapp;

// Sets credits, returns costs for various options
public class TuitionCalculator {
    static int credits;
    static int academicStatus;
    static int stateStatus;
    static int optionalExpenses;

    public TuitionCalculator() {

    }

    public void setCredits(String c) {
        credits = Integer.parseInt(c);
    }

    public int getCredits() {
        return credits;
    }

    public int getGraduateTuition() {
        return academicStatus = 800;
    }

    public int getUndergradTuition() {
        return academicStatus = 500;
    }

    public int getNonDegreeTuition() {
        return academicStatus = 300;
    }

    public int getInStateStatus() {
        return stateStatus = 1;
    }

    public int getOutOfStateStatus() {
        return stateStatus = 2;
    }

    public int getDormitoryExpense() {
        return optionalExpenses = 5000;
    }

    public int getDiningExpense() {
        return optionalExpenses = 2000;
    }

    public int getParkingExpense() {
        return optionalExpenses = 1000;
    }
}
