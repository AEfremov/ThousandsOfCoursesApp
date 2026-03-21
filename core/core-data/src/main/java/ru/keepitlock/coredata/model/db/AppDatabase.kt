package ru.keepitlock.coredata.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.keepitlock.coredata.model.dao.FavoritesDao
import ru.keepitlock.coredata.model.entity.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

    companion object {
        const val DATABASE_NAME = "courses_database"
    }
}