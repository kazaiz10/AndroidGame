package pmd.di.ubi.pt.work1.database;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pmd.di.ubi.pt.work1.R;
import pmd.di.ubi.pt.work1.database.dao.dao;
import pmd.di.ubi.pt.work1.jogador;

@Database(entities = {jogador.class},version = 1,exportSchema = false)
public abstract class database extends RoomDatabase {
    public abstract dao getdao();
}
