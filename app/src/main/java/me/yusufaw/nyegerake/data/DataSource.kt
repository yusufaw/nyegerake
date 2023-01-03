package me.yusufaw.nyegerake.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.yusufaw.nyegerake.model.Phrase
import java.io.IOException

class DataSource {
    fun getPhrases(context: Context): List<Phrase> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("phrases.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            print("xxx" + ioException)
        }

        val phraseType = object : TypeToken<List<Phrase>>() {}
        return Gson().fromJson(jsonString, phraseType)
    }
}