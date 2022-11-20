package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class SimonActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    GridLayout gl;
    Button btnStart;
    Button[][]buttons=new Button[3][3];
    ArrayList<Button> buttonOrder = new ArrayList<>();
    RunIt test;

    Animation animationFadeOut;

    int index=0;
    int order=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        gl=findViewById(R.id.gl);
        test = new RunIt();
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        animationFadeOut= AnimationUtils.loadAnimation(SimonActivity.this, R.anim.fade_out_simon);
        animationFadeOut.setAnimationListener(this);
        createBoard();

    }

    public class RunIt implements Runnable{

        public RunIt() {
        }

        @Override
        public void run() {
            for (int i = 0; i < buttonOrder.size(); i++) {
                buttonOrder.get(i).startAnimation(animationFadeOut);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        if (buttonText.equalsIgnoreCase("start game")) {
            btnStart.setVisibility(View.INVISIBLE);
            gl.setVisibility(View.VISIBLE);
            addRandomBtn();
            new Thread(test).start();
        }
        if (!buttonText.equalsIgnoreCase("start game")&&b.equals(buttonOrder.get(order))){
            order++;
            if (order==buttonOrder.size()){
                addRandomBtn();
                new Thread(test).start();
            }
        }
        else if (!buttonText.equalsIgnoreCase("start game")&&!b.equals(buttonOrder.get(order))){
            gl.setVisibility(View.INVISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            index=0;
            buttonOrder.clear();
        }

    }

    public void addRandomBtn(){
        order=0;
        Random random = new Random();
        int max = 3;
        int rnd1 = random.nextInt(max);
        int rnd2 = random.nextInt(max);
        buttonOrder.add(buttons[rnd1][rnd2]);
    }

    public void createBoard(){

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                LinearLayout.LayoutParams LL1=new LinearLayout.LayoutParams(330,330);
                LL1.setMargins(10,10,10,10);
                Button btn=new Button(this);
                btn.setLayoutParams(LL1);
                btn.setTextSize(40);
                btn.setOnClickListener(this);
                buttons[i][j]=btn;
                gl.addView(btn);
                gl.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}