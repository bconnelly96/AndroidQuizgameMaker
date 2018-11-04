package edu.temple.quizgame.database_mgmt;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;


public class QuizWriter {
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
        4   Other answers (separated by semicolons)
        5   Question #2 (true/false)
        6   true
        7   false
        8   Question #3
        9   answer
        10  other answers
        11

        Filename would be 'quiz_id.dat'

    */

    /*  Creates a file of specified size
        Returns 1 if file was created successfully, -1 otherwise */
    public static int createFile(Context context, String filename){
        File file = new File(context.getFilesDir(), filename);
        if (file.exists()){
            return 1;
        }
        return -1;

    }

    /*Writes given data to specified filename*/
    public static void writeToFile(Context context, String filename, String data) throws IOException {
        try {
            OutputStream os = new FileOutputStream(new File(context.getFilesDir(), filename));
            os.write(data.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*Writes given quiz to its appropriate file */
    public static void writeQuizToFile(Context context, QuizSession quiz, int num_questions, long id) throws IOException {

        //check to make sure quiz is not null
        if (quiz == null){
            throw new NullPointerException("Quiz has no reference");
        }
        //Setup buffer to write
        StringBuilder data = new StringBuilder();
        //Append file header to string
        data.append(setFileHeader(quiz.getQuizName(),num_questions));
        //Loop through quiz to add questions to buffer
        Question curr;
        for (int i = 0; i < num_questions; i++ ){
            curr = quiz.getQuestion(i);
            data.append(curr.getQuestion()).append("\n");
            data.append(curr.getCorrectAnswer().toString()).append("\n");
            data.append(curr.getAnswers().toString().substring(1,curr.getAnswers().toString().length()-1)).append("\n");

        }
        //Write buffer to file
        writeToFile(context,id + ".dat",data.toString());

    }

    private static String setFileHeader(String quiz_name, int num_questions) {
        return  quiz_name + "\n" + num_questions + "\n";
    }
    
}
