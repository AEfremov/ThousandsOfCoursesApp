package ru.keepitlock.coredata.model.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.keepitlock.coredata.model.db.AppDatabase
import ru.keepitlock.coredata.model.dao.FavoritesDao
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoritesDao(database: AppDatabase): FavoritesDao {
        return database.favoritesDao()
    }
}