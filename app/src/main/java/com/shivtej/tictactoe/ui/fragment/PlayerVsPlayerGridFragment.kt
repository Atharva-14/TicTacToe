package com.shivtej.tictactoe.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.shivtej.tictactoe.R
import com.shivtej.tictactoe.databinding.FragmentPlayerVsPlayerGridBinding

class PlayerVsPlayerGridFragment : Fragment(R.layout.fragment_player_vs_player_grid) {

    private lateinit var binding: FragmentPlayerVsPlayerGridBinding
    private var activePlayer = 0
    private var gameActive = true
    private var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )
    private var counter = 0
    lateinit var player1: String
    lateinit var player2: String
    private var p1count: Int = 0
    var p2count: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayerVsPlayerGridBinding.bind(view)

        val bundle = arguments
        player1 = bundle?.getString("playe1").toString()
        Log.e("play1", player1)

        val bundle2 = arguments
        player2 = bundle2?.getString("playe2").toString()
        Log.e("play2", player2)

        binding.tvPlayer1.text = player1
        binding.tvPlayer2.text = player2
        resetGame()

        binding.btnImg1.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg2.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg3.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg4.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg5.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg6.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg7.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg8.setOnClickListener {
            playerTap(it)
        }
        binding.btnImg9.setOnClickListener {
            playerTap(it)
        }

        binding.btnReset.setOnClickListener {
            resetGame()
        }
    }

    fun playerTap(view: View) {
        val img: ImageView = view as ImageView
        val tappedImage: Int = img.tag.toString().toInt()

        if (gameState[tappedImage] == 2) {
            counter++

            if (counter == 9) {
                gameActive = false
            }

            gameState[tappedImage] = activePlayer

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.circle_yellow)
                activePlayer = 1
                //Toast.makeText(this, "${player2}'s turn - Tap to play", Toast.LENGTH_SHORT).show()
                binding.tvInfo.text = "${player2}'s turn - Tap to play"
            } else {
                img.setImageResource(R.drawable.cross_yellow)
                activePlayer = 0
                //Toast.makeText(this, "$player1's turn - Tap to play", Toast.LENGTH_SHORT).show()
                binding.tvInfo.text = "${player1}'s turn - Tap to play"
            }
        }

        var flag = 0
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]
                && gameState[winPosition[0]] != 2
            ) {
                flag = 1
                var winner = ""
                gameActive = false
                if (gameState[winPosition[0]] == 0) {
                    winner = "$player1 has won"
                    p1count++
                    binding.tvPlayer1Score.text = p1count.toString()
                } else {
                    winner = "${player2} has won"
                    p2count++
                    binding.tvPlayer2Score.text = p2count.toString()
                }
                Toast.makeText(activity, winner, Toast.LENGTH_SHORT).show()
                binding.tvInfo.text = winner
                binding.btnImg1.isEnabled = false
                binding.btnImg2.isEnabled = false
                binding.btnImg3.isEnabled = false
                binding.btnImg4.isEnabled = false
                binding.btnImg5.isEnabled = false
                binding.btnImg6.isEnabled = false
                binding.btnImg7.isEnabled = false
                binding.btnImg8.isEnabled = false
                binding.btnImg9.isEnabled = false
            }
        }
        if (counter == 9 && flag == 0) {
            Toast.makeText(activity, "Match Draw", Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetGame() {
        gameActive = true
        activePlayer = 0
        for (i in gameState.indices) {
            gameState[i] = 2
        }

        binding.btnImg1.setImageResource(0)
        binding.btnImg2.setImageResource(0)
        binding.btnImg3.setImageResource(0)
        binding.btnImg4.setImageResource(0)
        binding.btnImg5.setImageResource(0)
        binding.btnImg6.setImageResource(0)
        binding.btnImg7.setImageResource(0)
        binding.btnImg8.setImageResource(0)
        binding.btnImg9.setImageResource(0)
        binding.btnImg1.isEnabled = true
        binding.btnImg2.isEnabled = true
        binding.btnImg3.isEnabled = true
        binding.btnImg4.isEnabled = true
        binding.btnImg5.isEnabled = true
        binding.btnImg6.isEnabled = true
        binding.btnImg7.isEnabled = true
        binding.btnImg8.isEnabled = true
        binding.btnImg9.isEnabled = true
    }
}