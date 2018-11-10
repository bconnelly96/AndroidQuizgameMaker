package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

/*
 *QuizSession objects tie together
 *the logic components of a quiz.
 *They are the objects that are to be saved
 *in the public directory of a user's phone
 */
public class QuizSession {
    // keeps track of the number of questions in a quiz
    public int numQuestions;
    // holds references to Question objects
    public ArrayList<Question> quizQuestions;
    // holds quiz's name for display and retrieval purposes
    String quizName;

    // public constructor initializes fields for a given QuizSession object
    public QuizSession() {
        numQuestions = 0;
        quizQuestions = new ArrayList<Question>();
        quizName = "";
    }

    // adds a Question object to the QuizSession's ArrayList
    public void addQuestion(Question newQuestion) {
        quizQuestions.add(newQuestion);
    }

    public void incrementNumQuestions() {
        numQuestions++;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    //Added methods by Joshee
    public Question getQuestion(int i) {
        return quizQuestions.get(i);
    }

    public String getQuizName() {
        return quizName;
    }

    public int getNumQuestions() {
        return numQuestions;
    }

}
