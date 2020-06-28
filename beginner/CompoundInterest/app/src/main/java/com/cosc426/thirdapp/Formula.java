package com.cosc426.thirdapp;


public class Formula {

    static float current;
    static float annual;
    static float years;
    static float rate;
    static double rateConst;
    static int finalAmount;

    public Formula() {}

    public static float getCurrent() {
        return current;
    }

    public static void setCurrent(float current) {
        Formula.current = current;
    }

    public static float getAnnual() {
        return annual;
    }

    public static void setAnnual(float annual) {
        Formula.annual = annual;
    }

    public static int getYears() {
        return Math.round(years);
    }

    public static void setYears(float years) {
        Formula.years = years;
    }

    public static int getRate() {
        return Math.round(rate);
    }

    /* Accounts for float to int loss conversions */
    public static double getRateConst() {
        return rateConst = 2.8623655411;
    }


    public static void setRate(float rate) {
        Formula.rate = rate;
    }

    /* Compute compound interests with set values */
    public static int compute(float current, float annual, float years, float rate) {
        finalAmount = (int)(((getCurrent() + 100*getAnnual()/getRate())*((1 + getRate()/100)^
                        getYears()) - 100* getAnnual()/getRate())/getRateConst());
        return finalAmount;
    }
}
