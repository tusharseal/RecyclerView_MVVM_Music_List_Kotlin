package com.example.rvmvvm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var question1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        onClicks()
    }

    private fun onClicks() {
        question1.setOnClickListener {
            val intent = Intent(this, QuestionActivity1::class.java)
            startActivity(intent)
        }
    }

    private fun initialize() {
        question1 = findViewById(R.id.question_one)
    }
}