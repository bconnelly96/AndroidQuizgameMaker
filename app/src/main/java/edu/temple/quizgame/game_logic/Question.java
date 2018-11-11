package edu.temple.quizgame.game_logic;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Question serves as a parent class
 * for all of the question-related logic
 * in the application.
 * Question implements the Serializable interface
 * for sending/receiving, loading/saving purposes.
 */
public class Question<T> implements Serializable {
    // Represents the question a user wishes to display
    private String question;
    // Represents the correct answer for a given question
    private T correctAnswer;
    // Each instance of Question has a list of possible answers
    private T answer;

    // public constructor initializes Question object's fields
    public Question(String question, T correctAnswer, T answer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer = answer;
    }

    // mutator methods
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public T getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(T correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public T getAnswer() {
        return answer;
    }

    // returns true if the userAnswer arg. is equal to the Question's correctAnswer var.
    public boolean isCorrectAnswer(T userAnswer) {
        return userAnswer == correctAnswer;
    }
}