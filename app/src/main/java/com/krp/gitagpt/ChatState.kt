package com.krp.gitagpt

import android.graphics.Bitmap
import com.krp.gitagpt.data.Chat

/**
 * Created by KUSHAL RAJ PAREEK on 21,January,2025
 */
data class ChatState (
    val chatList : MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)