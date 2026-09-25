package pmd.di.ubi.pt.work1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class jogador implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private int pontos;
    private int tempo;

    public jogador() {
        this.id = 0;
        this.name = "";
        this.pontos = 0;
        this.tempo = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPontos() {
        return pontos;
    }

    public int getTempo() {
        return tempo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    protected jogador(Parcel in) {
        id = in.readInt();
        pontos = in.readInt();
        tempo = in.readInt();
        name = in.readString();
    }

    public static final Creator<jogador> CREATOR = new Creator<jogador>() {
        @Override
        public jogador createFromParcel(Parcel in) {
            return new jogador(in);
        }

        @Override
        public jogador[] newArray(int size) {
            return new jogador[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(pontos);
        parcel.writeInt(tempo);
        parcel.writeString(name);
    }

}
