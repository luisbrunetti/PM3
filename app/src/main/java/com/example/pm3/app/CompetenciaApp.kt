package com.example.pm3.app

import android.app.Application
import androidx.room.Room
import com.example.pm3.dbs.CompetenciaDb
import com.example.pm3.models.Competencia

class CompetenciaApp : Application() {

    val room = Room.databaseBuilder(this,
    CompetenciaDb::class.java, "competencia").build()
}