package com.example.pm3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pm3.models.Competencia
import com.example.pm3.models.Equipo

@Dao
interface EquipoDao {
    @Query("SELECT * FROM equipo")
    fun getAllEquipos(): List<Equipo>

    @Query("SELECT * FROM equipo WHERE numEquipo = :numEquipo")
    fun getEquiposByCompetencia(numEquipo: Int) : List<Equipo>

    @Query("SELECT * FROM equipo WHERE numEquipo = :numEquipo ORDER BY puntaje DESC")
    fun getEquiposByCompetenciaAndOrderByPoints(numEquipo: Int): List<Equipo>

    @Insert
    fun insertEquipos(vararg equipo: Equipo)

    @Delete
    fun deleteEquipos(equipo: Equipo)
}