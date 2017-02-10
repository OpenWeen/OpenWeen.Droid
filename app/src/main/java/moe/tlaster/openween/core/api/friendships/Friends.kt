package moe.tlaster.openween.core.api.friendships

import android.support.v4.util.ArrayMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.user.UserListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper

/**
 * Created by Tlaster on 2016/9/7.
 */
object Friends {
    private fun <T> getUsers(uid: T, count: Int, cursor: Int, url: String) : UserListModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("cursor", cursor.toString())
        return HttpHelper.getAsync(url, param)
    }

    fun getFriends(uid: Long, count: Int, cursor: Int) : UserListModel {
        return getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FRIENDS)
    }

    fun getFriends(screen_name: String, count: Int, cursor: Int) : UserListModel {
        return getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FRIENDS)
    }

    fun getFriendsInCommon(uid: Long, suid: Long, count: Int, page: Int) : UserListModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        if (suid != -1L) param.put("suid", suid.toString())
        return HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_IN_COMMON, param)
    }

    fun getBliateral(uid: Long, count: Int, page: Int, sort: Int) : UserListModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("sort", sort.toString())
        return HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_BILATERAL, param)
    }

    fun getFollowers(uid: Long, count: Int, cursor: Int) : UserListModel {
        return getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS)
    }

    fun getFollowers(screen_name: String, count: Int, cursor: Int) : UserListModel {
        return getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS)
    }

    fun follow(uid: Long) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        return HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param)
    }

    fun follow(screen_name: String) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("screen_name", screen_name)
        return HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param)
    }

    fun unfollow(uid: Long) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("uid", uid.toString())
        return HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param)
    }

    fun unfollow(screen_name: String) : UserModel {
        val param = ArrayMap<String, String>()
        param.put("screen_name", screen_name)
        return HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param)
    }
}
