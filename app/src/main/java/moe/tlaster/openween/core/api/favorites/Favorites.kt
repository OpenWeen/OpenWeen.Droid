package moe.tlaster.openween.core.api.favorites

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.favor.FavorListModel
import moe.tlaster.openween.core.model.favor.FavorModel
import moe.tlaster.openween.core.model.favor.FavorTagListModel
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/6.
 */
object Favorites {
    fun getFavorList(count: Int = 20, page: Int = 1) : FavorListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        return HttpHelper.getAsync(Constants.FAVORITES_LIST, param)
    }

    fun getFavor(id: Long) : FavorModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        return HttpHelper.getAsync(Constants.FAVORITES_SHOW, param)
    }

    fun getFavorListByTag(tid: Long, count: Int, page: Int) : FavorListModel {
        val param = ArrayMap<String, String>()
        param.put("tid", tid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        return HttpHelper.getAsync(Constants.FAVORITES_LIST_BY_TAG, param)
    }

    fun getTags(count: Int, page: Int) : FavorTagListModel {
        val param = ArrayMap<String, String>()
        param.put("count", count.toString())
        param.put("page", page.toString())
        return HttpHelper.getAsync(Constants.FAVORITES_TAGS, param)
    }

    fun addFavor(id: Long) : FavorModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        return HttpHelper.postAsync(Constants.FAVORITES_CREATE, param)
    }

    fun removeFavor(id: Long) : FavorModel {
        val param = ArrayMap<String, String>()
        param.put("id", id.toString())
        return HttpHelper.postAsync(Constants.FAVORITES_DESTROY, param)
    }
}
