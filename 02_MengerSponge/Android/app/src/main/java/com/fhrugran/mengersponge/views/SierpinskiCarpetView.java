package com.fhrugran.mengersponge.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.fhrugran.mengersponge.R;
import com.fhrugran.mengersponge.data.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Fhrugran
 */

public class SierpinskiCarpetView extends View {
    public static final String TAG = SierpinskiCarpetView.class.getSimpleName();

    public static final int MAX_LEVELS = 7;


    int width, height;
    boolean recreated = false;
    private Map<Integer, ArrayList<Square>> levels = new HashMap<>();
    private int currentLevel;
    private Paint paint = new Paint();

    public SierpinskiCarpetView(Context context) {
        super(context);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.md_purple_800));

        if (recreated) {
            update(canvas);
        }
    }

    public void setXYSize(int sizeX, int sizeY) {
        //Initialize the screen metrics that we compare to
        width = sizeX;
        height = sizeY;

        recreate();
    }


    public void recreate() {
        recreated = false;
        for (int i = 0; i < MAX_LEVELS; ++i) {
            if (i == 0) {
                ArrayList<Square> squares = new ArrayList<>();
                if (width > height) {
                    squares.add(new Square(width/2 - height/2, 10, height - 20));
                } else {
                    squares.add(new Square(10, height/2 - width/2, width - 20));
                }
                levels.put(0, squares);
            } else {
                ArrayList<Square> squares = new ArrayList<>();
                for (Square s: levels.get(i-1)) {
                    squares.addAll(s.generate());
                }
                levels.put(i, squares);
            }
        }
        recreated = true;

        invalidate();
    }

    void update(Canvas canvas) {
        if (currentLevel == MAX_LEVELS) {
            currentLevel = 0;
        }
        for (Square s: levels.get(currentLevel)) {
            canvas.drawRect(new Rect(s.getX(), s.getY(), s.getX() + s.getL(), s.getY() + s.getL()), paint);
        }
        ++currentLevel;


        new CountDownTimer(1000 * currentLevel, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                invalidate();
            }
        }.start();
    }

    /**
     * Class Private Methods
     */


    float map(float what, float fromStart, float fromEnd, float toStart, float toEnd) {
        return (what - fromStart) * (toEnd - toStart) / (fromEnd - fromStart) + toStart;
    }
}
