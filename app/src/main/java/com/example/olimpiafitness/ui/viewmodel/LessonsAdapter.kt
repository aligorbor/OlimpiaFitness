package com.example.olimpiafitness.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.olimpiafitness.data.model.Lesson
import com.example.olimpiafitness.databinding.ItemLessonBinding
import com.example.olimpiafitness.databinding.ItemLessonNextBinding

class LessonsAdapter(
    private val values: ArrayList<Lesson>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_FIRST) ViewHolderFirst(
            ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else
            ViewHolderNext(
                ItemLessonNextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
        if (getItemViewType(position) == TYPE_FIRST) {
            with(holder as ViewHolderFirst) {
                name.text = item.name
                coach.text = item.coachId
                startTime.text = item.startTime
                endTime.text = item.endTime
                dOfW.text = item.date
            }
        } else {
            with(holder as ViewHolderNext) {
                name.text = item.name
                coach.text = item.coachId
                startTime.text = item.startTime
                endTime.text = item.endTime
            }
        }
    }

    override fun getItemCount(): Int = values.size

    override fun getItemViewType(position: Int): Int {
        // return if (values[position].subjectObj.obligatory) TYPE_OBLIGATORY else TYPE_OPTIONAL
        return TYPE_FIRST
    }

    fun addLessons(lessons: List<Lesson>) {
        this.values.apply {
            clear()
            addAll(lessons)
        }
    }

    inner class ViewHolderFirst(binding: ItemLessonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.itemName
        val coach: TextView = binding.itemCoach
        val startTime: TextView = binding.startTime
        val endTime: TextView = binding.endTime
        val dOfW: TextView = binding.textDofw
    }

    inner class ViewHolderNext(binding: ItemLessonNextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.itemName
        val coach: TextView = binding.itemCoach
        val startTime: TextView = binding.startTime
        val endTime: TextView = binding.endTime
    }

    companion object {
        private const val TYPE_FIRST = 1
        private const val TYPE_NEXT = 0
    }
}