package me.yusufaw.nyegerake

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.yusufaw.nyegerake.model.Phrase
import me.yusufaw.nyegerake.ui.theme.NyegerakeTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val xxx = getCountryCode(this)
        print("xxx" + xxx[0].meaning)
        Log.d("xxx", xxx[0].meaning)
        setContent {
            NyegerakeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NyegerakeTheme {
        Greeting("Android")
    }
}

fun getCountryCode(context: Context): List<Phrase> {

    lateinit var jsonString: String
    try {
        jsonString = context.assets.open("phrases.json")
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {
        print("xxx" + ioException)
    }

    val listCountryType = object : TypeToken<List<Phrase>>() {}
    return Gson().fromJson(jsonString, listCountryType)
}
//here Gson() is basically providing fromJson methods which actually //deserializing json. It basically parse json string to //list<Country> object
//this getCountryCode(ctx: Context) will return a list of Country data class.