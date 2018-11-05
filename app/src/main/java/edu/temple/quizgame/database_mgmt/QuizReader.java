package edu.temple.quizgame.database_mgmt;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;

public class QuizReader {
    /*NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
           for data manipulation. SOLVED see QuizSession.java
           QuizReader.getQuiz() should be the only method that can retrieve a quiz from the quiz data file.
           Once a quiz is loaded, our combined methods will be able to add/change/delete data with ease.

        Updated File format:
        Each quiz gets its own .dat file.

        0  Quiz name (String)
        1  Number of questions (int)
        2  Question #1 (multiple choice)
        3  Correct answer
        4  Other answers (separated by semicolons)
        5  Question #2 (true/false)
        6  true
        7  false
        8  Question #3
        9  answer
        10  other answers
        11

        Filename would be 'quiz_id.dat'

    */


    /* Reads a file line-by-line and returns the contents as a List*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<String> read_file(Context context, String filename) throws IOException {

        ArrayList<String> file_content = null;
        FileInputStream is = context.openFileInput(filename);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);
            file_content = new ArrayList<>();
            String str;
            while ((str = buffer.readLine()) != null) {
                file_content.add(str);
            }
            buffer.close();
        }
        return file_content;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static QuizSession getQuiz(Context context, long id) throws IOException {

        //Load quiz data to a list
        ArrayList<String> quiz_text = read_file(context, id + ".dat");
        //Create arraylist for Question objects
        QuizSession quiz = new QuizSession();
        Question curr;
        int n = 0;
        //Iterate through quiz to load questions and answers into memory
        for (int i = 2; i < quiz_text.size()-2; i += 3){
            curr = new Question<>(quiz_text.get(i),quiz_text.get(i+1),answersToArrayList(quiz_text.get(i+2)));
            quiz.addQuestion(curr);
            quiz.incrementNumQuestions();
        }
        quiz.setQuizName(quiz_text.get(0));
        quiz.setManualID(id);

        return quiz;
    }

    private static ArrayList<String> answersToArrayList(String answers){

        String[] delim = answers.split(", ");

        return (new ArrayList<>(Arrays.asList(delim)));
    }

}
