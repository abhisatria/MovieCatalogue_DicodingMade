package com.abhiwisesa.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.abhiwisesa.moviecatalogue.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
//    @Query("SELECT * FROM movieentity WHERE isMovie = 1 AND isFavorite = 1")
//    fun getFavoriteMovie(): PagingSource<Int, MovieEntity>
//
//    @Query("SELECT * FROM movieentity WHERE isMovie = 0 AND isFavorite = 1")
//    fun getFavoriteShow(): PagingSource<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteShow(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentity WHERE id = :id")
    fun getMovieById(id:Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie:MovieEntity)

    @Query("DELETE FROM movieentity WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM movieentity WHERE id = :id)")
    fun isMovieSaved(id: Int): LiveData<Boolean>

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovie(listMovie: List<MovieEntity>)

    @Query("SELECT * FROM movieentity where isMovie = 1")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity where isMovie = 0")
    fun getAllShow(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE isMovie = 1 AND isFavorite = 1")
    fun getFlowFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE isMovie = 0 AND isFavorite = 1")
    fun getFlowFavoriteShow(): Flow<List<MovieEntity>>
}