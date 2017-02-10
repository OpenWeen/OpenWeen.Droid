package moe.tlaster.openween.core.model.status

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper


/**
 * Created by Asahi on 2016/10/8.
 */

class MediaModel(fid: String) {

    @field:JsonProperty("bypass")
    private val byPass = "unistore.image"
    @field:JsonProperty("createtype")
    private val createType = "localfile"
    @field:JsonProperty("filterName")
    private val filterName = ""
    @field:JsonProperty("stickerID")
    private val stickerID = ""
    @field:JsonProperty("fid")
    private var fid = ""
    @field:JsonProperty("type")
    private val type = "pic"
    @field:JsonProperty("filterID")
    private val filterID = ""
    @field:JsonProperty("picStatus")
    private val picStatus = 0

    init {
        this.fid = fid
    }

    override fun toString(): String {
        return "[" + ObjectMapper().writeValueAsString(this) + "]"
    }
}
