package com.example.pm3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pm3.models.Competencia

class RecycleViewCompentencias (var data: ArrayList<Competencia>, var context: Context?):
                                    RecyclerView.Adapter<RecycleViewCompentencias.ViewHolder>() {
    private var mData: ArrayList<Competencia> = this.data
    private var mInflater:LayoutInflater = LayoutInflater.from(this.context)
    private var mcontext: Context? = this.context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recycleview_activity_competencias, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitleName = itemView.findViewById<TextView>(R.id.tv_title)
        fun bindData(item: Competencia){
            tvTitleName.text = item.name
        }
    }
}