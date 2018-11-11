package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

/*
* A Question object that represents a true false question.
* Invokes the parent class' constructor with the boolean correctAnswer
* in place of the generic type found in the parent class
*/
public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String question, boolean correctAnswer, ArrayList answers) {
        super(question, correctAnswer, answers);
    }
}