package com.example.quizzo.sound

import android.content.Context
import android.media.MediaPlayer
import com.example.quizzo.R

class SoundPlayer(private val soundType: SoundType, private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound() {
        val soundResId = when (soundType) {
            SoundType.BUTTON_SOUND -> R.raw.button_sound
            SoundType.MiddleSound -> R.raw.button_sound
            SoundType.RequestSound -> R.raw.button_sound
        }

        mediaPlayer = MediaPlayer.create(context, soundResId)
        mediaPlayer?.isLooping = false
        mediaPlayer?.start()
    }

    fun playRequestSound(){
        val soundResId = R.raw.button_sound
        mediaPlayer = MediaPlayer.create(context, soundResId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun stopSound() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    enum class SoundType {
        BUTTON_SOUND,
        MiddleSound,
        RequestSound
    }


}