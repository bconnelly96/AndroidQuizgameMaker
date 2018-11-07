package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;


/*This activity receives a Question object via Intent extra,
* uses it to set its UI elements,
* waits for user selection,
* and reports user selection back to the calling activity
* */
public class MultipleChoiceVisual extends AppCompatActivity implements View.OnClickListener {

    private final int NUM_BUTTONS = 4;

    String selectedAnswer = "";
    MultipleChoiceQuestion mcQuestion;


    TextView qText;
    Button[] answers = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_visual);

        Intent qIntent = getIntent();
        mcQuestion = (MultipleChoiceQuestion) qIntent.getSerializableExtra("mc_obj");

        qText = findViewById(R.id.mc_textView);
        answers[0] = findViewById(R.id.mc_1);
        answers[1] = findViewById(R.id.mc_2);
        answers[2] = findViewById(R.id.mc_3);
        answers[3] = findViewById(R.id.mc_4);

        setElements();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mc_1:
                selectedAnswer = answers[0].getText().toString();
                break;
            case R.id.mc_2:
                selectedAnswer = answers[1].getText().toString();
                break;
            case R.id.mc_3:
                selectedAnswer = answers[2].getText().toString();
                break;
            case R.id.mc_4:
                selectedAnswer = answers[3].getText().toString();
                break;
            default:
                break;
        }

        if (selectedAnswer.equals(mcQuestion.getCorrectAnswer())) {
            Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
        }

        reportAndStart();
    }

    /*set the UI elements and give each button a listener*/
    private void setElements() {
        qText.setText(mcQuestion.getQuestion());
        for (int i = 0; i < NUM_BUTTONS; i++) {
            answers[i].setText(mcQuestion.getAnswers().get(i).toString());
            answers[i].setOnClickListener(this);
        }
    }

    void reportAndStart() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("mc_answer", selectedAnswer);
        startActivity(intent);
    }
}