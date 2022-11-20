package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MemoryGameActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {
    HashMap<ImageButton, Integer> imageRes = new HashMap<ImageButton, Integer>();
    ArrayList<Integer> drawableEqual = new ArrayList<>();
    ArrayList<ImageButton> equals = new ArrayList<>();
    ArrayList<ImageButton> notCorrectButtons = new ArrayList<>();
    int[] myImageList = {R.drawable.placeholder2, R.drawable.placeholder3, R.drawable.placeholder5, R.drawable.placeholder4, R.drawable.placeholder6, R.drawable.placeholder7, R.drawable.placeholder8, R.drawable.placeholder9};
    ImageButton[][] buttons = new ImageButton[4][4];

    Button btnStart;
    GridLayout gl;
    TextView tvClicks;
    ImageButton ibNew,b;
    int visibleButtons = 0, id = 1;
    int index=0;
    int doubles = 8;
    int finish=0;

    Animation animationFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        gl = findViewById(R.id.gl);
        tvClicks = findViewById(R.id.tvClicks);
        animationFadeOut= AnimationUtils.loadAnimation(MemoryGameActivity.this, R.anim.fade_out_memory_game);
        animationFadeOut.setAnimationListener(this);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        createBoard();



    }

    @Override
    public void onClick(View view) {
        if (view == btnStart) {
            btnStart.setVisibility(View.INVISIBLE);
            gl.setVisibility(View.VISIBLE);
            finish=0;
            tvClicks.setText("");
            randomise();
            notCorrectButtons.clear();
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    notCorrectButtons.add(buttons[i][j]);
                    buttons[i][j].setClickable(true);
                }
            }
        }
        else{
            b = (ImageButton) view;
            int drawable = (Integer) b.getTag();

            b.setImageResource(imageRes.get(b));

            if (equals.size() < 2) {
                equals.add(b);
                b.setClickable(false);
                drawableEqual.add(drawable);
            }
            if (equals.size() == 2) {
                if (drawableEqual.get(0).equals(drawableEqual.get(1))) {
                    for (int i = 0; i < equals.size(); i++) {
                        equals.get(i).setClickable(false);
                    }
                    for (int i = 0; i < equals.size(); i++) {
                        if (notCorrectButtons.contains(equals.get(i))) {
                            notCorrectButtons.remove(equals.get(i));
                        }
                    }
                    finish++;
                    drawableEqual.clear();
                    equals.clear();
                }
                else {
                    equals.get(0).startAnimation(animationFadeOut);
                    equals.get(1).startAnimation(animationFadeOut);
                    for (int i = 0; i < notCorrectButtons.size(); i++) {
                        notCorrectButtons.get(i).setClickable(false);
                    }

                    for (int i = 0; i < equals.size(); i++) {
                        equals.get(i).setClickable(true);
                    }
                }

            }

            if (finish == doubles) {
                tvClicks.setText("gg");
                btnStart.setVisibility(View.VISIBLE);
            }
        }
    }

    public void createBoard() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                LinearLayout.LayoutParams LL1 = new LinearLayout.LayoutParams(200, 200);
                LL1.setMargins(10, 10, 10, 10);
                ibNew = new ImageButton(this);
                ibNew.setLayoutParams(LL1);
                ibNew.setId(id);
                ibNew.setOnClickListener(this);
                ibNew.setVisibility(View.INVISIBLE);
                buttons[i][j] = ibNew;
                notCorrectButtons.add(ibNew);
                gl.addView(ibNew);
                id++;
                index++;
            }
        }
        gl.setVisibility(View.INVISIBLE);
    }
    public void randomise() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setVisibility(View.INVISIBLE);
            }
        }

        for (int i = 0; i < doubles; i++) {
            visibleButtons = 0;
            while (visibleButtons < 2) {
                Random random = new Random();
                int max = 4;
                int rnd1 = random.nextInt(max);
                int rnd2 = random.nextInt(max);
                if (buttons[rnd1][rnd2].getVisibility() != View.VISIBLE) {
                    buttons[rnd1][rnd2].setVisibility(View.VISIBLE);
                    buttons[rnd1][rnd2].setImageResource(myImageList[i]);
                    buttons[rnd1][rnd2].setTag(myImageList[i]);
                    imageRes.put(buttons[rnd1][rnd2], myImageList[i]);
                    visibleButtons++;
                }
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setImageResource(R.drawable.placeholder1);
            }
        }
    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation==animationFadeOut&&equals.size()>0) {
            for (int i = 0; i < equals.size(); i++) {
                equals.get(i).setImageResource(R.drawable.placeholder1);
            }
            drawableEqual.clear();
            equals.clear();
            for (int i = 0; i < notCorrectButtons.size(); i++) {
                notCorrectButtons.get(i).setClickable(true);
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}