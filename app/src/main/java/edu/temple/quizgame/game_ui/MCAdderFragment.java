package edu.temple.quizgame.game_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.temple.quizgame.R;

/*
 *A Fragment representing a Multiple Choice question for
 *the QuestionAdder parent.
 *Contains public methods so parent can notify Fragment when
 *certain Button clicks occur.
 *Contains an interface so Fragment can send parent user input
 *for a given question to add to the quiz
 */
public class MCAdderFragment extends Fragment {
    private final int NUM_ANSWERS = 4;
    private final String EMPTY_FIELD = "";

    EditText[] answers;
    RadioGroup correct;
    EditText qText;

    MCInterface MCListener;

    public MCAdderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MCListener = (MCInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mc_adder_fragment_layout, container, false);
        answers = new EditText[4];
        answers[0] = v.findViewById(R.id.editText1);
        answers[1] = v.findViewById(R.id.editText2);
        answers[2] = v.findViewById(R.id.editText3);
        answers[3] = v.findViewById(R.id.editText4);
        correct = v.findViewById(R.id.mc_a_rg);
        qText = v.findViewById(R.id.editTextQMC);
        return v;
    }

    public interface MCInterface {
        void sendMCQ(String question);
        void sendMCAnswers(String[] answers);
        void sendMCCorrect(String correctAnswer);
    }

    /*Sends question data to the parent if its next button has been clicked
    * returns true if user has entered valid input for each field*/
    public boolean nextClicked() {
        if (allFieldsValid()) {
            MCListener.sendMCAnswers(getAnswers());
            MCListener.sendMCQ(getQuestion());
            MCListener.sendMCCorrect(getCorrectAnswer());
            clearFields();
            return true;
        }
        return false;
    }
    /*Sends question data to the parent if its done button has been clicked
     * returns true if user has entered valid input for each field*/
    public boolean doneClicked() {
        if (allFieldsValid() || allFieldsBlank()) {
            if (allFieldsValid()) {
                MCListener.sendMCQ(getQuestion());
                MCListener.sendMCAnswers(getAnswers());
                MCListener.sendMCCorrect(getCorrectAnswer());
            }
            return true;
        }
        return false;
    }

    /*Returns true if all answer options have been set,
    *and a correct answer and question have been set.*/

    public boolean allFieldsValid() {
        boolean retVal = true;
        for (int i = 0; i < NUM_ANSWERS; i++) {
            if (answers[i].getText().toString().equals(EMPTY_FIELD)) {
                retVal = false;
            }
        }
        if (qText.getText().toString().equals(EMPTY_FIELD) && (correct.getCheckedRadioButtonId() == -1)) {
            retVal = false;
        }
        return retVal;
    }

    /*Returns true if no answer options have been set,
     *and no correct answer or question have been set.*/
    private boolean allFieldsBlank() {
        boolean retVal = true;
        for (int i = 0; i < NUM_ANSWERS; i++) {
            if (!(answers[i].getText().toString().equals(EMPTY_FIELD))) {
                retVal = false;
            }
        }
        if (!(qText.getText().toString().equals(EMPTY_FIELD)) && (correct.getCheckedRadioButtonId() != -1)) {
            retVal = false;
        }
        return retVal;
    }

    // Resets the input fields for the UI elements
    private void clearFields() {
        for (int i = 0; i < NUM_ANSWERS; i++) {
            answers[i].setText(EMPTY_FIELD);
        }

        int id = correct.getCheckedRadioButtonId();
        RadioButton rb = correct.findViewById(id);
        rb.setChecked(false);

        qText.setText(EMPTY_FIELD);
    }

    // Returns the question from the EditText element
    private String getQuestion() {
        return qText.getText().toString();
    }

    // Returns an array of the answer Strings from the EditText array
    private String[] getAnswers() {
        String [] answerStrings = new String[4];
        for (int i = 0; i < NUM_ANSWERS; i++) {
            answerStrings[i] = answers[i].getText().toString();
        }
        return answerStrings;
    }

    /*Returns the user's selection for correct answer
    *from the correct RadioGroup*/
    private String getCorrectAnswer() {
        String correctA = "";
        switch (correct.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                correctA = answers[0].getText().toString();
                break;
            case R.id.radioButton2:
                correctA = answers[1].getText().toString();
                break;
            case R.id.radioButton3:
                correctA = answers[2].getText().toString();
                break;
            case R.id.radioButton4:
                correctA = answers[3].getText().toString();
                break;
        }
        return correctA;
    }
}