package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

public class QuestionAdder extends AppCompatActivity {
    final int NUM_ANSWER_FIELDS = 4;
    /*UI Elements for user interaction*/
    EditText quizName;
    EditText questionField;
    Switch questionTypeChoice;
    RadioGroup correctAnswerSelectorTF;
    RadioGroup correctAnswerSelectorMC;
    EditText[] multipleChoiceAnswerFields;
    Button doneButton;
    Button nextQuestionButton;
    /*game logic variables; bridge between UI and logic components of application*/
    QuizSession quizSession;
    private String[] mcAnswerStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

        initFields();

        /*Question adding mechanism
         * triggered by a
         * click on the 'next' button*/
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestionToQuiz();
                clearFields();
            }
        });

        /*Question adding mechanism; new Activity launched
         * triggered by a
         * click on the 'done' button*/
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizSession.setQuizName(getQuizName());
                addQuestionToQuiz();
            }
        });
    }

    /*initialize all UI elements and member variables*/
    public void initFields() {
        quizName = findViewById(R.id.quiz_name_et);
        questionField = findViewById(R.id.question_field_et);
        questionTypeChoice = findViewById(R.id.question_type_choice_s);
        correctAnswerSelectorTF = findViewById(R.id.true_false_selector_rg);
        correctAnswerSelectorMC = findViewById(R.id.correct_rg);
        multipleChoiceAnswerFields[0] = findViewById(R.id.answer_et_0);
        multipleChoiceAnswerFields[1] = findViewById(R.id.answer_et_1);
        multipleChoiceAnswerFields[2] = findViewById(R.id.answer_et_2);
        multipleChoiceAnswerFields[3] = findViewById(R.id.answer_et_3);
        doneButton = findViewById(R.id.done_button);
        nextQuestionButton = findViewById(R.id.next_button);

        quizSession = new QuizSession();
    }

    /*retrieves and returns string representing user's question from questionField EditText*/
    private String getUserQuestion() {
        return questionField.getText().toString();
    }

    /*retrieves and returns user's Quiz Name selection from quizName EditText*/
    private String getQuizName() {
        return quizName.getText().toString();
    }

    /*returns the user's selection for a correct answer for a multiple choice question*/
    private String getCorrectAnswerMC() {
        int selectedID = correctAnswerSelectorMC.getCheckedRadioButtonId();
        View rb = correctAnswerSelectorMC.findViewById(selectedID);
        int ioc = correctAnswerSelectorMC.indexOfChild(rb);
        RadioButton selectedButton = (RadioButton) correctAnswerSelectorMC.getChildAt(ioc);
        return selectedButton.getText().toString();
    }

    /*returns the user's selection for a correct answer for a true false question*/
    private boolean getCorrectAnswerTF() {
        int selectedID = correctAnswerSelectorTF.getCheckedRadioButtonId();
        View rb = correctAnswerSelectorTF.findViewById(selectedID);
        int ioc = correctAnswerSelectorTF.indexOfChild(rb);
        RadioButton selectedButton = (RadioButton) correctAnswerSelectorTF.getChildAt(ioc);
        if (selectedButton.getText().toString().equals("True")) {
            return true;
        } else {
            return false;
        }
    }

    /*determines and returns whether the current question is T/F or M-C based on user selection on questionChoice Switch*/
    private boolean isTrueFalse() {
        return true;
    }

    /*returns true if all of the data fields have been altered by the user*/
    private boolean allFieldsValid() {
        return true;
    }

    //TODO: reset RadioGroup and switch
    /*Clears the various fields after user input is saved for each question*/
    void clearFields() {
        questionField.setText("");
        for (int i = 0; i < NUM_ANSWER_FIELDS; i++) {
            multipleChoiceAnswerFields[i].setText("");
        }

    }

    private void addQuestionToQuiz() {
        ArrayList answers = new ArrayList();
        Question question;

        /*toast the user and do not save data if any field is empty*/
        if (!allFieldsValid()) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
        } else {
            /*user has indicated that the question is true false*/
            if (isTrueFalse()) {
                answers.add(true);
                answers.add(false);
                question = new TrueFalseQuestion(getUserQuestion(), getCorrectAnswerTF(), answers);
                /*user has indicated that the question is multiple choice*/
            } else {
                /*get user answers from answer fields*/
                for (int i = 0; i < NUM_ANSWER_FIELDS; i++) {
                    answers.add(i, multipleChoiceAnswerFields[i].getText().toString());
                }
                question = new MultipleChoiceQuestion(getUserQuestion(), getCorrectAnswerMC(), answers);
            }
            quizSession.addQuestion(question);
            quizSession.incrementNumQuestions();
        }
    }
}