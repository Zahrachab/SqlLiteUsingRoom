package com.example.myappplombiers

import android.R
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PlombierAdapter(private val mCtx: Context, private val list: List<Plombier>) :
    RecyclerView.Adapter<PlombierAdapter.PlombierViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlombierViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(com.example.myappplombiers.R.layout.item_view, parent, false)
        return PlombierViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlombierViewHolder, position: Int) {
        val p = list[position]
        holder.item_numero.setText(p.numero)
        holder.item_nom.setText(p.nom)
        holder.item_type.setText(p.type)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlombierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var item_numero: TextView
        var item_nom: TextView
        var item_type: TextView

        init {

            item_numero= itemView.findViewById(com.example.myappplombiers.R.id.item_numero)
            item_nom = itemView.findViewById(com.example.myappplombiers.R.id.item_nom)
            item_type = itemView.findViewById(com.example.myappplombiers.R.id.item_type)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val p = list[adapterPosition]

            val intent = Intent(mCtx, UpdatePlombierActivity::class.java)
            intent.putExtra("plombier", p)

            mCtx.startActivity(intent)
        }
    }
}