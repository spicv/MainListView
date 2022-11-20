package com.example.mainlistview;

import java.util.ArrayList;

public class TimedGame extends Game {

    ArrayList<Double> timeAvg;

    public TimedGame(String title, String subTitle,ArrayList<Double> timeAvg) {
        super(title, subTitle);
        this.timeAvg=timeAvg;
    }
}
