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
import com.example.pm3.activies.ComActivies
import com.example.pm3.models.Competencia
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class RecycleViewCompentencias (var data: ArrayList<Competencia>, var context: Context?, val onClickListener: ComActivies):
                                    RecyclerView.Adapter<RecycleViewCompentencias.ViewHolder>() {
    private var mData: ArrayList<Competencia> = this.data
    private var mInflater:LayoutInflater = LayoutInflater.from(this.context)
    private var mcontext: Context? = this.context
    private val mOnClickListener = this.onClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recycleview_activity_competencias, null)
        return ViewHolder(view,mOnClickListener,mcontext!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View, mComActivies: ComActivies, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var tvTitleName = itemView.findViewById<TextView>(R.id.tv_title)
        private var tvNumeroEquipos = itemView.findViewById<TextView>(R.id.tv_numero_equipos)
        private var ivCompetencia = itemView.findViewById<ImageView>(R.id.iv_competencias)
        private var tvNumberTier = itemView.findViewById<TextView>(R.id.tv_num_tier)
        private var mContext = context
        private var mInterface = mComActivies

        init {
            itemView.setOnClickListener(this)
        }
        fun bindData(item: Competencia){
            Log.d("item", item.name)
            tvTitleName.text = item.name
            tvNumeroEquipos.text = item.cantidad
            tvNumberTier.text = item.plan
        }

        override fun onClick(v: View?) {
            mInterface.onClickListenerRecycleView(adapterPosition)
        }


    }
}