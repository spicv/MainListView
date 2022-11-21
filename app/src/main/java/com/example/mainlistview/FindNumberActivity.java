package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FindNumberActivity extends AppCompatActivity implements View.OnClickListener {
    GridLayout gl;
    TextView tvNumber,tvTime;
    Button btnStart;
    ArrayList<String> numbers=new ArrayList<>();
    HashMap<String,Integer> getNumber=new HashMap<>();
    Button[][]buttons=new Button[3][3];
    int value=1;
    String str;
    int btnNumber,extraTurns=0;
    long startTime,difference,counter;
    double avg;
    RunIt2 playAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_number);
        gl=findViewById(R.id.gl);
        tvNumber=findViewById(R.id.tvNumber);
        tvTime=findViewById(R.id.tvTime);
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        playAnim = new RunIt2();

        numbers.add("one");
        numbers.add("two");
        numbers.add("three");
        numbers.add("four");
        numbers.add("five");
        numbers.add("six");
        numbers.add("seven");
        numbers.add("eight");
        numbers.add("nine");

        for (int i = 0; i < numbers.size(); i++) {
            getNumber.put(numbers.get(i),value);
            value++;
        }
        createBoard();
    }

    public class RunIt2 implements Runnable{

        public RunIt2() {
        }

        @Override
        public void run() {
            gl.setBackgroundColor(Color.RED);
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            gl.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        if (!buttonText.equalsIgnoreCase("start game")) {
            btnNumber = Integer.parseInt(buttonText);
        }
            if (buttonText.equalsIgnoreCase("start game")) {
                restartGame();
            }
            else if (getNumber.get(str) == btnNumber && !buttonText.equalsIgnoreCase("start game")) {
                if (extraTurns < 2) {
                    difference = System.currentTimeMillis() - startTime;
                    counter += difference;
                    randomise();
                    startTime = System.currentTimeMillis();
                    extraTurns++;
                }
                else {
                    stopGame();
                }
            }
            else if (getNumber.get(str) != btnNumber && !buttonText.equalsIgnoreCase("start game")) {
                new Thread(playAnim).start();
                //stopGame();
                    //tvTime.setText("Wrong button, try again");
            }
    }


        public void restartGame() {
            startTime = System.currentTimeMillis();
            randomise();
            gl.setVisibility(View.VISIBLE);
            tvNumber.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.INVISIBLE);
            tvTime.setText("");
        }

        public void stopGame(){
        difference = System.currentTimeMillis() - startTime;
        counter += difference;
        gl.setVisibility(View.INVISIBLE);
        tvNumber.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        extraTurns=0;
        avg=(double) (counter/3)/1000;
        tvTime.setText("Average time: "+avg+" seconds");
        avg=0;
        counter=0;
    }

    public void createBoard(){

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                LinearLayout.LayoutParams LL1=new LinearLayout.LayoutParams(330,330);
               // LL1.setMargins(10,10,10,10);
                Button btn=new Button(this);
                btn.setLayoutParams(LL1);
                btn.setTextSize(50);
                btn.setVisibility(View.INVISIBLE);
                btn.setTextColor(Color.parseColor("#FFFFFF"));
                btn.setBackground(getDrawable(R.drawable.back_image));
                btn.setOnClickListener(this);
                buttons[i][j]=btn;
                gl.addView(btn);
            }
        }
    }
    public void randomise(){
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setVisibility(View.INVISIBLE);
            }
        }
        int visibleButtons=0;
        int index=1;
        while (visibleButtons<9){
            Random random = new Random();
            int max=3;
            int rnd1=random.nextInt(max);
            int rnd2=random.nextInt(max);
            if (buttons[rnd1][rnd2].getVisibility() != View.VISIBLE) {
                buttons[rnd1][rnd2].setVisibility(View.VISIBLE);
                buttons[rnd1][rnd2].setText(index+"");
                index++;
                visibleButtons++;
            }
        }
        Random random2 = new Random();
        int max2=9;
        int rnd=random2.nextInt(max2);
        str=numbers.get(rnd);
        tvNumber.setText(str.toUpperCase());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}