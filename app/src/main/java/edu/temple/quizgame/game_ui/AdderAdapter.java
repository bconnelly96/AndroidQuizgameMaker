package edu.temple.quizgame.game_ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
* A FragmentPageAdapter for use in the QuestionAdder activity
* Contains references to a TFAdderFragment and an MCAdderFragment
* */
public class AdderAdapter extends FragmentPagerAdapter {
    // Element 0 in the dataset
    Fragment tfAdderFragment;
    // Element 1 in the dataset
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
    public Fragment getItem(int position) {
        if (position == 0) {
            return tfAdderFragment;
        } else if (position == 1) {
            return mcAdderFragment;
        }
        return null;
    }
}
