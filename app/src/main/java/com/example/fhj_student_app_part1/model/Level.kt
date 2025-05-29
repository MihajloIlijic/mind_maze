package com.example.fhj_student_app_part1.model

data class Level(
    val id: Int,
    val name: String,
    val description: String,
    val isLocked: Boolean = true,
    val isCompleted: Boolean = false,
    val stars: Int = 0,
    val requiredStars: Int = 0
) 