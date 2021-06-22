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
    private var mData: ArrayList<Equipo> = this.data
    private var mInflater:LayoutInflater = LayoutInflater.from(this.context)
    private var mcontext: Context? = this.context
    private val mOnClickListener = onClickListener
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

    class ViewHolder(itemView: View, onClickEquipo: ClickRecycleViewEquipos, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var tvNombreEquipo = itemView.findViewById<TextView>(R.id.tv_title_equipo)
        private var tvAnioFundado = itemView.findViewById<TextView>(R.id.tv_aniofundado)
        private var ivImagenEquipo = itemView.findViewById<ImageView>(R.id.iv_equipo)
        private var mContext = context
        private var mOnClickEquipo = onClickEquipo

        init {
            itemView.setOnClickListener(this)
        }
        fun bindData(item: Equipo){
            tvNombreEquipo.text = item.name
            tvAnioFundado.text = item.anio
            Log.d("url",item.url)
            if(item.url.isNotEmpty()){
                Picasso.get()
                    .load("https://i.pinimg.com/736x/2c/f2/b7/2cf2b7ee43f7706a5746949651bb2507.jpg")
                    .into(ivImagenEquipo)
            }else{
                ivImagenEquipo.setImageResource(R.drawable.trophy)
            }


        }

        override fun onClick(v: View?) {
            mOnClickEquipo.onClickEquipos(adapterPosition)
        }


    }
    interface ClickRecycleViewEquipos{
        fun onClickEquipos(position: Int)
    }

}