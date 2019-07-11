package com.example.myappplombiers;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface PlombierDao {

    @Query("SELECT * FROM Plombier")
    List<Plombier> getAll();

    @Insert
    void insert(Plombier p);

    @Delete
    void delete(Plombier p);

    @Update
    void update(Plombier p);

}