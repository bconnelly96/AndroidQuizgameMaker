package edu.temple.quizgame.database_mgmt;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;


public class QuizWriter {
    private static final String TAG = "QuizWriter";
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

    /* Returns 1 if file was created successfully, -1 otherwise */
    public static int createFile(Context context, String filename) throws IOException {
        FileOutputStream file = context.openFileOutput(filename,Context.MODE_PRIVATE);
        if (file.getFD() != null){
            file.close();
            return 1;
        }
        return -1;

    }

    /*Writes given data to specified filename*/
    public static void writeToFile(Context context, String filename, String data) {

        FileOutputStream os = null;
        try {
            os = context.openFileOutput(filename,Context.MODE_PRIVATE);
            os.write(data.getBytes());
            os.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG,e.toString());
        } catch (IOException e) {
            Log.e(TAG,e.toString());
        }


    }

    /*Writes given quiz to its appropriate file */
    public static void writeQuizToFile(Context context, QuizSession quiz, int num_questions) throws IOException {

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
            if (curr instanceof MultipleChoiceQuestion){
                curr = (MultipleChoiceQuestion) quiz.getQuestion(i);
                data.append(curr.getQuestion()).append("\n");
                data.append(curr.getCorrectAnswer().toString()).append("\n");
                data.append(curr.getAnswer().toString().substring(1,curr.getAnswer().toString().length()-1)).append("\n");
            }
            else if (curr instanceof TrueFalseQuestion){
                curr = (TrueFalseQuestion) quiz.getQuestion(i);
                data.append(curr.getQuestion()).append("\n");
                data.append(curr.getCorrectAnswer().toString()).append("\n");
                data.append(curr.getAnswer().toString().substring(1,curr.getAnswer().toString().length()-1)).append("\n");
            }

        }
        //Write buffer to file
        writeToFile(context,quiz.getQuizName() + ".dat",data.toString());

    }

    private static String setFileHeader(String quiz_name, int num_questions) {
        return  quiz_name + "\n" + num_questions + "\n";
    }
    
}
