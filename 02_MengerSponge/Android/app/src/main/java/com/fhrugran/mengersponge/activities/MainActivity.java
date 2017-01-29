package com.fhrugran.mengersponge.activities;

import android.os.Bundle;

import com.fhrugran.mengersponge.R;
import com.fhrugran.mengersponge.fragments.SierpinskiCarpetFragment;

/**
 * Created by Fhrugran
 */

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    SierpinskiCarpetFragment scf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            scf = SierpinskiCarpetFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.drawing_container, scf, SierpinskiCarpetFragment.TAG)
                    .commitAllowingStateLoss();
        }
    }
}
