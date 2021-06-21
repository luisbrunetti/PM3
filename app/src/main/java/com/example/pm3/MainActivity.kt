package com.example.pm3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pm3.databinding.ActivityMainBinding
import com.example.pm3.dbs.CompetenciaDb
import com.example.pm3.models.Competencia
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivityTag"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFireStore: FirebaseFirestore
    private fun ClearDatabase(){
        val roomDao = CompetenciaDb.getDatabase(this)!!.competenciaDao()
        val array = roomDao.getAllCompetencias()
        for(a in array){
            roomDao.deleteCompetencia(a)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ClearDatabase()
        binding.btnMainActivity.setOnClickListener {
            binding.btnMainActivity.visibility = View.GONE
            binding.rvMainActivity.visibility = View.VISIBLE
            mFireStore = FirebaseFirestore.getInstance()
            mFireStore.collection("competencias")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        Log.d(TAG, "ONCREATE")
                        val arrayDoc = ArrayList<Competencia>()
                        val roomDao = CompetenciaDb.getDatabase(this)!!.competenciaDao()
                        for (doc in task.result!!.iterator()) {
                            Log.d(TAG, doc.data["name"].toString())
                            arrayDoc.add(Competencia(doc.id, doc.data["name"].toString()))
                            roomDao.insertCompetencia(Competencia(doc.id, doc.data["name"].toString()))
                            /*val arr = roomDao.getAllCompetencias()
                            Log.d(TAG, arr.toString())*/
                        }
                        Log.d(TAG, arrayDoc.toString())
                        llenarDatos()


                    } else {
                        Toast.makeText(
                            this,
                            "Hubo un error al conectarse con Firebase",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnCanceledListener {
                    Toast.makeText(
                        this,
                        "Hubo un error al conectarse con Firebase",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun llenarDatos() {
        val roomDao = CompetenciaDb.getDatabase(this)!!.competenciaDao()
        val array = roomDao.getAllCompetencias()
        Log.d("array", array.toString())

        val recycleView = RecycleViewCompentencias(array as ArrayList<Competencia>, this)
        binding.rvMainActivity.setHasFixedSize(true)
        binding.rvMainActivity.layoutManager = LinearLayoutManager(this)
        binding.rvMainActivity.adapter = recycleView
    }

}