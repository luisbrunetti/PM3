package com.example.pm3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pm3.models.Competencia

@Dao
interface CompetenciaDao {
    @Query("SELECT * FROM competencias")
    fun getAllCompetencias(): List<Competencia>

    @Insert
    fun insertCompetencia(vararg comp: Competencia)

    @Delete
    fun deleteCompetencia(comp: Competencia)
}