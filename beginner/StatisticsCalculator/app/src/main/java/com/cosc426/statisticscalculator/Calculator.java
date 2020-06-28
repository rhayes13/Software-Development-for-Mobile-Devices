package com.cosc426.statisticscalculator;


public class Calculator {

    public float[] array;

    public Calculator() {}

    public float[] getArray() {
        return array;
    }

    public void setArray(float[] array) {
        this.array = array;
    }

    public float getSum() {
        float sum = 0.0f;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public float getMean() {
        return getSum()/array.length;
    }

    public float getMedian() {
        int mid = array.length/2;
        if(array.length%2 == 1) {
            return array[mid];
        } else {
            return (array[mid-1] + array[mid]) / 2;
        }
    }

    public float getStdv() {
        double sd = 0.0f;
        for(float num : array) {
            sd += num;
        }
        for(float num : array) {
            sd += Math.pow(num - getMean(), 2);
        }
        return (float)Math.sqrt(sd/array.length);
    }

    public float getMin() {
        float min = array[0];
        for(int i = 1; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public float getMax() {
        float max = array[0];
        for(int i = 1; i < array.length; i++) {
            if(array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
