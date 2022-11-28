package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class ChimpTestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvRestart, tvNumber;
    Button btnStart;
    Button[][] buttons = new Button[4][4];
    GridLayout gl;
    int btnOrder = 1;
    int visibleCounter = 3;
    boolean clickedFirst = false;
    int visibleButtons = 0;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chimp_test);
        tvNumber = findViewById(R.id.tvNumber);
        tvRestart = findViewById(R.id.tvRestart);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        gl = findViewById(R.id.gl);

        createBoard();

    }


    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        if (buttonText.equalsIgnoreCase("start game")) {
            startGame();
        }
        if (visibleCounter > 3) {
            if (buttonText.equals("1")) {
                clickedFirst = true;
                hideText();
            }
        }
        if (buttonText.equals(btnOrder + "")) {
            b.setVisibility(View.INVISIBLE);
            btnOrder++;
            if (btnOrder > 16) {
                stopGame();
                tvRestart.setText("You finished the game, congrats");
            }
            if (btnOrder == visibleCounter + 1 && btnOrder <= 16) {
                restartRound();
            }
        } else if (!buttonText.equalsIgnoreCase("start game")) {
            restartGame();
        }

    }

    public void restartGame() {
        stopGame();
        tvNumber.setText("You got to " + visibleCounter + " numbers");
        tvRestart.setText("You clicked on the wrong button");
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void startGame() {
        tvRestart.setText("");
        tvNumber.setText("");
        btnOrder = 1;
        visibleCounter = 3;
        gl.setVisibility(View.VISIBLE);
        randomise(visibleCounter);
        btnStart.setVisibility(View.INVISIBLE);
    }

    public void hideText() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getVisibility() == View.VISIBLE) {
                    buttons[i][j].setTextColor(Color.parseColor("#000000"));
                }
            }
        }
    }

    public void restartRound() {
        btnOrder = 1;
        clickedFirst = false;
        visibleCounter++;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        randomise(visibleCounter);
    }

    public void stopGame() {
        btnStart.setVisibility(View.VISIBLE);
        gl.setVisibility(View.INVISIBLE);
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    public void createBoard() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                LinearLayout.LayoutParams LL1 = new LinearLayout.LayoutParams(250, 250);
                LL1.setMargins(10,10,10,10);
                Button btn = new Button(this);
                btn.setLayoutParams(LL1);
                btn.setTextSize(40);
                btn.setVisibility(View.INVISIBLE);
                btn.setOnClickListener(this);
                btn.setTextColor(Color.parseColor("#FFFFFF"));
                btn.setBackground(getDrawable(R.drawable.back_image));
                buttons[i][j] = btn;
                gl.addView(btn);
            }
        }
    }

    public void randomise(int visibleCounter) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setVisibility(View.INVISIBLE);
            }
        }
        visibleButtons = 0;
        index = 1;
        while (visibleButtons < visibleCounter) {
            Random random = new Random();
            int max = 4;
            int rnd1 = random.nextInt(max);
            int rnd2 = random.nextInt(max);
            if (buttons[rnd1][rnd2].getVisibility() != View.VISIBLE) {
                buttons[rnd1][rnd2].setVisibility(View.VISIBLE);
                buttons[rnd1][rnd2].setText(index + "");
                visibleButtons++;
                index++;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}