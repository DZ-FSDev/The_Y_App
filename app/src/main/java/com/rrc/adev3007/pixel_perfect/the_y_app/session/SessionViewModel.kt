package com.rrc.adev3007.pixel_perfect.the_y_app.session

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rrc.adev3007.pixel_perfect.the_y_app.components.ScalingLevel

class SessionViewModel(context: Context) : ViewModel() {
    private val session: Session = Session.getInstance(context)!!

    val username: State<String> = mutableStateOf(session.getString("username", "undefined")!!)
    val darkMode: State<Boolean> = mutableStateOf(session.getBoolean("darkMode", false))
    val autoplay: State<Boolean> = mutableStateOf(session.getBoolean("autoplay", false))
    val profanityFilter: State<Boolean> = mutableStateOf(session.getBoolean("profanityFilter", false))
    val scale: State<ScalingLevel> = mutableStateOf(
        ScalingLevel.valueOf(
            session.getString("scale", ScalingLevel.Normal.toString())
                ?: ScalingLevel.Normal.toString()
        )
    )

    fun updateUsername(newUsername: String) {
        session.putString("username", newUsername)
        (username as MutableState<String>).value = newUsername
    }

    fun toggleDarkMode() {
        val currentDarkMode = darkMode.value
        session.putBoolean("darkMode", !currentDarkMode)
        (darkMode as MutableState<Boolean>).value = !currentDarkMode
    }

    fun toggleAutoplay() {
        val currentAutoplay = autoplay.value
        session.putBoolean("autoplay", !currentAutoplay)
        (autoplay as MutableState<Boolean>).value = !currentAutoplay
    }

    fun toggleProfanityFilter() {
        val currentProfanityFilter = profanityFilter.value
        session.putBoolean("profanityFilter", !currentProfanityFilter)
        (profanityFilter as MutableState<Boolean>).value = !currentProfanityFilter
    }

    fun updateScale(newScale: ScalingLevel) {
        session.putString("scale", newScale.toString())
        (scale as MutableState<ScalingLevel>).value = newScale
    }
}
