package com.example.pm3.models.SharedPreferences

import android.content.Context


class SharedPrefManager private constructor(private val mCtx: Context) {

    fun saveValue(value: String) { //Se usa shared preferences para guardar el numero escogido en la sección de compentencias
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("numEquipo",value.toString())

        editor.apply()
        editor.commit()
    }

    fun getValue():String{ //Con esta función se obtiene el valor guardado previamente que se tiene en memoria
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("numEquipo","0")!!
    }
    fun clear() { //Con esta función se limpia el valor guardado que tiene en moemoria
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("numEquipo");
        editor.apply()
        editor.commit()

    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized //Singleton para crear un sola intancia durante toda la aplicación
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}