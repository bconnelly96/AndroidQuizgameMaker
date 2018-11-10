package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

/*
 * A Question object that represents a true false question.
 * Invokes the parent class' constructor with the String correctAnswer
 * in place of the generic type found in the parent class
 */
public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String question, String correctAnswer, ArrayList answers) {
        super(question, correctAnswer, answers);
    }
}