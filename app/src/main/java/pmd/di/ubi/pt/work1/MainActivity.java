package pmd.di.ubi.pt.work1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pmd.di.ubi.pt.work1.database.dao.dao;
import pmd.di.ubi.pt.work1.database.database;

public class MainActivity extends AppCompatActivity {
    private ImageButton button;
    private database database;
    private dao dao;
    private adapter adapter;
    private ImageButton scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database= Room.databaseBuilder(getApplicationContext(),database.class,"db").allowMainThreadQueries().build();
        dao=database.getdao();
        Dialog dialog = new Dialog(this);

        jogador jogador=new jogador();
        jogador.setName("joao");
        jogador.setPontos(10);
        jogador.setTempo(5);
        //dao.Insert(jogador);
        //List<jogador> lista=new ArrayList<>();
        //lista=dao.getall();


        button= findViewById(R.id.buttonplay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.popupadd);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ListView listView=dialog.findViewById(R.id.playerlist);

                ImageButton add=dialog.findViewById(R.id.plusbutton);
                ImageButton iniciar=dialog.findViewById(R.id.play2);
                EditText name=dialog.findViewById(R.id.editTextTextPersonName);

                List<jogador> listaplayer=new ArrayList<>();
                adapter=new adapter(dialog.getContext(), android.R.layout.simple_list_item_1,listaplayer);
                listView.setAdapter(adapter);
                showplayers();
                dialog.show();

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogador k=new jogador();
                        k.setName(name.getText().toString());
                        dao.Insert(k);
                        showplayers();
                    }
                });

                iniciar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<jogador> test=adapter.getSelectlista();
                        for(jogador j:test){
                            j.setPontos(0);
                        }
                        Intent intent1= new Intent(MainActivity.this,Activity2.class);
                        intent1.putExtra("tagzinha", (Serializable) test);
                        intent1.putExtra("tagzona","0");
                        List<jogador> losers = new ArrayList<>();
                        intent1.putExtra("hugetag",(Serializable) losers);
                        System.out.println(adapter.getSelectlista().size());
                        startActivity(intent1);
                    }
                });


            }
        });


        scores=findViewById(R.id.buttonscores);

        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(MainActivity.this,highscores.class);
                startActivity(intent2);
            }
        });


    }

    private void showplayers(){
        adapter.clear();
        List<jogador> lista=dao.getall();
        for(jogador j:lista){
            adapter.add(j);
        }



    }


}