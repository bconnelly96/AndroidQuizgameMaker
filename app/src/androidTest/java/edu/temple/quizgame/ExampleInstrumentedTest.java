package edu.temple.quizgame;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import edu.temple.quizgame.database_mgmt.QuizReader;
import edu.temple.quizgame.database_mgmt.QuizWriter;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws IOException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.temple.quizgame", appContext.getPackageName());

        String filename = "text.txt";

        //Test 1 create, write, and read from a file
        int test = QuizWriter.createFile(appContext,filename);
        assertEquals(1,test);
        String input = "writeToFile = true";
        QuizWriter.writeToFile(appContext, filename, input);
        ArrayList<String> file_contents = QuizReader.read_file(appContext,filename);
        assertEquals("writeToFile = true",file_contents.get(0));


        //Test 2 create quiz and write to file
        QuizSession quiz = new QuizSession();
        String quiz_name = "TestQuiz001";
        quiz.setQuizName(quiz_name);

        ArrayList<String> answers = new ArrayList<>();
        for(int i = 11; i<14; i++){
            answers.add("Incorrect Ans = " + i);
        }
        for(int i = 1; i<6; i++){
            Question q = new Question<>("Question #" + i, "Correct Ans = " + (i+5) ,answers);
            quiz.addQuestion(q);
            quiz.incrementNumQuestions();
        }
        QuizWriter.writeQuizToFile(appContext,quiz,quiz.getNumQuestions(),quiz.getID());

        ArrayList<String> text = QuizReader.read_file(appContext,quiz.getID()+".dat");
        assertEquals(quiz_name,text.get(0));

        //Test 3 getQuiz()
        QuizSession quiz2 = QuizReader.getQuiz(appContext,quiz.getID());
        assertEquals(quiz.getID(),quiz2.getID());
        for (int i = 0; i < quiz2.getNumQuestions(); i++) {
            assertEquals(quiz.getQuestion(i).getQuestion(), quiz2.getQuestion(i).getQuestion());
            assertEquals("Question #" + (i+1), quiz2.getQuestion(i).getQuestion());
            for(int j =0; j < 3; j++) {
                String q1 = (String) quiz.getQuestion(j).getAnswers().get(j);
                String q2 = (String) quiz2.getQuestion(j).getAnswers().get(j);
                assertEquals(q1,q2);
            }
        }



    }
}
