package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

public class TrueFalseQuestion extends Question {

    /*T-F contructor invokes parent's constructor*/
    public TrueFalseQuestion(String question, boolean correctAnswer, ArrayList answers) {
        super(question, correctAnswer, answers);
    }
}