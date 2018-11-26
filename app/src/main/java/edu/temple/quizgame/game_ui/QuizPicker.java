package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.temple.quizgame.R;
import edu.temple.quizgame.database_mgmt.QuizReader;

public class QuizPicker extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_picker);


        //TODO: set adapter
        final ArrayList<String> quizzes = QuizReader.getQuizList(getApplicationContext());
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizzes);
        ListView quizList = findViewById(R.id.list_of_quizzes);
        quizList.setAdapter(adapter);


        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent quizStart = new Intent(QuizPicker.this, GameActivity.class);
                // Load the array of quizzes
                // the array of quizzes[position] will be the selected quiz
                // the loaded QuizSession object should be passed in on line 31 as second arg.
                quizStart.putExtra("selected_quiz",QuizReader.getQuiz(getApplicationContext(),quizzes.get(position)));
                startActivity(quizStart);
            }
        });
    }
}

