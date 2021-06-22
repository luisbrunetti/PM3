package com.example.pm3.activies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pm3.adapter.RecycleViewCompentencias
import com.example.pm3.databinding.ActivityMainBinding
import com.example.pm3.dbs.DatabaseRoom
import com.example.pm3.models.Competencia
import com.example.pm3.models.Equipo
import com.example.pm3.models.SharedPreferences.SharedPrefManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(), ComActivies{
    companion object {
        const val TAG = "MainActivityTag"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mFireStore: FirebaseFirestore
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private fun ClearDatabase(){
        val roomDao = DatabaseRoom.getDatabase(this)!!.competenciaDao()
        val roomDaoEquipo = DatabaseRoom.getDatabase(applicationContext)!!.equipoDao()
        val arrayCompetencia = roomDao.getAllCompetencias()
        val arrayEquipos = roomDaoEquipo.getAllEquipos()
        for(a in arrayCompetencia){
            roomDao.deleteCompetencia(a)
        }

        for(b in arrayEquipos){
            roomDaoEquipo.deleteEquipos(b)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ClearDatabase()
        binding.btnMainActivity.setOnClickListener {
            binding.pbActivityMain.visibility = View.VISIBLE
            binding.tvNoDataMainActivity.visibility = View.GONE
            binding.btnMainActivity.visibility = View.GONE
            binding.rvMainActivity.visibility = View.VISIBLE
            mFireStore = FirebaseFirestore.getInstance()
            mFirebaseDatabase = FirebaseDatabase.getInstance()
            val myref = mFirebaseDatabase.getReference("Competencia")

            myref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d(TAG, "ONCREATE")
                    val arrayDoc = ArrayList<Competencia>()
                    val roomDaoCompetencia = DatabaseRoom.getDatabase(applicationContext)!!.competenciaDao()
                    val roomDaoEquipo = DatabaseRoom.getDatabase(applicationContext)!!.equipoDao()
                    var count = 0
                    for(snap in snapshot.children){
                        Log.d("snap", snap.child("teams").children.toString())
                        val arrayEquipo = ArrayList<Equipo>()
                        for(team in snap.child("teams").children){
                            Log.d("team", team.child("name").value.toString())
                            val equipo =  Equipo(team.child("id").value.toString(),count.toString(), team.child("name").value.toString(),
                                team.child("points").value.toString().toInt(),0,
                                team.child("crestUrl").value.toString(), team.child("founded").value.toString())
                            arrayEquipo.add(equipo)
                            roomDaoEquipo.insertEquipos(equipo)
                        }
                        Log.d(TAG,snap.child("name").value.toString())
                        val plan = snap.child("plan").value.toString()
                        val date = snap.child("currentSeason").child("startDate").value.toString()
                        Log.d("date",date)
                        val listSplit = date.split("-")
                        val concat = snap.child("name").value.toString().plus("-").plus(listSplit[0])
                        Log.d("concant", concat)
                        val competencia = Competencia(snap.child("id").value.toString(),concat,snap.child("cantidad").value.toString(),plan)
                        arrayDoc.add(competencia)
                        roomDaoCompetencia.insertCompetencia(competencia)
                        Log.d("equipo",arrayEquipo.toString())
                        count += 1
                    }
                    llenarDatos()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun llenarDatos() {
        binding.pbActivityMain.visibility = View.GONE
        val roomDao = DatabaseRoom.getDatabase(this)!!.competenciaDao()
        val array = roomDao.getAllCompetencias()
        //Log.d("array", array.toString())

        val recycleView = RecycleViewCompentencias(array as ArrayList<Competencia>, this,this)
        binding.rvMainActivity.setHasFixedSize(true)
        binding.rvMainActivity.layoutManager = LinearLayoutManager(this)
        binding.rvMainActivity.adapter = recycleView
    }

    override fun onClickListenerRecycleView(position : Int) {
        val intent = Intent(this, NavigationDrawable::class.java)

        SharedPrefManager.getInstance(applicationContext).clear()
        SharedPrefManager.getInstance(applicationContext).saveValue(position.toString())
        startActivity(intent)
    }


}