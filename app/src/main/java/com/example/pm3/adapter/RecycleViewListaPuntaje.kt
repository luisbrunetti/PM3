package com.example.pm3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pm3.R
import com.example.pm3.models.Equipo

class RecycleViewListaPuntaje(
    var data: ArrayList<Equipo>,
    var context: Context?
) :
    RecyclerView.Adapter<RecycleViewListaPuntaje.ViewHolder>() {
    private var mData: ArrayList<Equipo> = this.data
    private var mInflater: LayoutInflater = LayoutInflater.from(this.context)
    private var mcontext: Context? = this.context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recycleview_activity_lista_puntaje, null)
        return ViewHolder(view, mcontext!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View,context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private var tvNombreEquipo =
            itemView.findViewById<TextView>(R.id.tv_title_Equipo_lsita_equipos)
        private var tvValorPuntaje =
            itemView.findViewById<TextView>(R.id.tv_value_puntaje_lista_equipos)
        private var tvPosicion = itemView.findViewById<TextView>(R.id.tv_posicion_equipo)
        private var mContext = context
        fun bindData(item: Equipo) {
            tvNombreEquipo.text = item.name
            tvValorPuntaje.text = item.puntaje.toString()
            tvPosicion.text = item.position.toString()

        }


    }

}