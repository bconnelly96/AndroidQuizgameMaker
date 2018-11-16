package edu.temple.quizgame;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.temple.quizgame.database_mgmt.QuizReader;
import edu.temple.quizgame.database_mgmt.QuizWriter;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

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

        ArrayList<String> mc_answers = new ArrayList<>();
        for(int i = 11; i<14; i++){
            mc_answers.add("Incorrect Ans = " + i);
        }

        ArrayList<String> answers = new ArrayList<>();
        answers.add("true");

        for(int i = 1; i<11; i += 2){
            Question q = new TrueFalseQuestion("Question #" + i, false ,answers);
            quiz.addQuestion(q);
            quiz.incrementNumQuestions();
            Question q2 = new MultipleChoiceQuestion("Question #" + (i+1), "Hello World" ,mc_answers);
            quiz.addQuestion(q2);
            quiz.incrementNumQuestions();
        }
        QuizWriter.writeQuizToFile(appContext,quiz,quiz.getNumQuestions());

        ArrayList<String> text = QuizReader.read_file(appContext,quiz.getQuizName()+".dat");
        assertEquals(quiz_name,text.get(0));

        //Test 3 getQuiz()
        QuizSession quiz2 = QuizReader.getQuiz(appContext,quiz.getQuizName());
        assertEquals(quiz.getQuizName(),quiz2.getQuizName());
        assertEquals(10,quiz.getNumQuestions());
        assertEquals(quiz.getNumQuestions(),quiz2.getNumQuestions());
        for (int i = 0; i < quiz2.getNumQuestions(); i++) {
            assertEquals(quiz.getQuestion(i).getQuestion(), quiz2.getQuestion(i).getQuestion());
            assertEquals("Question #" + (i+1), quiz2.getQuestion(i).getQuestion());
            for(int j =0; j < 1; j++) {
                ArrayList a1 = (ArrayList) quiz.getQuestion(j).getAnswer();
                ArrayList a2 = (ArrayList) quiz2.getQuestion(j).getAnswer();
                assertEquals(a1.get(j),a2.get(j));
                }
        }
        String[] dir = QuizReader.getQuizList(appContext);
        assertEquals(dir[0],"Joshee.dat");
        QuizWriter.writeToFile(appContext,"list.txt", Arrays.toString(dir));



    }
}
