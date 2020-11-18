package geniuskt

import com.beust.klaxon.JsonObject

class Hit constructor(jroot: JsonObject){
    val id: Long = jroot["id"] as Long
    val title: String = jroot["title"] as String
    val titleWithFeatured: String = jroot["title_with_features"] as String
    val url: String = jroot["url"] as String
    val imageUrl: String = jroot["header_image_url"] as String
    val thumbnailUrl: String = jroot["song_art_image_thumbnail_url"] as String

}