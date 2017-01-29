package com.fhrugran.mengersponge.data;

import java.util.ArrayList;

/**
 * Created by Fhrugran
 */

public class Square {
    private int x;
    private int y;
    private int l;

    public Square(int x, int y, int l) {
        this.x = x;
        this.y = y;
        this.l = l;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public ArrayList<Square> generate() {
        ArrayList<Square> squares = new ArrayList<>();
        int[][] pattern = Pattern.getInstance().getPattern();
        int newL = l /3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (pattern[i][j] == 1) {
                    squares.add(new Square(x + i*newL, y + j*newL, newL));
                }
            }
        }
        return squares;
    }
}

