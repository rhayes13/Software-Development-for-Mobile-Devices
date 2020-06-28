package com.cosc426.onlinepurchase;

public class Calculator {

    private float price = 0.0f;
    private float cost = 0.0f;
    private float warranty = 0.0f;
    private float insurance = 0.0f;
    private float delivery = 0.0f;

    public Calculator() {}

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCost() {
        return cost = getPrice() + getWarranty() +
                getInsurance() + getDelivery();
    }

    public float getWarranty() {
        return warranty*price;
    }

    public void setWarranty(float warranty) {
        this.warranty = warranty;
    }

    public float getInsurance() {
        return insurance*price;
    }

    public void setInsurance(float insurance) {
        this.insurance = insurance;
    }

    public float getDelivery() {
        return delivery;
    }

    public void setDelivery(int d) {
        if(d == 0) {
            delivery = 20;
        } else if(d == 1) {
            delivery = 10;
        } else if(d == 2) {
            delivery = 5;
        }
    }
}
