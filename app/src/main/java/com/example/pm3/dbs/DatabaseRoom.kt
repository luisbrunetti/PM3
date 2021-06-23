package com.example.pm3.dbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pm3.dao.CompetenciaDao
import com.example.pm3.dao.EquipoDao
import com.example.pm3.models.Competencia
import com.example.pm3.models.Equipo

@Database(
    entities = [Competencia::class , Equipo::class],
    version = 1
)
//Craendo Base de datos Room
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun competenciaDao(): CompetenciaDao //Función abstracta que tiene las funciones DAO del data class Competencia
    abstract fun equipoDao(): EquipoDao //Función abstracta que tiene las funciones DAO del data class Equipo
    companion object{
        @Volatile //Implementando singleton para llamada ala instancia de la base dedatos
        private var INSTANCE : DatabaseRoom? = null
        fun getDatabase(context : Context): DatabaseRoom? {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseRoom::class.java,
                    "competenciaDB"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}