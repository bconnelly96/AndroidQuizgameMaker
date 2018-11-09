package edu.temple.quizgame.game_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import edu.temple.quizgame.R;

public class TFAdderFragment extends Fragment {

    EditText qText;
    RadioGroup correct;

    TFInterface TFListener;

    public TFAdderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TFListener = (TFInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tf_adder_fragment_layout, container, false);
        qText = v.findViewById(R.id.editTextQTF);
        correct = v.findViewById(R.id.tf_a_rg);
        return v;
    }

    public interface TFInterface {
        void sendQ(String question);
        void sendAnswer(boolean answer);
    }

    /*Called by parent when next button is clicked (user wishes to submit current question)
     * returns true if user has entered valid input for each field*/
    public boolean nextClicked() {
        if (allFieldsValid()) {
            TFListener.sendAnswer(getAnswer());
            TFListener.sendQ(getQuestion());
            clearFields();
            return true;
        }
        return false;
    }

    public boolean doneClicked() {

    }

    //TODO: implement method aFV
    private boolean allFieldsValid() {
    }

    //TODO: implement method cF
    private void clearFields() {

    }

    private String getQuestion() {
        return qText.getText().toString();
    }

    //TODO: implement method gA
    private boolean getAnswer() {

    }
}
