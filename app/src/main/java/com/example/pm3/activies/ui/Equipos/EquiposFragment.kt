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

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val numEquipo = SharedPrefManager.getInstance(requireContext()).getValue()
        Log.d("equipos", numEquipo.toString())


        val roomDaoEquipo = DatabaseRoom.getDatabase(requireContext())!!.equipoDao()
        val arrayEquipos  = roomDaoEquipo.getEquiposByCompetencia(numEquipo = numEquipo.toInt())
        Log.d("asd", arrayEquipos.toString())
        val recycleView = RecycleViewEquipos(arrayEquipos as ArrayList<Equipo>, requireContext(),this)
        binding.rvFragmentEquipos.setHasFixedSize(true)
        binding.rvFragmentEquipos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentEquipos.adapter = recycleView

        return root
    }

    override fun onClickEquipos(position: Int) {

    }

}