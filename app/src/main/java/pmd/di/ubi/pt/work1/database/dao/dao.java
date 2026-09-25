package pmd.di.ubi.pt.work1.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pmd.di.ubi.pt.work1.jogador;

@Dao
public interface dao {
    @Insert
    void Insert(jogador jogador);
    @Delete
    void Remove(jogador jogador);
    @Update
    void Update(jogador jogador);
    @Query("SELECT * FROM jogador")
    List<jogador> getall();
    @Query("SELECT name FROM jogador")
    List<String> getnames();
    @Query("SELECT pontos From jogador WHERE :codigo=id")
    int getscore(int codigo);
}
