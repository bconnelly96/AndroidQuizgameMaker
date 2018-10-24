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
import java.util.List;

public class IO_Testing {


    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<String> read_file(String filename) throws IOException {

        Path path = Paths.get(filename);
        return Files.readAllLines(path, StandardCharsets.UTF_8);

    }
/*
    @Test
    public void createQuiz() throws IOException {

        System.out.println("\ncreateQuiz test.....");

        int num_quizzes = 0;
        FileWriter fw;
        //First quiz
        if (num_quizzes == 0){
            fw = new FileWriter("test.txt");
            fw.write("Quiz name: test.txt\n");
            fw.write("Testing createQuiz method.");
            fw.close();
        }
        read_file();
    }
*/
    @Test
    public void read_file_test() throws IOException {

        List<String> test = read_file("test.txt");
        for (int i = 0; i < test.size(); i++){
            System.out.println(test.get(i));
        }

    }

   // @Test
    public void rmvLine() throws IOException {
        /*
            Instead, figure out algorithm to find specific questions
            at it's index.
            Note: Since each heading is the same, then question #n's
            index should be at the head of the quiz (Quiz name/id).index + 2n
            *Maybe save head of quiz index in metadata
         */

        File inputFile = new File("test.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = "Quiz name: test.txt 3";
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

    }

}
