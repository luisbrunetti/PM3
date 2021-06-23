package com.example.pm3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pm3.models.Competencia

@Dao
interface CompetenciaDao { //Data Access Object para la tabla de competencnia
    @Query("SELECT * FROM competencias")
    fun getAllCompetencias(): List<Competencia> //Función que permite traer todas las competencias en una lista

    @Insert
    fun insertCompetencia(vararg comp: Competencia) // Función que permite ingresar un objecto competencia a la base dedatos

    @Delete
    fun deleteCompetencia(comp: Competencia) // Función que permite borrar una competencia de la base dedatos
}