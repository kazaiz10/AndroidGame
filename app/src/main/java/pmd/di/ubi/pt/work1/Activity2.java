package pmd.di.ubi.pt.work1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaParser;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pmd.di.ubi.pt.work1.database.dao.dao;
import pmd.di.ubi.pt.work1.database.database;


public class Activity2 extends AppCompatActivity {

    EditText TextNumber;
    TextView text1;
    private static final String pi="141592653589793238462643383279502884197169399375105820974944592307816406286";
    private Dialog dialog;
    private long data1,data2;
    private  char array[]=pi.toCharArray();
    private int index=0;
    private database database;
    private dao dao;
    private TextView playername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        dialog=new Dialog(this);

        Date currentTime1 = Calendar.getInstance().getTime();
        data1=currentTime1.getTime();

        TextNumber= (EditText) findViewById(R.id.TextNumber);
        text1 = (TextView) findViewById(R.id.question);


        ArrayList<jogador> lista=getIntent().getParcelableArrayListExtra("tagzinha");
        ArrayList<jogador> losers=getIntent().getParcelableArrayListExtra("hugetag");
        index = Integer.parseInt(getIntent().getStringExtra("tagzona"));


        playername=findViewById(R.id.playername);
        playername.setText(lista.get(index).getName());

        variosplayer(lista,losers);

    }

    public void variosplayer(ArrayList<jogador> partipantes, ArrayList<jogador> losers){

        TextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String number = (new String(TextNumber.getText().toString()));
                char array2[]= number.toCharArray();
                int aux=array2.length - 1;


                if (array2.length>0 && array[aux]==array2[aux]){
                    partipantes.get(index).setPontos((partipantes.get(index).getPontos()+1));

                }else{
                    Date currentTime2 = Calendar.getInstance().getTime();
                    data2=currentTime2.getTime();
                    partipantes.get(index).setTempo((int)(data2-data1)/1000);
                    index++;
                    dialog.setContentView(R.layout.popup);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                    database= Room.databaseBuilder(getApplicationContext(),database.class,"db").allowMainThreadQueries().build();
                    dao=database.getdao();
                    if(dao.getscore(partipantes.get(index-1).getId())<partipantes.get(index-1).getPontos()){
                        dao.Update(partipantes.get(index-1));
                    }


                    Handler timedelay= new Handler();
                    timedelay.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            losers.add(partipantes.get(index-1));

                            dialog.dismiss();
                            if(losers.size()==partipantes.size()){

                                Intent intent1= new Intent(Activity2.this,MainActivity.class);
                                startActivity(intent1);
                            }else{


                                Intent intent3=new Intent(Activity2.this,Activity2.class);
                                intent3.putExtra("tagzinha",partipantes);
                                intent3.putExtra("tagzona",String.valueOf(index));
                                intent3.putExtra("hugetag",losers);
                                startActivity(intent3);
                            }

                        }
                    },1500);

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

}