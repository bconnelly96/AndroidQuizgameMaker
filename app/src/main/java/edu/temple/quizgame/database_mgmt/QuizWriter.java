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
import edu.temple.quizgame.game_logic.Question;


public class QuizWriter {
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
        8  Question #3
        9  answer
        10  other answers
        11
        Filename would be 'Quiz_Name.txt'

        A single file named quiz_index.dat will store the file name for each quiz.
        Each quiz is assigned an ID. Said ID will be the index in which the name of the .txt file
        to where that quiz's data is stored.

        0  Quiz_name.txt    //quiz name's id =1
        1  Quiz_name2.txt   //quiz name2's id =2
        2  Quiz_name3.txt   //quiz name3's id =3
    */

    private int num_quizzes = 0;    //Number of quizzes on file

    /*Writes given data to specified filename*/
    private void writeToFile(String filename, String data, int offset) throws IOException {
        FileWriter fw = new FileWriter(filename);
        fw.write(data,offset,data.length());
        fw.close();

    }
    /*Appends given data to specified filename*/
    private void appendToFile(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename,true);
        fw.write(data);
        fw.close();

    }



    public void writeQuizToFile(ArrayList<Object> questions, int id) throws IOException {

        //check to make sure questions in not null

        //Setup buffer to write
        StringBuilder data = new StringBuilder();
        Question curr;
        int fileLength = questions.size() * 3 + 2; //Num questions * (3 lines in file) + 2 (offset)
        //Loop through list to add to buffer
        for (int i = 0; i < questions.size(); i++ ){
            curr = (Question) questions.get(i);
            data.append(curr.getQuestion()).append("\n");
            data.append((String) curr.getCorrectAnswer()).append("\n");
            data.append(arrayListToString(curr.getAnswers())).append("\n");

        }
        //Write buffer to file
        writeToFile(getQuizFilename(),data.toString(),0);
        //Write quiz id to file
        writeToFile("quiz_index.dat", Integer.toString(id), id);

    }

    private String getQuizFilename() {

        //Fill in code


        return "";
    }

    private String arrayListToString(ArrayList<Object> list){

        StringBuilder sb = new StringBuilder();
        for (Object s : list) {
            sb.append(s);
            sb.append(";");
        }
        return sb.toString();
    }



    /* Adds a line of text to a file */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addline(String filename, int position, String str) throws IOException {
        /*  if position < 0, line will be appended to end of file*/
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        if(position > lines.size() || position < 0)
            lines.add(lines.size(), str);
        else
            lines.add(position, str);
        Files.write(path, lines, StandardCharsets.UTF_8);

    }

    /* Sets quiz id*/
    public void setQuizID(int id) {



    }
}
