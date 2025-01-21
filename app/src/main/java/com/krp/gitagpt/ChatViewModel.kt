package com.krp.gitagpt

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krp.gitagpt.data.Chat
import com.krp.gitagpt.data.ChatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by KUSHAL RAJ PAREEK on 21,January,2025
 */
class ChatViewModel : ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, event.bitmap)

                    if (event.bitmap != null) {
                        getResponseWithImage(event.prompt, event.bitmap)
                    } else {
                        getResponse(event.prompt)
                    }
                }
            }

            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }

    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, bitmap, true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            val newPrompt = "As Lord Krishna would answer to arjun, please respond to the following question in Hindi only, providing spiritual guidance along with an appropriate verse from the Bhagavad Gita in Sanskrit. After the verse, provide its Hindi translation: $prompt"

            val chat = ChatData.getResponse(newPrompt)

            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, Chat(chat.prompt, null, false))
                    }
                )
            }
        }
    }

    private fun getResponseWithImage(prompt: String, bitmap: Bitmap) {
        viewModelScope.launch {

            val newPrompt = "As Lord Krishna would answer to arjun, please respond to the following question in Hindi only, providing spiritual guidance along with an appropriate verse from the Bhagavad Gita in Sanskrit. After the verse, provide its Hindi translation: $prompt"


            val chat = ChatData.getResponseWithImage(newPrompt, bitmap)


            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, Chat(chat.prompt, null, false))
                    }
                )
            }
        }
    }
}
