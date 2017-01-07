package com.fhrugran.starfield.activities;

import android.os.Bundle;

import com.fhrugran.starfield.R;
import com.fhrugran.starfield.fragments.DrawFragment;

/**
 * Created by Fhrugran
 */

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    DrawFragment df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            df = DrawFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.drawing_container, df, DrawFragment.TAG)
                    .commitAllowingStateLoss();
        }
    }
}
