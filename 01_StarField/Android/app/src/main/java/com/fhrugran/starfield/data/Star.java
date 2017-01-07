package com.fhrugran.starfield.data;

import java.util.Random;

/**
 * Created by Fhrugran
 */

public class Star {
    private float x;
    private float y;
    private float z;
    private float pz;

    private float width;
    private float height;
    private Random random;

    public Star(float width, float height) {
        this.random = new Random();
        this.width = width;
        this.height = height;
        x = random.nextFloat() * width - width / 2;
        y = random.nextFloat() * height - height / 2;
        z = random.nextFloat() * width;
        pz = z;
    }

    public void update(float speed) {
        z = z - speed;
        if (z < 1) {
            z = width;
            x = random.nextFloat() * width - width / 2;
            y = random.nextFloat() * height - height / 2;
            pz = z;
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getPz() {
        return pz;
    }

    public void setPz(float pz) {
        this.pz = pz;
    }
}
