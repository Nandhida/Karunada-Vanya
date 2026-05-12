package com.example.karunada_vanya

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SoundsFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentBtn: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sounds, container, false)

        val btnElephant = view.findViewById<TextView>(R.id.btnElephant)
        val btnTiger = view.findViewById<TextView>(R.id.btnTiger)
        val btnPeacock = view.findViewById<TextView>(R.id.btnPeacock)
        val btnHornbill = view.findViewById<TextView>(R.id.btnHornbill)
        val btnJungle = view.findViewById<TextView>(R.id.btnJungle)

        btnElephant.setOnClickListener { playSound(R.raw.elephant, btnElephant) }
        btnTiger.setOnClickListener { playSound(R.raw.tiger, btnTiger) }
        btnPeacock.setOnClickListener { playSound(R.raw.peacock, btnPeacock) }
        btnHornbill.setOnClickListener { playSound(R.raw.hornbill, btnHornbill) }
        btnJungle.setOnClickListener { playSound(R.raw.jungle, btnJungle) }

        return view
    }

    private fun playSound(soundRes: Int, btn: TextView) {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                currentBtn?.text = "▶"
                if (currentBtn == btn) {
                    currentBtn = null
                    return
                }
            }

            mediaPlayer = MediaPlayer.create(requireContext(), soundRes)
            mediaPlayer?.start()
            btn.text = "⏹"
            currentBtn = btn

            mediaPlayer?.setOnCompletionListener {
                btn.text = "▶"
                currentBtn = null
                mediaPlayer?.release()
                mediaPlayer = null
            }

        } catch (e: Exception) {
            Toast.makeText(context, "Sound file not found!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}