package com.cosc426.shootinggame;

import java.util.Random;

public class Game {
    private double birdX;
    private double birdY;
    private double birdSpeed;

    private double bulletX;
    private double bulletY;
    private double bulletSpeed;

    private double gunX;
    private double gunY;

    private double radius;
    private double distanceThreshold;

    private boolean fired;
    private boolean hit;
    private Random r = new Random();

    public Game()
    {
        initializeGame();
    } //randomized values

    public void update()
    {
        moveBird();

        if (fired) {
            moveBullet();
        }

        if (sceneClear())
            initializeGame();
    }

    public void fire()
    {
        fired = true;
    }

    public double getBirdX()
    {
        return birdX;
    }

    public double getBirdY()
    {
        return birdY;
    }

    public double getBulletX()
    {
        return bulletX;
    }

    public double getBulletY()
    {
        return bulletY;
    }

    public double getGunX()
    {
        return gunX;
    }

    public double getGunY()
    {
        return gunY;
    }

    public double getRadius()
    {
        return radius;
    }

    private void moveBird()
    {
        if (!hit && birdY > 0)
        {
            birdY = birdY - birdSpeed;
            hit = decideHit();
        }
        else
            birdY = -90;
    }

    private void moveBullet()
    {
        bulletX = bulletX + bulletSpeed;
    }

    public boolean decideHit()
    {
        if(birdX - bulletX < 30 && 950 <= (birdY + bulletY) && (birdY + bulletY) <= 1050 && bulletX < 1400) {
            //System.out.println("BIRDY: " + birdY + " BULLETY: " + bulletY);
            //play sound
            MainActivity.soundPool.play(MainActivity.soundId, 1, 1, 1, 0, 1);
            return true;
        }
        return false;
    }

    private boolean sceneClear()
    {
        return (birdX < 0 || birdY < -80) && bulletX > 1850;
    }

    private void initializeGame()
    {
        int sceneWidth = 1800;
        int sceneHeight = 1000;
        double gunLength = 200;

        this.birdY = sceneHeight+50;
        this.birdX = sceneWidth - 400 -800 * Math.random();
        this.birdSpeed = 7 + 5 * Math.random();

        this.gunX = gunLength;
        this.gunY = r.nextInt(sceneHeight-700)+500;

        this.bulletX = gunX;
        this.bulletY = gunY;
        this.bulletSpeed = 30;

        this.radius = 50;
        this.distanceThreshold = (50+50)^2;

        this.fired = false;
        this.hit = false;
    }
}
