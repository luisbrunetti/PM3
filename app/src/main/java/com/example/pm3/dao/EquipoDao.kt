package com.example.pm3.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pm3.models.Competencia
import com.example.pm3.models.Equipo

@Dao
interface EquipoDao { //Data Acces Object de los principales metodos se que itlizaran en la base dedatos de ROOM
    @Query("SELECT * FROM equipo")
    fun getAllEquipos(): List<Equipo> //Se llama a una lista de equipos

    @Query("SELECT * FROM equipo WHERE numEquipo = :numEquipo")
    fun getEquiposByCompetencia(numEquipo: Int) : List<Equipo> //Se obtiene los equipos por la competencnia escogida

    @Query("SELECT * FROM equipo WHERE numEquipo = :numEquipo ORDER BY puntaje DESC")
    fun getEquiposByCompetenciaAndOrderByPoints(numEquipo: Int): List<Equipo> //Se obtiene los equipos por competencia escogida y ordenada por la cantidad de puntos que tienen

    @Insert
    fun insertEquipos(vararg equipo: Equipo) //Función que sirve para ingresar un nuevo objecto Equipo a la base dedatos

    @Delete
    fun deleteEquipos(equipo: Equipo) //Función que sirve para borrar un equipo de la base dedatos
}