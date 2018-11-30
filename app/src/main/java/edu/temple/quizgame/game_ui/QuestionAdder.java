package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import edu.temple.quizgame.R;
import edu.temple.quizgame.database_mgmt.QuizWriter;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

/*
*Parent Activity of the TFAdderFragment and MCAdderFragment fragments.
*Both fragments are contained within the Activity's ViewPager.
*The Activity sends the fragments a notification when the next Button is clicked,
*indicating a user wishes to create a new question.
*The activity receives values to build a Question object if valid
*values can be retrieved from the UI elements found in the active fragment
*by implementing interfaces found in the two fragments.
*It builds the QuizSession object to be saved to the user's device when
*the done Button is clicked.
*/
public class QuestionAdder extends AppCompatActivity implements TFAdderFragment.TFInterface, MCAdderFragment.MCInterface {
    private final String ADDED = "Question Added to Quiz";
    private final String INVALID = "One or More Fields is Invalid";
    private final String CREATED = "Your Quiz Has Been Created";

    AdderAdapter adderAdapter;
    ViewPager viewPager;

    QuizSession quizSession = new QuizSession();
    private String mcQuestion;
    private String mcCorrect;
    private ArrayList<String> mcAnswers;
    private String tfQuestion;
    private boolean tfCorrect;
    private ArrayList<Boolean> tfAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

        Button done = findViewById(R.id.done_b);
        Button next = findViewById(R.id.next_b);
        final EditText quizName = findViewById(R.id.name_et);
        viewPager = findViewById(R.id.viewPager);

        FragmentManager fm = getSupportFragmentManager();
        adderAdapter = new AdderAdapter(fm);
        viewPager.setAdapter(adderAdapter);
        viewPager.setOffscreenPageLimit(2);

        // TrueFalse questions take on static values true or false
        tfAnswers.add(0, true);
        tfAnswers.add(1, false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                // if the user is currently editing a T/F question
                if (viewPager.getCurrentItem() == 0) {
                    TFAdderFragment tFrag = (TFAdderFragment) adderAdapter.getItem(0);
                    // question added if nextClicked() returns true (data is valid)
                    if (tFrag.nextClicked()) {
                        addTFToQuiz();
                    } else {
                        makeToast(INVALID);
                    }
                // if the user is currently editing a MC question
                } else {
                    MCAdderFragment mFrag = (MCAdderFragment) adderAdapter.getItem(1);
                    // question added if nextClicked() returns true (data is valid)
                    if (mFrag.nextClicked()) {
                        addMCToQuiz();
                    } else {
                        makeToast(INVALID);
                    }
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the user is currently editing a MC question
                if (viewPager.getCurrentItem() == 0) {
                    TFAdderFragment tFrag = (TFAdderFragment) adderAdapter.getItem(0);
                    // add question to quiz if some data has been entered before next button has been clicked
                    if (tFrag.doneClicked()) {
                        if (tFrag.allFieldsValid()) {
                            addTFToQuiz();
                            quizSession.setQuizName(quizName.getText().toString());

                            //Code added by Joshee
                            try {
                                QuizWriter.writeQuizToFile(getApplicationContext(),quizSession,quizSession.getNumQuestions());
                            } catch (IOException e) {
                                Log.e(QuizWriter.TAG,e.toString());
                            }
                            //End code added by Joshee

                            makeToast(CREATED);
                            Intent intent = new Intent(QuestionAdder.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            makeToast(INVALID);
                        }
                    }
                // if the user is currently editing a T/F question
                } else {
                    MCAdderFragment mFrag = (MCAdderFragment) adderAdapter.getItem(1);
                    // add question to quiz if some data has been entered before next button has been clicked
                    if (mFrag.doneClicked()) {
                        if (mFrag.allFieldsValid()) {
                            addMCToQuiz();
                            quizSession.setQuizName(quizName.getText().toString());

                            //Code added by Joshee
                            try {
                                QuizWriter.writeQuizToFile(getApplicationContext(),quizSession,quizSession.getNumQuestions());
                            } catch (IOException e) {
                                Log.e(QuizWriter.TAG,e.toString());
                            }
                            //End code added by Joshee

                            makeToast(CREATED);
                            Intent intent = new Intent(QuestionAdder.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            makeToast(INVALID);
                        }
                    } else {
                        makeToast(INVALID);
                    }
                }
            }
        });
    }

    // Implement methods from the interfaces defined in TFAdderFragment and MCAdderFragment
    @Override
    public void sendMCQ(String question) {
        mcQuestion = question;
    }
    @Override
    public void sendMCAnswers(String[] answers) {
        mcAnswers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mcAnswers.add(i, answers[i]);
        }
    }
    @Override
    public void sendMCCorrect(String correctAnswer) {
        mcCorrect = correctAnswer;
    }
    @Override
    public void sendTFQ(String question) {
        tfQuestion = question;
    }
    @Override
    public void sendTFCorrect(boolean correctAnswer) {
        tfCorrect =correctAnswer;
    }

    // Makes a toast to the user msg as the display text
    private void makeToast(String msg) {
        Toast.makeText(QuestionAdder.this, msg, Toast.LENGTH_SHORT).show();
    }

    // Adds a TrueFalseQuestion object to and updates the QuizSession object
    private void addTFToQuiz() {
       TrueFalseQuestion tfq = new TrueFalseQuestion(tfQuestion, tfCorrect, tfAnswers);
       quizSession.addQuestion(tfq);
       quizSession.incrementNumQuestions();
       makeToast(ADDED);
    }

    // Adds a MultipleChoiceQuestion object to and updates the QuizSession object
    private void addMCToQuiz() {
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(mcQuestion, mcCorrect, mcAnswers);
        quizSession.addQuestion(mcq);
        quizSession.incrementNumQuestions();
        makeToast(ADDED);
    }
}
