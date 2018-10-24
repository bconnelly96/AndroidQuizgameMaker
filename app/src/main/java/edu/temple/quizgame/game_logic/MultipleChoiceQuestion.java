package edu.temple.quizgame.game_logic;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {

    /*M-C-Q contructor invokes parent's constructor*/
    public MultipleChoiceQuestion(String question, String correctAnswer, ArrayList answers) {
        super(question, correctAnswer, answers);
    }
}