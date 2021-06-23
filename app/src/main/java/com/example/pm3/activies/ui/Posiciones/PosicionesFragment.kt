package com.example.pm3.activies.ui.Posiciones

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pm3.adapter.RecycleViewListaPuntaje
import com.example.pm3.databinding.FragmentGalleryBinding
import com.example.pm3.dbs.DatabaseRoom
import com.example.pm3.models.Equipo
import com.example.pm3.models.SharedPreferences.SharedPrefManager

class PosicionesFragment : Fragment() {
    //Declarando el tipo de la variable BINDING con su respectivo fragment
    private var _binding: FragmentGalleryBinding? = null
    // Obteniendo el binding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root // Asignado la vista usando binding.root

        val numEquipo = SharedPrefManager.getInstance(requireContext()).getValue() //obteniendo la oompetencia escodigo usandoShared preferences

        val roomDaoEquipo = DatabaseRoom.getDatabase(requireContext())!!.equipoDao() //Obteniendo el DAO de Equipo
        val arrayEquipos  = roomDaoEquipo.getEquiposByCompetenciaAndOrderByPoints(numEquipo.toInt()) // Obteniendo una lista de los Equipos por el numero de competencia ordenada por los puntos
        var cont = 1
        for( eq in arrayEquipos){
            eq.position = cont //Asignando un VALOR al atributo "posición" dependiendo el orden
            cont +=1
        }
        Log.d("posicionFragment", arrayEquipos.toString())
        val recycleView = RecycleViewListaPuntaje(arrayEquipos as ArrayList<Equipo>, requireContext())
        binding.rvFragmentListaEquipos.setHasFixedSize(true)
        binding.rvFragmentListaEquipos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentListaEquipos.adapter = recycleView // Poniendo el adapter del recycle view

        return root
    }
}