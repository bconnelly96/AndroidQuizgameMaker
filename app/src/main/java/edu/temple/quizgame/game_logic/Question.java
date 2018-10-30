package edu.temple.quizgame.game_logic;


import java.util.ArrayList;

public class Question<T> {
    /*Each instance of Question should have a String representing a question*/
    private String question;
    /*Each instance of Question should have an associated correct answer*/
    private T correctAnswer;
    /*Each instance of Question should have an ArrayList of user-created, potential answers*/
    private ArrayList<T> answers;

    public Question(String question, T correctAnswer, ArrayList answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = new ArrayList();
        this.answers = answers;
    }

    /*mutator methods*/
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
    public ArrayList getAnswers() {
        return answers;
    }

    /*returns true if the userAnswer arg. is equal to the Question's correctAnswer var.*/
    public boolean isCorrectAnswer(T userAnswer) {
        return userAnswer == correctAnswer;
    }
}

