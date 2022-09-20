package com.example.bkap.android.marketonline.ROOMDATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bkap.android.marketonline.DAO.MyCartDAO;
import com.example.bkap.android.marketonline.DTO.MyCart;


@Database(entities = {MyCart.class},version = 1)
public abstract class MyCartDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "myCart.db";
    private static MyCartDatabase instance;

    public static synchronized MyCartDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),MyCartDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract MyCartDAO myCartDAO();
}
