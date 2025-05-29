package com.example.fhj_student_app_part1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhj_student_app_part1.R
import com.example.fhj_student_app_part1.model.Level

class LevelAdapter(
    private val levels: List<Level>,
    private val onLevelClick: (Level) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvLevelNumber: TextView = view.findViewById(R.id.tvLevelNumber)
        val tvLevelName: TextView = view.findViewById(R.id.tvLevelName)
        val tvLevelDescription: TextView = view.findViewById(R.id.tvLevelDescription)
        val ivLevelStatus: ImageView = view.findViewById(R.id.ivLevelStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val level = levels[position]
        
        holder.tvLevelNumber.text = "Level ${level.id}"
        holder.tvLevelName.text = level.name
        holder.tvLevelDescription.text = level.description
        
        // Set level status icon
        holder.ivLevelStatus.setImageResource(
            when {
                level.isCompleted -> R.drawable.ic_check
                level.isLocked -> R.drawable.ic_lock
                else -> R.drawable.ic_play
            }
        )

        holder.itemView.setOnClickListener {
            onLevelClick(level)
        }
    }

    override fun getItemCount() = levels.size
} 