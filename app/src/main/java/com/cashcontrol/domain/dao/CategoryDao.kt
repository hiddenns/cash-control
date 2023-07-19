package com.cashcontrol.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cashcontrol.data.room.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE categoryId IN (:categoryIds)")
    fun loadAllByIds(categoryIds: List<Int>): Flow<List<Category>>

    @Insert
    fun insertAll(join: List<Category>)

    @Insert
    fun insert(join: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun getById(categoryId: Long): Category

}