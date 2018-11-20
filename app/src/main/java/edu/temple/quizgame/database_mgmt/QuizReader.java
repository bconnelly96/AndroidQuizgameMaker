package edu.temple.quizgame.database_mgmt;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

public class QuizReader {
    private static final String TAG = "QuizReader";
    /*NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
           for data manipulation. SOLVED see QuizSession.java
           QuizReader.getQuiz() should be the only method that can retrieve a quiz from the quiz data file.
           Once a quiz is loaded, our combined methods will be able to add/change/delete data with ease.

        Updated File format:
        Each quiz gets its own .dat file.

        0   Quiz name (String)
        1   Number of questions (int)
        2   Question #1 (multiple choice)
        3   Correct answer
        4   Other answers (separated by commas)
        5   Question #2 (true/false)
        6   true
        7   false
        8   Question #3
        9   answer
        10  other answers
        11

        Filename would be 'quiz_id.dat'

    */


    /* Reads a file line-by-line and returns the contents as a List*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<String> read_file(Context context, String filename) {

        ArrayList<String> file_content = null;
        try {
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
        }
        catch (IOException e){
            Log.e(TAG,e.toString());
        }
        return file_content;
    }

    public static ArrayList<String> getQuizList(Context context){

        File dir = new File(String.valueOf(context.getFilesDir()));
        String[] files = dir.list();
        ArrayList<String> list = new  ArrayList<>();
        for (String file : files) {
            if (file.endsWith(".dat")) {
                list.add(file.substring(0, file.length() - 4)); //-4 = ".dat"
            }
        }


        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static QuizSession getQuiz(Context context, String quiz_name)  {

        //Load quiz data to a list
        ArrayList<String> quiz_text = read_file(context, quiz_name + ".dat");
        //Create arraylist for Question objects
        QuizSession quiz = new QuizSession();
        int n = 0;
        //Iterate through quiz to load questions and answers into memory
        for (int i = 2; i < quiz_text.size()-2; i += 3){
            //check for True/False question
            if (quiz_text.get(i+1).equals("true") || quiz_text.get(i+1).equals("false") ){
                boolean tf;
                tf = quiz_text.get(i + 1).equals("true");
                quiz.addQuestion(new TrueFalseQuestion(quiz_text.get(i),tf,answersToArrayList(quiz_text.get(i + 2))));
                quiz.incrementNumQuestions();
            }
            else {
                quiz.addQuestion(new MultipleChoiceQuestion(quiz_text.get(i), quiz_text.get(i + 1), answersToArrayList(quiz_text.get(i + 2))));
                quiz.incrementNumQuestions();
            }
        }
        quiz.setQuizName(quiz_text.get(0));

        return quiz;
    }

    private static ArrayList<String> answersToArrayList(String answers){

        String[] delim = answers.split(", ");

        return (new ArrayList<>(Arrays.asList(delim)));
    }

}
