package com.betylf.b_corner.ui.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betylf.b_corner.R
import com.betylf.b_corner.databinding.TreatmentItemBinding
import kotlin.math.min

class TreatmentAdapter(private val name: Array<String>, private val description: Array<String>) :
    RecyclerView.Adapter<TreatmentAdapter.ViewHolder>() {
    private lateinit var binding: TreatmentItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = TreatmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(name[position], description[position])
    }

    override fun getItemCount(): Int = min(name.size, description.size)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, description: String) {
            with(binding) {
                tvName.text = name
                tvDesc.text = description
            }
        }
    }
}