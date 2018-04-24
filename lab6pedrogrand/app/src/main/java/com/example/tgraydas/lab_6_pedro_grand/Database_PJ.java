package com.example.tgraydas.lab_6_pedro_grand; /**
 * Created by tgraydas on 03-04-18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database (entities = {Formulario_DB.class, User.class, Question.class,Answer.class}, version = 2, exportSchema = false)
    public abstract class Database_PJ extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
