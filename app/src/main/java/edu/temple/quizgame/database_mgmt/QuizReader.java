package edu.temple.quizgame.database_mgmt;

import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import edu.temple.quizgame.game_logic.Question;

public class QuizReader {


    /*NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
           for data manipulation.
           QuizReader.getQuiz() will be the only method that can retrieve a quiz from the quiz data file.
           Once a quiz is loaded, my methods will be able to add/change/delete data with ease.

        Updated File format:
        Each quiz gets its own .txt file.

        0  Quiz name;quiz_id (String)/(int)
        1  Number of questions (int)
        2  Question #1 (multiple choice)
        3  Correct answer
        4  Other answers (separated by semicolons)
        5  Question #2 (true/false)
        6  true
        7  false
        7  Question #3
        8  answer
        9  other answers
        10
        Filename would be 'Quiz_Name.txt'

        A single file named quiz_index.dat will store the file name for each quiz.
        Each quiz is assigned an ID. Said ID will be the index in which the name of the .txt file
        to where that quiz's data is stored.

        0  Quiz_name.txt    //quiz name's id =1
        1  Quiz_name2.txt   //quiz name2's id =2
        2  Quiz_name3.txt   //quiz name3's id =3


        indexOf(String str) Returns the index within this string of the first occurrence of the specified substring.
        indexOf(int ch, int fromIndex) Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
     */


   /* Reads a file line-by-line and returns the contents as a List*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<String> read_file(String filename) throws IOException {

        Path path = Paths.get(filename);
        return Files.readAllLines(path, StandardCharsets.UTF_8);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Object> getQuiz(String quiz_name) throws IOException {

        //Load quiz data to a list
        List<String> quiz_text = read_file(quiz_name);
        //Create arraylist for Question objects
        ArrayList<Object> quiz = new ArrayList<>();
        Question curr;
        int n = 0;
        //Iterate through quiz to load questions and answers into memory
        for (int i = 2; i < quiz_text.size()-1; i++){
            curr = new Question(null,null,null);
            curr.setQuestion(quiz_text.get(i));
            curr.setCorrectAnswer(quiz_text.get(i+1));
            //NEEDS setAnswers() METHOD HERE

        }
        return quiz;
    }

}
