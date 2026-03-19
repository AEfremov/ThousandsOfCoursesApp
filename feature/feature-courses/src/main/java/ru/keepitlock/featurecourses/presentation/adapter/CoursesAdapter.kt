package ru.keepitlock.featurecourses.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.keepitlock.featurecourses.presentation.model.CourseUi

class CoursesAdapter(
    onItemClick: (CourseUi) -> Unit = {}
) : AsyncListDifferDelegationAdapter<CourseUi>(CourseDiffCallback()) {

    init {
        delegatesManager.addDelegate(courseAdapterDelegate(onItemClick))
    }
}
class CourseDiffCallback : DiffUtil.ItemCallback<CourseUi>() {

    override fun areItemsTheSame(oldItem: CourseUi, newItem: CourseUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CourseUi, newItem: CourseUi): Boolean {
        return oldItem == newItem
    }
}
