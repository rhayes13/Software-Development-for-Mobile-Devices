package com.cosc426.universitycost;

public class Tuition {
    private int credits;
    private String status, dorm, dining;
    private int total;

    public Tuition() {
        status = "undergraduate";
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public int getTotal() {
        //Find status
        int creditCost = 0;
        if(getStatus() == "undergraduate") {
            creditCost = 300;
        }
        if(getStatus() == "graduate") {
            creditCost = 400;
        }

        //Get additional costs
        int dormCost = 0;
        if(getDorm() == "yes") {
            dormCost = 1000;
        } else {
            dormCost = 0;
        }
        int diningCost = 0;
        if(getDining() == "yes") {
            diningCost = 500;
        } else {
            diningCost = 0;
        }

        //Return total
        total = creditCost*getCredits() + dormCost + diningCost;
        return total;
    }
}
