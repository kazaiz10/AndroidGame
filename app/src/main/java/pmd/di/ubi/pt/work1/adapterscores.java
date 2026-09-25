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

public class adapterscores extends ArrayAdapter<jogador> {
    private Context context;
    private int resource;
    private List<jogador> lista=new ArrayList<>();

    public adapterscores(@NonNull Context context, int resource, @NonNull List<jogador> objects) {
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
        View view= inflater.inflate(R.layout.layouthigh,null) ;
        TextView textView1=view.findViewById(R.id.high1) ;
        TextView textView2=view.findViewById(R.id.high2) ;
        TextView textView3=view.findViewById(R.id.high3) ;
        textView1.setText(jogador.getName().toString());
        textView2.setText(String.valueOf(jogador.getPontos()));
        textView3.setText(String.valueOf(jogador.getTempo()));

        return view;
    }

}
