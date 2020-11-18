package genius

import com.beust.klaxon.Json
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import jdk.nashorn.internal.parser.JSONParser
import managers.HttpManager
import java.io.StringReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.properties.Delegates

class SearchSong constructor(private val genius: Genius, song: String, page: Int = 1){
    private val geniusApi: Genius = genius
    private val requestedSong: String = song
    private val thePage: Int = page
    private var status: Int = -1
    private var nextPage: Int = -1
    private var hits: LinkedList<JsonObject> = TODO()

    init {
        val query: String = URLEncoder.encode(requestedSong, StandardCharsets.UTF_8.toString())
        val uri: URI = URI("https://genius.com/api/search/song?page=$thePage&q=$query")
        request(uri)
    }


    private fun request(uri: URI){
        val con: HttpURLConnection = geniusApi.getHttpManager().getConnection(uri.toURL())
        val result: String? = geniusApi.getHttpManager().get(con)
        if (result != null) {
            val klaxon: Klaxon = Klaxon()
            parse(klaxon.parseJsonObject(StringReader(result.toString())))
        }
    }

    private fun parse(jroot: JsonObject){
        this.status = (jroot["meta"] as JsonObject)["status"] as Int
        val response: JsonObject = jroot["response"] as JsonObject
        val tmpResponse: Int? = response["next_page"] as Int
        if (tmpResponse != null) {
            this.nextPage = tmpResponse
        }
        val section: JsonObject = (response["sections"] as JsonArray<*>)[0] as JsonObject
        val hits = section["hits"] as JsonArray<*>

        for (hit in 1..hits.size){
            val hitRoot: JsonObject = (hits[hit] as JsonObject)["result"] as JsonObject

        }
    }
}
