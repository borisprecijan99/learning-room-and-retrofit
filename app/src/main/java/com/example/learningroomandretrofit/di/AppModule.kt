package com.example.learningroomandretrofit.di

import android.content.Context
import androidx.room.Room
import com.example.learningroomandretrofit.data.PersonDao
import com.example.learningroomandretrofit.data.PersonDatabase
import com.example.learningroomandretrofit.data.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePersonDatabase(@ApplicationContext context: Context): PersonDatabase {
        return Room.databaseBuilder(context, PersonDatabase::class.java, "person_db").build()
    }

    @Singleton
    @Provides
    fun providePersonDao(personDatabase: PersonDatabase): PersonDao {
        return personDatabase.personDao()
    }

    @Singleton
    @Provides
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder().baseUrl("https://dummyjson.com/quotes/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(QuoteApi::class.java)
    }
}