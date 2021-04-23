package com.shivtej.tictactoe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.InterstitialAd
import com.shivtej.tictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val navController =findNavController(R.id.nav_host_fragment)
    }
}