package edu.temple.quizgame.game_ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdderAdapter extends FragmentPagerAdapter {
    Fragment tfAdderFragment;
    Fragment mcAdderFragment;

    public AdderAdapter(FragmentManager fm) {
        super(fm);
        tfAdderFragment = new TFAdderFragment();
        mcAdderFragment = new MCAdderFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return tfAdderFragment;
        } else if (i == 1) {
            return mcAdderFragment;
        }
        return null;
    }
}
