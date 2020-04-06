package com.globo.raphaelbgr.desafio.brasileirao.util

import java.text.SimpleDateFormat
import java.util.*

class BrasileiraoUtil {

    companion object {

        fun parseDate(date: Date?): String? {
            return if (date != null) SimpleDateFormat(
                "dd/MM/yyyy - HH:mm",
                Locale.getDefault()
            ).format(date)
            else {
                null
            }
        }
    }
}