package edu.temple.quizgame.database_mgmt;
import java.io.*;

public class QuizReader {


    /*
        NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
          for data manipulation.
          QuizReader.getQuiz() will be the only method that can retrieve a quiz from the quiz data file.
          Once a quiz is loaded, my methods will be able to add/change/delete data with ease.

       File format:

       0  2     <---------Number of quizzes
       1  Quiz name;quiz_id (String)/(int)
       2  Number of questions (int)
       3  Question #1
       4  a1,a2,a3,a4
       5  Question #2
       6  true,false
       7  Question #3
       8  answer
       9  .    <-------- end of quiz
       10 Quiz name;quiz_id (String)/(int)
       11 Number of questions (int)
       12 Question #1
       13 a1,a2,a3,a4


       and so on...
    */


   /* Reads a file*/
    public void read_file(String filename) throws IOException {

        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new java.io.FileReader(file));

        String str;
        while ((str = buffer.readLine()) != null)
            System.out.println(str);
        buffer.close();
    }

    public static void getQuiz() {


    }

    public static void getQuestion() {


    }
}
