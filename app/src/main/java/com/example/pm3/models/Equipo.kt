package com.example.pm3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Equipo(@PrimaryKey val id: String,
                  val numEquipo: String,
                  val name: String,
                  val url: String,
                    val anio: String)