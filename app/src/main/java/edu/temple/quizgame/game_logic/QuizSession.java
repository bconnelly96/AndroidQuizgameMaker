package edu.temple.quizgame.game_logic;

import java.util.ArrayList;
import java.util.Random;

/*
*QuizSession objects tie together
*the logic components of a quiz.
*They are the objects that are to be saved
*in the public directory of a user's phone
*/
public class QuizSession {
    /*keeps track of the number of questions in a quiz*/
    int numQuestions;
    /*holds the quiz questions;
    *more specifically, holds the question visuals, which contain references
    *to Question objects*/
    ArrayList<Question> quizQuestions;
    /*Gives quiz a name for display to user*/
    String quizName;
    /*Each quiz has a unique ID for storage purposes*/
    long quizID;

    /*public constructor sets up a QuizSession object*/
    public QuizSession() {
        numQuestions = 0;
        quizQuestions = new ArrayList<Question>();
        quizName = "";
        quizID = getID();
    }

    /*adds a question to the QuizSession's ArrayList of questions*/
    public void addQuestion(Question newQuestion) {
        quizQuestions.add(newQuestion);
    }

    public void incrementNumQuestions() {
        numQuestions++;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    //TODO: Add check and associated variables to ensure each ID is unique
    /*returns a long representing a quiz's unique ID*/
    private long getID() {
        Random r = new Random();
        return r.nextLong();
    }
}
