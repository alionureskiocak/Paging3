package com.example.paging3.data.di

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import com.example.paging3.data.local.UserDao
import com.example.paging3.data.local.UserDatabase
import com.example.paging3.data.remote.UserApi
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
    fun provideUserApi() : UserApi{
        return Retrofit.Builder()
            .baseUrl("//https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDatabase(
        @ApplicationContext context : Context
    ) : UserDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = UserDatabase::class.java,
            name = "user_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db : UserDatabase) : UserDao{
        return db.userDao()
    }
}