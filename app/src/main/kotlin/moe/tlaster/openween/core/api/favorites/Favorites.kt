package moe.tlaster.openween.core.api.favorites

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.favor.FavorListModel
import moe.tlaster.openween.core.model.favor.FavorModel
import moe.tlaster.openween.core.model.favor.FavorTagListModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/6.
 */
object Favorites {
    fun getFavorList(count: Int = 20, page: Int = 1, callback: JsonCallback<FavorListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        HttpHelper.getAsync(Constants.FAVORITES_LIST, param, callback)
    }

    fun getFavor(id: Long, callback: JsonCallback<FavorModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        HttpHelper.getAsync(Constants.FAVORITES_SHOW, param, callback)
    }

    fun getFavorListByTag(tid: Long, count: Int, page: Int, callback: JsonCallback<FavorListModel>) {
        val param = HashMap<String, String>()
        param.put("tid", tid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        HttpHelper.getAsync(Constants.FAVORITES_LIST_BY_TAG, param, callback)
    }

    fun getTags(count: Int, page: Int, callback: JsonCallback<FavorTagListModel>) {
        val param = HashMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        HttpHelper.getAsync(Constants.FAVORITES_TAGS, param, callback)
    }

    fun addFavor(id: Long, callback: JsonCallback<FavorModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        HttpHelper.postAsync(Constants.FAVORITES_CREATE, param, callback)
    }

    fun removeFavor(id: Long, callback: JsonCallback<FavorModel>) {
        val param = HashMap<String, String>()
        param.put("id", id.toString())
        HttpHelper.postAsync(Constants.FAVORITES_DESTROY, param, callback)
    }
}
