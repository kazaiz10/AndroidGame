package pmd.di.ubi.pt.work1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pmd.di.ubi.pt.work1.database.dao.dao;
import pmd.di.ubi.pt.work1.database.database;

public class highscores extends AppCompatActivity {

    private ImageButton back;
    private database database;
    private dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        List<jogador> items=new ArrayList<>();
        ListView list=findViewById(R.id.highscorlist);
        adapterscores adapter=new adapterscores(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);
        database= Room.databaseBuilder(getApplicationContext(),database.class,"db").allowMainThreadQueries().build();
        dao=database.getdao();
        List<jogador> lista=dao.getall();
        for(jogador j:lista){
            adapter.add(j);
        }


        back=findViewById(R.id.buttonback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(highscores.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }


}