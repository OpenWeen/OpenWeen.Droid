package moe.tlaster.openween.core.model.status

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by Asahi on 2016/10/8.
 */

class MediaModel(fid: String) {

    @SerializedName("bypass")
    private val byPass = "unistore.image"
    @SerializedName("createtype")
    private val createType = "localfile"
    @SerializedName("filterName")
    private val filterName = ""
    @SerializedName("stickerID")
    private val stickerID = ""
    @SerializedName("fid")
    private var fid = ""
    @SerializedName("type")
    private val type = "pic"
    @SerializedName("filterID")
    private val filterID = ""
    @SerializedName("picStatus")
    private val picStatus = 0

    init {
        this.fid = fid
    }
    override fun toString(): String {
        return "[" + Gson().toJson(this) + "]"
    }
}
