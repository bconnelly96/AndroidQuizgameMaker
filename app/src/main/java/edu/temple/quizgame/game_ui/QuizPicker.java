/*package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.temple.quizgame.R;

public class QuizPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_picker);

        //TODO: set adapter
        ListView quizList = findViewById(R.id.list_of_quizzes);



        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent quizStart = new Intent(QuizPicker.this, GameActivity.class);
                // Load the array of quizzes
                // the array of quizzes[position] will be the selected quiz
                // the loaded QuizSession object should be passed in on line 31 as second arg.
                quizStart.putExtra("selected_quiz", );
            }
        });
}*/

