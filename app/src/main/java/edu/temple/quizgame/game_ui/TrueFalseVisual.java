package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

/*This activity receives a Question object via Intent extra,
 * and uses it to set its UI elements.
 * It also waits for user selection,
 * gathers it from the UI elements,
 * and reports it back to the calling activity
 * */
public class TrueFalseVisual extends AppCompatActivity {
    boolean selectedAnswer = false;

    TrueFalseQuestion tfQuestion;

    TextView qText;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_visual);

        // Receive Intent from starting activity
        Intent qIntent = getIntent();
        tfQuestion = (TrueFalseQuestion) qIntent.getSerializableExtra("tf_obj");

        qText = findViewById(R.id.tf_textView);
        radioGroup = findViewById(R.id.tf_rg);

        // Set question text, and wait for user to select from RadioGroup
        qText.setText(tfQuestion.getQuestion());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tf_rb1:
                        selectedAnswer = true;
                        break;
                    case R.id.tf_rb2:
                        selectedAnswer = false;
                        break;
                }
                reportAndStart();
            }
        });
    }

    /*Creates a new Intent.
    *Sends user's selected answer back to original starting activity via Extra.
    *Starts starting activity.*/
    private void reportAndStart() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("tf_answer", selectedAnswer);
        startActivity(intent);
    }
}