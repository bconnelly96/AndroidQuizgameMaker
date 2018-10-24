package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

public class QuizSession {
    /*keeps track of the number of questions in a quiz*/
    int numQuestions;
    /*holds the quiz questions*/
    ArrayList<Object> quizQuestions;
    /*Gives quiz a name for display to user*/
    String quizName;
    /*Each quiz has a unique ID for storage and identification purposes*/
    int quizID;

    /*public constructor sets up a QuizSession object*/
    public QuizSession() {

    }

    /*adds a question to the QuizSession's ArrayList of questions*/
    public void addQuestion(Question newQuestion) {

    }
}
