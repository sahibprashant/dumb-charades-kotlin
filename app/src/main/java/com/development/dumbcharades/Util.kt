package com.development.dumbcharades

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.development.dumbcharades.db.Word
import java.lang.StringBuilder

fun formatWords(words : List<Word>, resources: Resources) : Spanned{
    val sb = StringBuilder()

    sb.apply {
        sb.append(resources.getString(R.string.heading_words_list))
        sb.append("<br>")
        if (words.isNotEmpty()) {
            var index = 1
            words.forEach {
                sb.append("<b>${index++}</b>\t:\t${it.word}<br>")
            }
        }else{
            sb.append("No words added, Please add words to play.")
        }
    }

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    }else{
        HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}