package edu.temple.quizgame;

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


    public void read_file() throws IOException {

        File file = new File("test.txt");
        BufferedReader buffer = new BufferedReader(new java.io.FileReader(file));

        String str;
        while ((str = buffer.readLine()) != null)
            System.out.println(str);
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
   // @Test
    public void addline() throws IOException {

        Path path = Paths.get("test.txt");
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.add(lines.size(), "Quiz name: test.txt 3");
        Files.write(path, lines, StandardCharsets.UTF_8);
        read_file();
    }

    @Test
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
        read_file();

    }

}
