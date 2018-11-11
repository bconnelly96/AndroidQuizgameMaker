package edu.temple.quizgame.game_ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.temple.quizgame.R;


public class QuestionAdder extends AppCompatActivity implements TFAdderFragment.TFInterface, MCAdderFragment.MCInterface {

    AdderAdapter adderAdapter;
    ViewPager viewPager;

    Button done;
    Button next;
    EditText qName;
    TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

        done = findViewById(R.id.done_b);
        next = findViewById(R.id.next_b);
        qName = findViewById(R.id.name_et);
        viewPager = findViewById(R.id.viewPager);
        instruction = findViewById(R.id.instruction);

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void sendMCQ(String question) {

    }

    public void sendMCAnswers(String[] answers) {

    }

    public void sendMCCorrect(String correctAnswer) {

    }

    public void sendTFQ(String question) {

    }

    public void sendTFCorrect(boolean answer) {

    }

    @Override
    public void sendAnswers(String[] answers) {

    }

    @Override
    public void sendCorrect(String correctAnswer) {

    }

    @Override
    public void sendQ(String question) {

    }

    @Override
    public void sendAnswer(boolean answer) {

    }
}