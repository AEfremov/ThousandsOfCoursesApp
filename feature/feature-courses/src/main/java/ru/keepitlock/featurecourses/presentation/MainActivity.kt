package ru.keepitlock.featurecourses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import ru.keepitlock.coreui.BaseActivity
import ru.keepitlock.featurecourses.R
import ru.keepitlock.featurecourses.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun createBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CoursesFragment.newInstance())
                .commit()
        }
    }
}