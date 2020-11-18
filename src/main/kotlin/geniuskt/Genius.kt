package genius

import managers.HttpManager

class Genius {
    private val httpManager: HttpManager = HttpManager()


    fun searchSong(song: String){
        val search: SearchSong = SearchSong(this, song)
    }

    fun getHttpManager(): HttpManager {
        return httpManager
    }
}