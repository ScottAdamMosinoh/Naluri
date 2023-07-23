package com.example.naluri.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naluri.databinding.CircumferenceViewItemBinding
import com.example.naluri.model.CircumferenceModel

class CircumferenceAdapter(var context: Context?, var circumferenceLists: ArrayList<CircumferenceModel>?) : RecyclerView.Adapter<CircumferenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CircumferenceViewItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return circumferenceLists?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        circumferenceLists?.get(position)?.let { circumferenceModel ->
            holder.binding.tvLabel.text = circumferenceModel.title
            holder.binding.tvRadius.text = circumferenceModel.radius
            holder.binding.tvCircumference.text = circumferenceModel.circumference
        }
    }

    fun refresh (circumferenceList: ArrayList<CircumferenceModel>?) {
        this.circumferenceLists = circumferenceList
        this.notifyDataSetChanged()
    }

    class ViewHolder(var binding: CircumferenceViewItemBinding) : RecyclerView.ViewHolder(binding.root)
}
