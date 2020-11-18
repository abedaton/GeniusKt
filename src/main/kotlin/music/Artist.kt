package music

import com.beust.klaxon.JsonObject

class Artist constructor(jroot: JsonObject){
    val id: String = jroot["id"] as String
    val imageUrl: String = jroot["image_url"] as String
}