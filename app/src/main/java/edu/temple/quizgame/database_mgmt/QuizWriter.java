package edu.temple.quizgame.database_mgmt;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.RandomAccessFile;

import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;


public class QuizWriter {
    /*NOTE* Since one quiz can be worked on at time, quizzes should be loaded into some data structure
           for data manipulation. SOLVED see QuizSession.java
           QuizReader.getQuiz() should be the only method that can retrieve a quiz from the quiz data file.
           Once a quiz is loaded, our combined methods will be able to add/change/delete data with ease.

        Updated File format:
        Each quiz gets its own .dat file.

        0  Quiz name (String)
        1  Number of questions (int)
        2  Question #1 (multiple choice)
        3  Correct answer
        4  Other answers (separated by semicolons)
        5  Question #2 (true/false)
        6  true
        7  false
        8  Question #3
        9  answer
        10  other answers
        11

        Filename would be 'quiz_id.dat'

    */

    /*  Creates a file of specified size
        Returns 1 if file was created successfully, -1 otherwise */
    public static int createFile(String filename, int size){

        try {
            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            file.setLength(size);
            if (file.length() > 0){//File was allocated
                file.close();
                return 1;
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
        return -1;

    }

    /*Writes given data to specified filename*/
    private static void writeToFile(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(data);
        fw.close();

    }

    /*Writes given quiz to its appropriate file */
    public static void writeQuizToFile(QuizSession quiz, int num_questions, long id) throws IOException {

        //check to make sure quiz is not null
        if (quiz == null){
            throw new NullPointerException("Quiz has no reference");
        }
        //Setup buffer to write
        StringBuilder data = new StringBuilder();
        //Append file header to string
        data.append(setFileHeader(quiz.getQuizName(),num_questions)).append("\n");
        //Loop through quiz to add questions to buffer
        Question curr;
        for (int i = 0; i < num_questions; i++ ){
            curr = quiz.getQuestion(i);
            data.append(curr.getQuestion()).append("\n");
            data.append(curr.getCorrectAnswer().toString()).append("\n");
            data.append(curr.getAnswers().toString()).append("\n");

        }
        //Write buffer to file
        writeToFile(id + ".dat",data.toString());

    }

    private static String setFileHeader(String quiz_name, int num_questions) {

        StringBuilder header = new StringBuilder();
        header.append(quiz_name).append("\n");
        header.append(num_questions);


        return header.toString();
    }

    /* Replaces a line (position) in a file with given str */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void replaceLine(String filename, int position, String str) throws IOException {

        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(position);
        lines.add(position,str);
        Files.write(path, lines, StandardCharsets.UTF_8);

    }

    /* Adds a line of text to a file at position */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void addLine(String filename, int position, String str) throws IOException {
        /*  if position < 0, line will be appended to end of file*/
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        if(position > lines.size() || position < 0){
            lines.add(lines.size(), str);
        }
        else{
            lines.add(position, str);
        }
        Files.write(path, lines, StandardCharsets.UTF_8);

    }
}
