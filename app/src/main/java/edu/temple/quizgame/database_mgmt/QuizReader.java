package edu.temple.quizgame.database_mgmt;
import android.annotation.TargetApi;
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
        2  Question #1
        3  a1,a2,a3,a4
        4  Question #2
        5  true,false
        6  Question #3
        7  answer
        8
        Filename would be 'Quiz_Name.txt'

        A single file named quiz_index.dat will store some basic info about the quiz.
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
    public void getQuiz(String quiz_name) throws IOException {

        //Load quiz data to a list
        List<String> quiz_text = read_file(quiz_name);
        //Create arraylist for Question objects
        ArrayList<Question> question_list = new ArrayList<Question>();
        Question curr;
        int n = 0;
        //Iterate through quiz to load questions and answers in memory
        for (int i = 2; i < quiz_text.size()-1; i++){
            //curr =;
        }

    }

    public static void getQuestion() {


    }
}
