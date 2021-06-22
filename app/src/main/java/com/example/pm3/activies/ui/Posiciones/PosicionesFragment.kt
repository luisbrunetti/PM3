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

    private var _binding: FragmentGalleryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val numEquipo = SharedPrefManager.getInstance(requireContext()).getValue()

        val roomDaoEquipo = DatabaseRoom.getDatabase(requireContext())!!.equipoDao()
        val arrayEquipos  = roomDaoEquipo.getEquiposByCompetenciaAndOrderByPoints(numEquipo.toInt())
        var cont = 1
        for( eq in arrayEquipos){
            eq.position = cont
            cont +=1
        }
        Log.d("posicionFragment", arrayEquipos.toString())
        val recycleView = RecycleViewListaPuntaje(arrayEquipos as ArrayList<Equipo>, requireContext())
        binding.rvFragmentListaEquipos.setHasFixedSize(true)
        binding.rvFragmentListaEquipos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentListaEquipos.adapter = recycleView

        return root
    }
}