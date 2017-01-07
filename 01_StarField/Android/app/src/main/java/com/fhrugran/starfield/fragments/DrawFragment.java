package com.fhrugran.starfield.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.fhrugran.starfield.R;
import com.fhrugran.starfield.views.CanvasDrawingView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fhrugran
 */

public class DrawFragment extends BaseFragment {
    public static final String TAG = DrawFragment.class.getSimpleName();

    @BindView(R.id.draw_fragment_pb)
    ProgressBar progressBar;

    @BindView(R.id.speedSeeker)
    SeekBar speedSeeker;

    @BindView(R.id.starsToShowSeeker)
    SeekBar starSeeker;

    CanvasDrawingView canvasDrawing;

    public DrawFragment() {
        // Required empty public constructor
    }

    public static DrawFragment newInstance() {

        Bundle args = new Bundle();

        DrawFragment fragment = new DrawFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_canvas, container, false);
        ButterKnife.bind(this, rootView);

        RelativeLayout canvasLayout = (RelativeLayout) rootView.findViewById(R.id.canvasLayout);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        canvasDrawing = new CanvasDrawingView(getActivity());
        canvasDrawing.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        //canvasDrawing.setCanvasEvents(this);
        canvasLayout.addView(canvasDrawing);

        speedSeeker.setMax((int) CanvasDrawingView.MAX_SPEED);
        speedSeeker.setProgress(25);
        speedSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                canvasDrawing.setSpeed(progress + 0.01f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        starSeeker.setMax(CanvasDrawingView.MAX_STARS);
        starSeeker.setProgress(750);
        starSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                canvasDrawing.setStarsToShow(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        progressBar.setVisibility(View.GONE);
    }


}
