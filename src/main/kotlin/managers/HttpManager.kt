package managers

import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpManager {

    fun getConnection(url: URL): HttpURLConnection{
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.setRequestProperty("User-Agent", Consts.Companion.USER_AGENT)
        con.setRequestProperty("accept-language", "en-US")
        con.setRequestProperty("Content-Type", "application/json")
        con.setRequestProperty("Accept", "application/json")
        return con
    }

    fun get(con: HttpURLConnection): String?{
        con.requestMethod = "GET"
        return con.inputStream.bufferedReader().use(BufferedReader::readText)
    }
}