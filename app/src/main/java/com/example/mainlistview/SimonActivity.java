package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimonActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    RunIt playAnim;
    Animation animationFadeOut;
    GridLayout gl;
    Button btnStart;
//    SoundPool soundPool;
    Button[][]buttons=new Button[3][3];
    ArrayList<Button> buttonOrder = new ArrayList<>();
    int[] colors={Color.parseColor("#e3342f"),Color.parseColor("#f6993f"),Color.parseColor("#ffed4a"),Color.parseColor("#38c172"),Color.parseColor("#4dc0b5"),Color.parseColor("#3490dc"),Color.parseColor("#6574cd"),Color.parseColor("#9561e2"),Color.parseColor("#f66d9b"),};
//    HashMap<Button,Integer>btnSounds;

    //int b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int order=0;
    int colorIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

//        AudioAttributes aa = new AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .build();
//        soundPool = new SoundPool.Builder()
//                .setMaxStreams(10)
//                .setAudioAttributes(aa)
//                .build();
//        b1 = soundPool.load(this, R.raw.c3, 1);
//        b2 = soundPool.load(this, R.raw.doo, 1);
//        b3 = soundPool.load(this, R.raw.fa, 1);
//        b4 = soundPool.load(this, R.raw.g6, 1);
//        b5 = soundPool.load(this, R.raw.la, 1);
//        b6 = soundPool.load(this, R.raw.mi, 1);
//        b7 = soundPool.load(this, R.raw.re, 1);
//        b8 = soundPool.load(this, R.raw.si, 1);
//        b9 = soundPool.load(this, R.raw.sol, 1);
//        int[]sounds={b1,b2,b3,b4,b5,b6,b7,b8,b9};

        gl=findViewById(R.id.gl);
        playAnim = new RunIt();
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        animationFadeOut= AnimationUtils.loadAnimation(SimonActivity.this, R.anim.fade_out_simon);
        animationFadeOut.setAnimationListener(this);
        createBoard();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
 //               btnSounds.put(buttons[i][j],sounds[i]);
            }
        }


    }

    public class RunIt implements Runnable{

        public RunIt() {
        }

        @Override
        public void run() {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setClickable(false);
                }
            }
            for (int i = 0; i < buttonOrder.size(); i++) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttonOrder.get(i).startAnimation(animationFadeOut);
            }
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setClickable(true);
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
            new Thread(playAnim).start();
        }
        if (!buttonText.equalsIgnoreCase("start game")&&b.equals(buttonOrder.get(order))){
//            soundPool.play(btnSounds.get(0), 1, 1, 0, 0, 1);
            order++;
            if (order==buttonOrder.size()){
                addRandomBtn();
                new Thread(playAnim).start();
            }
        }
        else if (!buttonText.equalsIgnoreCase("start game")&&!b.equals(buttonOrder.get(order))){
            gl.setVisibility(View.INVISIBLE);
            btnStart.setVisibility(View.VISIBLE);
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
                LL1.setMargins(15,15,15,15);
                Button btn=new Button(this);
                btn.setLayoutParams(LL1);
                btn.setTextSize(40);
                btn.setOnClickListener(this);
                btn.setBackgroundColor(colors[colorIndex]);
                buttons[i][j]=btn;
                gl.addView(btn);
                gl.setVisibility(View.INVISIBLE);
                colorIndex++;
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