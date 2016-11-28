package moe.tlaster.openween.core.api.friendships

import java.util.HashMap

import moe.tlaster.openween.core.api.Constants
import moe.tlaster.openween.core.model.user.UserListModel
import moe.tlaster.openween.core.model.user.UserModel
import moe.tlaster.openween.common.helpers.HttpHelper
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException
import moe.tlaster.openween.common.helpers.JsonCallback

/**
 * Created by Tlaster on 2016/9/7.
 */
object Friends {
    private fun <T> getUsers(uid: T, count: Int, cursor: Int, url: String, callback: JsonCallback<UserListModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("cursor", cursor.toString())
        HttpHelper.getAsync(url, param, callback)
    }

    fun getFriends(uid: Long, count: Int, cursor: Int, callback: JsonCallback<UserListModel>) {
        getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FRIENDS, callback)
    }

    fun getFriends(screen_name: String, count: Int, cursor: Int, callback: JsonCallback<UserListModel>) {
        getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FRIENDS, callback)
    }

    fun getFriendsInCommon(uid: Long, suid: Long, count: Int, page: Int, callback: JsonCallback<UserListModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        if (suid != -1L) param.put("suid", suid.toString())
        HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_IN_COMMON, param, callback)
    }

    fun getBliateral(uid: Long, count: Int, page: Int, sort: Int, callback: JsonCallback<UserListModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        param.put("count", count.toString())
        param.put("page", page.toString())
        param.put("sort", sort.toString())
        HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_BILATERAL, param, callback)
    }

    fun getFollowers(uid: Long, count: Int, cursor: Int, callback: JsonCallback<UserListModel>) {
        getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS, callback)
    }

    fun getFollowers(screen_name: String, count: Int, cursor: Int, callback: JsonCallback<UserListModel>) {
        getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS, callback)
    }

    fun follow(uid: Long, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param, callback)
    }

    fun follow(screen_name: String, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("screen_name", screen_name)
        HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param, callback)
    }

    fun unfollow(uid: Long, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("uid", uid.toString())
        HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param, callback)
    }

    fun unfollow(screen_name: String, callback: JsonCallback<UserModel>) {
        val param = HashMap<String, String>()
        param.put("screen_name", screen_name)
        HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param, callback)
    }
}
