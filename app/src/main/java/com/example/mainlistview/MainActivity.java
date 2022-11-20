package com.example.mainlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvGames;
    ArrayList<Game> gamesList;
    GameAdapter gameAdapter;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createArrayList();
        //phase 3 - create adapter
        gameAdapter=new GameAdapter(this,0,0, gamesList);
        //phase 4 reference to listview
        lvGames=findViewById(R.id.lvGames);
        lvGames.setAdapter(gameAdapter);
        lvGames.setOnItemClickListener(this);
    }



    protected void createArrayList()
    {
        //create the objects
        Game game1 = new Game("Memory Game","Category: Memory");
        Game game2 = new Game("Find Number","Category: Speed");
        Game game3 = new Game("Chimp Test","Category: Speed");
        Game game4 = new Game("Simon","Category: Memory");


        //phase 2 - add to array list
        gamesList = new ArrayList<>();
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        gamesList.add(game4);




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i==0){
            it=new Intent(this,MemoryGameActivity.class);
            startActivity(it);
        }
        if (i==1){
            it=new Intent(this,FindNumberActivity.class);
            startActivity(it);
        }
        if (i==2){
            it=new Intent(this,ChimpTestActivity.class);
            startActivity(it);
        }
        if (i==3){
            it=new Intent(this,SimonActivity.class);
            startActivity(it);
        }
    }
}