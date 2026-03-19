package ru.keepitlock.featurecourses.data.api

import retrofit2.http.GET
import ru.keepitlock.featurecourses.data.model.CoursesResponse

interface CoursesApi {
    @GET("courses")
    suspend fun getCourses(): CoursesResponse
}
