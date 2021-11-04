package com.example.room_database_exercise.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_database_exercise.databinding.FragmentProjectBinding
import com.example.room_database_exercise.fragments.MainFragmentDirections
import com.example.room_database_exercise.model.Project

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    lateinit var binding: FragmentProjectBinding
    private var projects = emptyList<Project>()

    inner class ViewHolder(binding: FragmentProjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.projectId.text = project.ID.toString()
            binding.projectTitle.text = project.title
            binding.projectDescription.text = project.description
            binding.projectLayout.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(project)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = FragmentProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(projects: List<Project>) {
        this.projects = projects
        notifyDataSetChanged()
    }
}