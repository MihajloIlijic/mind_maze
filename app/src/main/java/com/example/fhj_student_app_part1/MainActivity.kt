package com.example.fhj_student_app_part1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhj_student_app_part1.adapter.LevelAdapter
import com.example.fhj_student_app_part1.model.Level
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var levelsRecyclerView: RecyclerView
    private lateinit var fabProfile: FloatingActionButton

    companion object {
        private const val REQUEST_LEVEL_COMPLETE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setupViews()
        setupLevels()
    }

    private fun setupViews() {
        levelsRecyclerView = findViewById(R.id.levelsRecyclerView)
        fabProfile = findViewById(R.id.fabProfile)

        levelsRecyclerView.layoutManager = LinearLayoutManager(this)
        
        fabProfile.setOnClickListener {
            // TODO: Implement profile view
            Toast.makeText(this, "Profile coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupLevels() {
        // TODO: Replace with actual level data from Firebase
        val levels = listOf(
            Level(1, "The Beginning", "Find the pattern in the sequence", false, false),
            Level(2, "First Challenge", "Test your skills", true, false),
            Level(3, "The Maze", "Navigate through complexity", true, false),
            Level(4, "Logic Gates", "Solve the puzzle", true, false),
            Level(5, "Pattern Recognition", "Find the sequence", true, false)
        )

        levelsRecyclerView.adapter = LevelAdapter(levels) { level ->
            if (!level.isLocked) {
                startLevel(level)
            } else {
                Toast.makeText(this, "Complete previous levels to unlock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startLevel(level: Level) {
        val intent = Intent(this, LevelActivity::class.java).apply {
            putExtra("level", level)
        }
        startActivityForResult(intent, REQUEST_LEVEL_COMPLETE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LEVEL_COMPLETE && resultCode == RESULT_OK) {
            // TODO: Update level completion status in Firebase
            Toast.makeText(this, "Level completed!", Toast.LENGTH_SHORT).show()
        }
    }
}