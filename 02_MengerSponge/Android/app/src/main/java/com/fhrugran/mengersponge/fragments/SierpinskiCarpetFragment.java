package com.fhrugran.mengersponge.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.fhrugran.mengersponge.R;
import com.fhrugran.mengersponge.data.Pattern;
import com.fhrugran.mengersponge.views.SierpinskiCarpetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created by Fhrugran
 */

public class SierpinskiCarpetFragment extends BaseFragment {
    public static final String TAG = SierpinskiCarpetFragment.class.getSimpleName();

    @BindView(R.id.ch1)
    CheckBox ch00;

    @BindView(R.id.ch4)
    CheckBox ch01;

    @BindView(R.id.ch7)
    CheckBox ch02;

    @BindView(R.id.ch2)
    CheckBox ch10;

    @BindView(R.id.ch5)
    CheckBox ch11;

    @BindView(R.id.ch8)
    CheckBox ch12;

    @BindView(R.id.ch3)
    CheckBox ch20;

    @BindView(R.id.ch6)
    CheckBox ch21;

    @BindView(R.id.ch9)
    CheckBox ch22;

    SierpinskiCarpetView canvasDrawing;
    private boolean setup = false;

    public SierpinskiCarpetFragment() {
        //Default empty constructor
    }

    public static SierpinskiCarpetFragment newInstance() {
        Bundle args = new Bundle();

        SierpinskiCarpetFragment fragment = new SierpinskiCarpetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sierpinski, container, false);
        ButterKnife.bind(this, rootView);
        setup = false;
        int[][] pattern = Pattern.getInstance().getPattern();
        ch00.setChecked(pattern[0][0] == 1);
        ch01.setChecked(pattern[0][1] == 1);
        ch02.setChecked(pattern[0][2] == 1);
        ch10.setChecked(pattern[1][0] == 1);
        ch11.setChecked(pattern[1][1] == 1);
        ch12.setChecked(pattern[1][2] == 1);
        ch20.setChecked(pattern[2][0] == 1);
        ch21.setChecked(pattern[2][1] == 1);
        ch22.setChecked(pattern[2][2] == 1);

        RelativeLayout canvasLayout = (RelativeLayout) rootView.findViewById(R.id.canvasLayout);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        canvasDrawing = new SierpinskiCarpetView(getActivity());
        canvasDrawing.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        canvasLayout.addView(canvasDrawing);

        return rootView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                canvasDrawing.setXYSize(view.getWidth(), view.getHeight());
            }
        });
        setup = true;
    }


    /**
     * ButterKnife Methods
     */

    @OnCheckedChanged(R.id.ch1)
    public void onCh00Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(0, 0);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch4)
    public void onCh01Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(0, 1);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch7)
    public void onCh02Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(0, 2);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch2)
    public void onCh10Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(1, 0);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch5)
    public void onCh11Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(1, 1);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch8)
    public void onCh12Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(1, 2);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch3)
    public void onCh20Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(2, 0);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch6)
    public void onCh21Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(2, 1);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }

    @OnCheckedChanged(R.id.ch9)
    public void onCh22Changed() {
        if (setup) {
            Pattern.getInstance().flipPatternAt(2, 2);
            if (canvasDrawing != null) {
                canvasDrawing.recreate();
            }
        }
    }
}
