package edu.temple.quizgame.game_ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.temple.quizgame.R;

public class QuestionAdder extends AppCompatActivity {

    AdderAdapter adderAdapter;
    ViewPager viewPager;

    Button done;
    Button next;
    EditText qName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

        done = findViewById(R.id.done_b);
        next = findViewById(R.id.next_b);
        qName = findViewById(R.id.name_et);
        viewPager = findViewById(R.id.viewPager);

        FragmentManager fm = getSupportFragmentManager();
        adderAdapter = new AdderAdapter(fm);
        viewPager.setAdapter(adderAdapter);
        viewPager.setOffscreenPageLimit(2);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem());
                adderAdapter.getItem(viewPager.getCurrentItem());
            }

            @Override
            public void onPageSelected(int i) {
                viewPager.setCurrentItem(viewPager.getCurrentItem());
                adderAdapter.getItem(viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}