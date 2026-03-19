package ru.keepitlock.featurecourses.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.keepitlock.featurecourses.databinding.ItemCourseBinding
import ru.keepitlock.featurecourses.presentation.model.CourseUi

fun courseAdapterDelegate(
    onItemClick: (CourseUi) -> Unit = {}
) = adapterDelegateViewBinding<CourseUi, CourseUi, ItemCourseBinding>(
    { layoutInflater, parent -> ItemCourseBinding.inflate(layoutInflater, parent, false) }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.apply {
            courseTitleTextView.text = item.title
        }
    }
}
