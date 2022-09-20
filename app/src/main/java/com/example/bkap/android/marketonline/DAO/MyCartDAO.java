package com.example.bkap.android.marketonline.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bkap.android.marketonline.DTO.MyCart;

import java.util.List;

@Dao
public interface MyCartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMyCart(MyCart myCart);

    @Query("SELECT * FROM myCart")
    List<MyCart> getListMyCart();

    @Query("SELECT quantity FROM myCart WHERE idP = :productId")
    int getNumber (int productId);

    @Query("SELECT * FROM myCart WHERE idP = :productId")
    MyCart getMyCartByID (int productId);

    @Query("DELETE FROM myCart")
    void deleteAll();

    @Delete
    void deleteProduct(MyCart myCart);
}
