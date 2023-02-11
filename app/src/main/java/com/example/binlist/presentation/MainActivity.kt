package com.example.binlist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binlist.R
import com.example.binlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            launchMainFragment()
        }
    }

    private fun launchMainFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
