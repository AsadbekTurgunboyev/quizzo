package com.example.quizzo.ui.home.play.usecases

class LivesManager {
    companion object {
        const val MAX_LIVES = 3
    }
    private var remainingLives = MAX_LIVES

    fun decrementLife() {
        if (remainingLives > 0) {
            remainingLives--
        }
    }

    fun getRemainingLives() = remainingLives
}
