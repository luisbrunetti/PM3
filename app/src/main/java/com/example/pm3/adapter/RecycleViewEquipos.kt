package com.example.pm3.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.pm3.R
import com.example.pm3.models.Equipo
import com.squareup.picasso.Picasso

class RecycleViewEquipos (var data: ArrayList<Equipo>, var context: Context? , var onClickListener: ClickRecycleViewEquipos):
                                    RecyclerView.Adapter<RecycleViewEquipos.ViewHolder>() {
    private var mData: ArrayList<Equipo> = this.data //Guardando la lista de tipo equipo en un atributo
    private var mInflater:LayoutInflater = LayoutInflater.from(this.context) //Guardando el layout
    private var mcontext: Context? = this.context // Guardando el contexto
    private val mOnClickListener = onClickListener //Guardando la interface ClickRecycleViewEquipos en un atributo de la clase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recycleview_activity_equipos, null)
        return ViewHolder(view,mOnClickListener,mcontext!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View, onClickEquipo: ClickRecycleViewEquipos, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener { //Se hereda la clase OnclickListtener
        // Declarando los atributos de la clase View Holder
        private var tvNombreEquipo = itemView.findViewById<TextView>(R.id.tv_title_equipo)
        private var tvAnioFundado = itemView.findViewById<TextView>(R.id.tv_aniofundado)
        private var ivImagenEquipo = itemView.findViewById<ImageView>(R.id.iv_equipo)
        private var mOnClickEquipo = onClickEquipo //Guardando la interface OnClickEquipo en un atributo

        init {
            //Asignando la clase Onclick listener al ItemView del recycle View
            itemView.setOnClickListener(this)
        }
        fun bindData(item: Equipo){
            //Asignando texto con la información del objeto
            tvNombreEquipo.text = item.name
            tvAnioFundado.text = item.anio
            // usando picasso para descargar la imagen de la web
            ivImagenEquipo.setImageResource(R.drawable.olregion)


        }
        //Usando la función onclicklistener para enviar la orden al activity a traves de la interface creada
        override fun onClick(v: View?) {
            mOnClickEquipo.onClickEquipos(adapterPosition)
        }


    }
    //Creando una interface para comunicarse con el activity
    interface ClickRecycleViewEquipos{
        fun onClickEquipos(position: Int)
    }

}