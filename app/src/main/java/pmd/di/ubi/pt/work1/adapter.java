package pmd.di.ubi.pt.work1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import pmd.di.ubi.pt.work1.jogador;

public class adapter extends ArrayAdapter<jogador> {
    private Context context;
    private int resource;
    private List<jogador> lista=new ArrayList<>();
    private ArrayList<jogador> selectlista=new ArrayList<>();

    public adapter(@NonNull Context context, int resource, @NonNull List<jogador> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        jogador jogador=lista.get(position);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.layoutjogador,null) ;
        TextView textView=view.findViewById(R.id.jogadorview) ;
        textView.setText(jogador.getName().toString());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectlista.contains(jogador)){
                    textView.setBackground(ContextCompat.getDrawable(context,R.drawable.selectfalse));
                    selectlista.remove(jogador);
                }else {
                    if(selectlista.size()<=3){
                        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.select));
                        selectlista.add(jogador);
                    }

                }

            }
        });
       return view;
    }

    public ArrayList<jogador> getSelectlista() {
        return selectlista;
    }
}
