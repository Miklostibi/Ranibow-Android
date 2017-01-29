package com.fhrugran.mengersponge.data;

/**
 * Created by Fhrugran
 */

public class Pattern {
    public static Pattern instance;

    private int[][] pattern = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
    };

    private Pattern() {
    }

    public static Pattern getInstance() {
        if (instance == null) {
            instance = new Pattern();
        }
        return instance;
    }

    public int[][] getPattern() {
        return pattern;
    }

    public void flipPatternAt(int x, int y) {
        if (x < 3 && x >= 0 && y < 3 && y >= 0) {
            if (pattern[x][y] == 0) {
                pattern[x][y] = 1;
            } else {
                pattern[x][y] = 0;
            }
        }
    }
}
