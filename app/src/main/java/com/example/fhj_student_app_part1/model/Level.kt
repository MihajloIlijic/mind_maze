package com.example.fhj_student_app_part1.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Level(
    val id: Int,
    val name: String,
    val description: String,
    val isLocked: Boolean = true,
    val isCompleted: Boolean = false,
    val stars: Int = 0,
    val requiredStars: Int = 0
) : Parcelable 