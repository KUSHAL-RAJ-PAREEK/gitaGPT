package com.krp.gitagpt.data

import android.graphics.Bitmap

/**
 * Created by KUSHAL RAJ PAREEK on 21,January,2025
 */
data class Chat(
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)
