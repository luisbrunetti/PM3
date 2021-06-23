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
    private lateinit var mFirebaseDatabase: FirebaseDatabase
    private fun ClearDatabase(){ //Función que se encarga de limpiar los datos que se tienen en memoria en la base de datos ROOM
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
        binding = ActivityMainBinding.inflate(layoutInflater) //Inciializando el binding con el layout del Main Activity
        setContentView(binding.root)
        val roomDaoCompetencia = DatabaseRoom.getDatabase(applicationContext)!!.competenciaDao() //Obteniendo DAO de la entiendad Competencia
        val roomDaoEquipo = DatabaseRoom.getDatabase(applicationContext)!!.equipoDao() //Obteniendo DAO de Equipo
        val lista = roomDaoCompetencia.getAllCompetencias()
        val listaTeam = roomDaoEquipo.getAllEquipos()
        if(lista.isNotEmpty() && listaTeam.isNotEmpty()){
            binding.tvNoDataMainActivity.visibility = View.GONE //Haciendo invisible el textview
            binding.btnMainActivity.visibility = View.GONE //Haciendo invisible el boton
            binding.rvMainActivity.visibility = View.VISIBLE //Haciendo visible el recycle view
            llenarDatos()
        }else{
            binding.btnMainActivity.setOnClickListener {
                ClearDatabase() //Limpiando los datos de la memoria
                binding.pbActivityMain.visibility = View.VISIBLE //Haciendo visible el progress bar
                binding.tvNoDataMainActivity.visibility = View.GONE //Haciendo invisible el textview
                binding.btnMainActivity.visibility = View.GONE //Haciendo invisible el boton
                binding.rvMainActivity.visibility = View.VISIBLE //Haciendo visible el recycle view
                mFirebaseDatabase = FirebaseDatabase.getInstance() // Intancia al Real Time Database
                val myref = mFirebaseDatabase.getReference("Competencia") //Collecion de Firebase

                //Se llama al firebase para descargar los datos que necesita la aplicación
                myref.addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        Log.d(TAG, "ONCREATE")
                        val arrayDoc = ArrayList<Competencia>()
                        var count = 0 //contador 0 QUE aumentara para indicar el numero de equipo al ccual pertenecen cada Equipo
                        for(snap in snapshot.children){

                            val arrayEquipo = ArrayList<Equipo>()
                            //Se recorre el arreglo "teams" donde se guarda la información de los equipos por competencina
                            for(team in snap.child("teams").children){
                                //Guardadn ola información en una variable EQUIPO
                                val equipo =  Equipo(team.child("id").value.toString(),count.toString(), team.child("name").value.toString(),
                                    team.child("points").value.toString().toInt(),0,
                                    team.child("crestUrl").value.toString(), team.child("founded").value.toString())
                                arrayEquipo.add(equipo)
                                roomDaoEquipo.insertEquipos(equipo) //Insertando el objecto Equipo en la base dedatos ROOM
                            }
                            //Obteniendo el anio de la competencia
                            val plan = snap.child("plan").value.toString()
                            val date = snap.child("currentSeason").child("startDate").value.toString()
                            val listSplit = date.split("-") //Dividnedo el anio con split
                            val concat = snap.child("name").value.toString().plus("-").plus(listSplit[0])

                            //Guardando los datos necesarios en una clase Competencia
                            val competencia = Competencia(snap.child("id").value.toString(),concat,snap.child("cantidad").value.toString(),plan)
                            arrayDoc.add(competencia)
                            roomDaoCompetencia.insertCompetencia(competencia) //Agregando el objecto competencnia a la base edatos Room
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

    }

    private fun llenarDatos() { //Se llenan los datos del recycleview del Main acctivity
        binding.pbActivityMain.visibility = View.GONE
        val roomDao = DatabaseRoom.getDatabase(this)!!.competenciaDao() // Para llenar los datos se llama a la información guardada en el DAO competencnia
        val array = roomDao.getAllCompetencias() // SE Obtiene todas las competencnias en una lista

        val recycleView = RecycleViewCompentencias(array as ArrayList<Competencia>, this,this) //Se inicializa el recycleView Competencnia
        binding.rvMainActivity.setHasFixedSize(true)
        binding.rvMainActivity.layoutManager = LinearLayoutManager(this)
        binding.rvMainActivity.adapter = recycleView // sE INDICA EL ADAPTER
    }

    override fun onClickListenerRecycleView(position : Int) {
        //Cuandos e haga un click en un elemento del recycle se llamara esta función
        val intent = Intent(this, NavigationDrawable::class.java)
        //Se limpia la información en memoria
        SharedPrefManager.getInstance(applicationContext).clear()
        //Se guardada el valor de la posición seleccionada
        SharedPrefManager.getInstance(applicationContext).saveValue(position.toString())
        startActivity(intent)
    }


}