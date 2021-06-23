package com.example.pm3.activies.ui.Equipos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pm3.adapter.RecycleViewEquipos
import com.example.pm3.databinding.FragmentHomeBinding
import com.example.pm3.dbs.DatabaseRoom
import com.example.pm3.models.Equipo
import com.example.pm3.models.SharedPreferences.SharedPrefManager

class EquiposFragment : Fragment() , RecycleViewEquipos.ClickRecycleViewEquipos {
    //Declarando el tipo de la variable BINDING con su respectivo fragment
    private var _binding: FragmentHomeBinding? = null
    // Obteniendo el binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root // Asignado la vista usando binding.root

        val numEquipo = SharedPrefManager.getInstance(requireContext()).getValue() //obteniendo la oompetencia escodigo usandoShared preferences
        Log.d("equipos", numEquipo.toString())

        val roomDaoEquipo = DatabaseRoom.getDatabase(requireContext())!!.equipoDao() //Obteniendo el DAO de Equipo
        val arrayEquipos  = roomDaoEquipo.getEquiposByCompetencia(numEquipo = numEquipo.toInt()) // Obteniendo una lista de los Equipos por el numero de competencia
        Log.d("asd", arrayEquipos.toString())
        val recycleView = RecycleViewEquipos(arrayEquipos as ArrayList<Equipo>, requireContext(),this) //Inicializando el  adapter recycle view
        binding.rvFragmentEquipos.setHasFixedSize(true)
        binding.rvFragmentEquipos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentEquipos.adapter = recycleView // Aplicando el adapter al recycleView del fragment

        return root
    }

    override fun onClickEquipos(position: Int) {

    }

}