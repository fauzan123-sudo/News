package com.example.news.module

import android.content.Context
import androidx.room.Room
import com.example.news.data.db.PaintDB
import com.example.news.data.model.Notification
import com.example.news.util.Constans.CONTACTS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PaintDB::class.java, CONTACTS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db: PaintDB) = db.contactsDao()


    @Provides
    fun provideEntity() = Notification()

}