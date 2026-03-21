package ru.keepitlock.coredata.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.keepitlock.coredata.model.entity.CourseEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(course: CourseEntity)

    @Query("DELETE FROM favorites WHERE id = :courseId")
    suspend fun deleteFavoriteById(courseId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :courseId)")
    fun isFavoriteFlow(courseId: Int): Flow<Boolean>
}