package com.krp.gitagpt

import android.graphics.Bitmap

/**
 * Created by KUSHAL RAJ PAREEK on 21,January,2025
 */
sealed class ChatUiEvent {
    data class UpdatePrompt(val newPrompt: String) : ChatUiEvent()
    data class SendPrompt(val prompt: String, val bitmap: Bitmap?) : ChatUiEvent()
}