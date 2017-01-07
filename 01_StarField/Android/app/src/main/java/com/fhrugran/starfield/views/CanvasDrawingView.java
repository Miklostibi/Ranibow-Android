package com.fhrugran.starfield.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.fhrugran.starfield.R;
import com.fhrugran.starfield.data.Star;

/**
 * Created by Fhrugran
 */

public class CanvasDrawingView extends View {
    public static final String TAG = CanvasDrawingView.class.getSimpleName();

    public static final int MAX_STARS = 2000;
    public static final float MAX_SPEED = 100f;
    Star[] stars = new Star[MAX_STARS];
    int starsToShow = 750;
    float speed = 25;


    int width, height;
    boolean recreated = false;
    private Paint paint = new Paint();
    private Paint linepaint = new Paint();

    public CanvasDrawingView(Context context) {
        super(context);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        linepaint.setColor(Color.WHITE);
        linepaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linepaint.setStrokeWidth(2);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2f, getHeight() / 2f);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.md_black));

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

    public void setStarsToShow(int starsToShow) {
        if (starsToShow > 0 && starsToShow < MAX_STARS) {
            this.starsToShow = starsToShow;
        }
    }

    public void setSpeed(float speed) {
        if (speed > 0 && speed < MAX_SPEED) {
            this.speed = speed;
        }
    }

    /**
     * Class Private Methods
     */

    void recreate() {
        for (int i = 0; i < MAX_STARS; ++i) {
            stars[i] = new Star(width, height);
            //paths[i] = new Path();
            //paths[i].setFillType(Path.FillType.EVEN_ODD);
        }
        recreated = true;
        invalidate();
    }

    void update(Canvas canvas) {
        float sx, sy, r, px, py;
        for (int i = 0; i < starsToShow; i++) {
            stars[i].update(speed);
            sx = map(stars[i].getX() / stars[i].getZ(), 0, 1, 0, width);
            sy = map(stars[i].getY() / stars[i].getZ(), 0, 1, 0, height);
            r = map(stars[i].getZ(), 0, width, 8, 0);

            //Drawing the tail
            px = map(stars[i].getX() / stars[i].getPz(), 0, 1, 0, width);
            py = map(stars[i].getY() / stars[i].getPz(), 0, 1, 0, height);

            if (px > -width
                    && px < width
                    && sx > -width
                    && sx < width
                    && py > -height
                    && py < height
                    && sy + r / 2 > -height
                    && sy + r / 2 < height
                    && sy - r / 2 > -height
                    && sy - r / 2 < height) {
                canvas.drawLine(px, py, sx, sy + r / 2, linepaint);
                canvas.drawLine(px, py, sx, sy - r / 2, linepaint);

//                paths[i].lineTo(px, py);
//                paths[i].lineTo(sx, sy + r / 2);
//                paths[i].lineTo(sx, sy - r / 2);
//                paths[i].close();
//
//                canvas.drawPath(paths[i], paint);
            }
            canvas.drawCircle(sx, sy, r, paint);
            stars[i].setPz(stars[i].getZ());

        }
        invalidate();
    }

    float map(float what, float fromStart, float fromEnd, float toStart, float toEnd) {
        return (what - fromStart) * (toEnd - toStart) / (fromEnd - fromStart) + toStart;
    }
}
