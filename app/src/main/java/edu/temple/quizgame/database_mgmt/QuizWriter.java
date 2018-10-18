package edu.temple.quizgame.database_mgmt;

import android.os.Build;
import android.support.annotation.RequiresApi;

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
import java.util.List;

public class QuizWriter {
    /*NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
           for data manipulation.
           QuizReader.getQuiz() will be the only method that can retrieve a quiz from the quiz data file.
           Once a quiz is loaded, my methods will be able to add/change/delete data with ease.

        File format:

        1  Quiz name;quiz_id (String)/(int)
        2  Number of questions (int)
               List questions with answers on the next line
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


        indexOf(String str) Returns the index within this string of the first occurrence of the specified substring.
        indexOf(int ch, int fromIndex) Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
     */

    private int num_quizzes = 0;    //Number of quizzes on file

    /*Writes given data to specified filename*/
    public void writeToFile(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(data);
        fw.close();

    }
    /*Appends given data to specified filename*/
    public void appendToFile(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename,true);
        fw.write(data);
        fw.close();

    }



    public void createQuiz(String name) throws IOException {
        FileWriter fw;
        //First quiz
        if (num_quizzes == 0 ){
            num_quizzes++;
            writeToFile("test.txt",num_quizzes +    //Head of file indicates number of quizzes
                                              "\nQuiz Name: " + name + ";" + num_quizzes +
                                              "\n.");             //'.' signifies end of quiz
        }
        else {
            num_quizzes++;
            appendToFile("test.txt","\nQuiz Name: " + name + ";" + num_quizzes +
                                                  "\n.");

        }


    }

    /* Removes a question from specified quiz */
    public void rmvQuestion(int question_num, int quiz_id) throws IOException {
    /*
        File inputFile = new File("test.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "test tset";
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);

    */
    }

    /* Adds a line of text to a file */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addline(String filename, int position, String str) throws IOException {
        /*  if position < 0, line will be appened to end of file*/
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        if(position > lines.size() || position < 0)
            lines.add(lines.size(), str);
        else
            lines.add(position, str);
        Files.write(path, lines, StandardCharsets.UTF_8);

    }

    /* Appends a question to an existing quiz*/
    public void addToQuiz(int quiz_id, String question) throws IOException {



    }
}
