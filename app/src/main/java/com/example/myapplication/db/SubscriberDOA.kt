package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

//Room Data Access Objets

@Dao
interface SubscriberDOA {

    @Insert //(onConflict = OnConflictStrategy.IGNORE) we can use this as well
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteALl()

    @Query("SELECT * FROM SUBSCRIBER_DATA_TABLE")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}

