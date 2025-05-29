package com.example.fhj_student_app_part1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fhj_student_app_part1.model.Level
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class LevelActivity : AppCompatActivity() {
    private lateinit var level: Level
    private lateinit var tvLevelTitle: TextView
    private lateinit var tvLevelDescription: TextView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var btnCheck: Button
    private lateinit var btnHint: Button
    private lateinit var etAnswer: TextInputEditText
    private lateinit var tvPattern: TextView

    private val patterns = listOf(
        "2, 4, 6, 8, ?" to 10,
        "1, 3, 5, 7, ?" to 9,
        "3, 6, 9, 12, ?" to 15,
        "1, 2, 4, 8, ?" to 16,
        "2, 3, 5, 7, ?" to 11
    )
    private var currentPatternIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get level data from intent
        level = intent.getParcelableExtra("level") ?: throw IllegalArgumentException("Level data is required")

        setupViews()
        setupGame()
    }

    private fun setupViews() {
        toolbar = findViewById(R.id.toolbar)
        tvLevelTitle = findViewById(R.id.tvLevelTitle)
        tvLevelDescription = findViewById(R.id.tvLevelDescription)
        btnCheck = findViewById(R.id.btnCheck)
        btnHint = findViewById(R.id.btnHint)
        etAnswer = findViewById(R.id.etAnswer)
        tvPattern = findViewById(R.id.tvPattern)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        tvLevelTitle.text = "Level ${level.id}: ${level.name}"
        tvLevelDescription.text = level.description

        btnCheck.setOnClickListener {
            checkSolution()
        }

        btnHint.setOnClickListener {
            showHint()
        }
    }

    private fun setupGame() {
        when (level.id) {
            1 -> setupLevel1()
            2 -> setupLevel2()
            3 -> setupLevel3()
            4 -> setupLevel4()
            5 -> setupLevel5()
            else -> throw IllegalArgumentException("Unknown level ID: ${level.id}")
        }
    }

    private fun setupLevel1() {
        val puzzleContainer = findViewById<View>(R.id.puzzleContainer)
        val level1Container = findViewById<View>(R.id.level1Container)
        
        puzzleContainer.visibility = View.VISIBLE
        level1Container.visibility = View.VISIBLE

        // Set initial pattern
        updatePattern()
    }

    private fun updatePattern() {
        val (pattern, _) = patterns[currentPatternIndex]
        tvPattern.text = pattern
    }

    private fun checkSolution() {
        val answer = etAnswer.text.toString().toIntOrNull()
        if (answer == null) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val (_, correctAnswer) = patterns[currentPatternIndex]
        if (answer == correctAnswer) {
            if (currentPatternIndex < patterns.size - 1) {
                currentPatternIndex++
                updatePattern()
                etAnswer.text?.clear()
                Toast.makeText(this, "Correct! Try the next pattern", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Congratulations! You completed the level!", Toast.LENGTH_LONG).show()
                completeLevel()
            }
        } else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showHint() {
        val hints = listOf(
            "Look at the difference between consecutive numbers",
            "The pattern increases by the same amount each time",
            "Try adding the same number to get the next number"
        )
        Toast.makeText(this, hints.random(), Toast.LENGTH_LONG).show()
    }

    private fun setupLevel2() {
        // TODO: Implement level 2
    }

    private fun setupLevel3() {
        // TODO: Implement level 3
    }

    private fun setupLevel4() {
        // TODO: Implement level 4
    }

    private fun setupLevel5() {
        // TODO: Implement level 5
    }

    private fun completeLevel() {
        // TODO: Save progress to Firebase
        setResult(RESULT_OK)
        finish()
    }
} 