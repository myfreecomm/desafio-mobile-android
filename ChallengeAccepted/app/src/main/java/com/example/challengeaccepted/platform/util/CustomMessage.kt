package com.example.challengeaccepted.platform.util

import androidx.annotation.IntDef
import androidx.annotation.StringRes
import com.example.challengeaccepted.R

class CustomMessage(
    @MessageStatus val messageStatus: Int,
    @StringRes val message: Int
) {
    companion object {
        fun error(message: Int?): CustomMessage {
            return CustomMessage(ERROR, message ?: R.string.error_generic)
        }

        fun warning(message: Int): CustomMessage {
            return CustomMessage(ERROR, message)
        }

        fun success(message: Int): CustomMessage {
            return CustomMessage(ERROR, message)
        }

        @IntDef(ERROR, WARNING, SUCCESS)
        @Retention(AnnotationRetention.SOURCE)
        annotation class MessageStatus

        const val ERROR = -1
        const val WARNING = 0
        const val SUCCESS = 1
    }
}