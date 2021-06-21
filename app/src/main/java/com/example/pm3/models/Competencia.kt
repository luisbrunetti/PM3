package com.example.pm3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competencias")
data class Competencia(@PrimaryKey
                       val id: String,
                       val name : String)