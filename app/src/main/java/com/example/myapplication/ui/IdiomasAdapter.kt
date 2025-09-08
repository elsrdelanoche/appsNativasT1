package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class IdiomasAdapter(
    private val items: List<String>,
    private val onChecked: (name: String, checked: Boolean) -> Unit
) : RecyclerView.Adapter<IdiomasAdapter.VH>() {

    private var selected: Set<String> = emptySet()

    fun updateSelection(newSel: Set<String>) {
        selected = newSel
        notifyDataSetChanged() // lista pequeña; suficiente
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_idioma, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val name = items[position]
        holder.bind(name, selected.contains(name), onChecked)
    }

    override fun getItemCount(): Int = items.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv = itemView.findViewById<TextView>(R.id.tvNombre)
        private val cb = itemView.findViewById<CheckBox>(R.id.cbSel)

        fun bind(name: String, isChecked: Boolean, onChecked: (String, Boolean) -> Unit) {
            tv.text = name
            // Evita disparos al reusar view
            cb.setOnCheckedChangeListener(null)
            cb.isChecked = isChecked
            cb.setOnCheckedChangeListener { _, checked -> onChecked(name, checked) }

            // Tocar la fila alterna el check
            itemView.setOnClickListener { cb.isChecked = !cb.isChecked }
        }
    }
}
