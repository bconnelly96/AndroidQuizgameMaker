package edu.temple.quizgame;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.temple.quizgame.database_mgmt.QuizReader;
import edu.temple.quizgame.database_mgmt.QuizWriter;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;

public class IO_Testing {

    @Test
    public void main() throws IOException {

        //Test 1 .dat file creation
        int dat = QuizWriter.createFile("test.dat",4);
        System.out.println("Test 1 == " + (dat==1));

        //Test 2 write to .dat file and read from it
        String input = "Successfully replaced line 0";
        try {
            QuizWriter.replaceLine("test.dat",0,input);
            List<String> text = QuizReader.read_file("test.dat");
            System.out.println("Test 2 == " + text.get(0).equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Test 3 create new quiz session
        QuizSession quiz = new QuizSession();
        if(quiz.getID() < 0 || quiz.getID() > 0 ){
            System.out.println("Test 3 == true ");
        }
        else{
            System.out.println("Test 3 == false ");
        }

        //Test 4 set and get quiz name
        String quiz_name = "TestQuiz001";
        quiz.setQuizName(quiz_name);
        String newQuiz_name = quiz.getQuizName();
        System.out.println("Test 4 == " + newQuiz_name.equals(quiz_name));

        //Test 5 generate a quiz with answers
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 11; i<14; i++){

            answers.add("Incorrect Ans = " + i);
        }

        ArrayList<Question> questions = new ArrayList<>();
        for(int i = 1; i<6; i++){

            Question<String> q = new Question<>("Question #" + i, "Correct Ans = " + (i+5) ,answers);
            quiz.addQuestion(q);
            quiz.incrementNumQuestions();
        }

        try {
            QuizWriter.writeQuizToFile(quiz,quiz.getNumQuestions(),1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
