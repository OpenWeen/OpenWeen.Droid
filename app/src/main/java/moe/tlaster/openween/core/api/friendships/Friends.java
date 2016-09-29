package moe.tlaster.openween.core.api.friendships;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.user.UserListModel;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/7.
 */
public class Friends {
    private static <T> void getUsers(T uid, int count, int cursor, String url, JsonCallback<UserListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("count", String.valueOf(count));
        param.put("cursor", String.valueOf(cursor));
        HttpHelper.getAsync(url, param, callback);
    }
    public static void getFriends(long uid, int count, int cursor, JsonCallback<UserListModel> callback) {
        getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FRIENDS, callback);
    }
    public static void getFriends(String screen_name, int count, int cursor, JsonCallback<UserListModel> callback) {
        getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FRIENDS, callback);
    }
    public static void getFriendsInCommon(long uid, long suid, int count, int page, JsonCallback<UserListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        if (suid != -1) param.put("suid", String.valueOf(suid));
        HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_IN_COMMON, param, callback);
    }
    public static void getBliateral(long uid, int count, int page, int sort, JsonCallback<UserListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("sort", String.valueOf(sort));
        HttpHelper.getAsync(Constants.FRIENDSHIPS_FRIENDS_BILATERAL, param, callback);
    }
    public static void getFollowers(long uid, int count, int cursor, JsonCallback<UserListModel> callback) {
        getUsers(uid, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS, callback);
    }
    public static void getFollowers(String screen_name, int count, int cursor, JsonCallback<UserListModel> callback) {
        getUsers(screen_name, count, cursor, Constants.FRIENDSHIPS_FOLLOWERS, callback);
    }
    public static void follow(long uid, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param, callback);
    }
    public static void follow(String screen_name, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("screen_name", String.valueOf(screen_name));
        HttpHelper.postAsync(Constants.FRIENDSHIPS_CREATE, param, callback);
    }
    public static void unfollow(long uid, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param, callback);
    }
    public static void unfollow(String screen_name, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("screen_name", String.valueOf(screen_name));
        HttpHelper.postAsync(Constants.FRIENDSHIPS_DESTROY, param, callback);
    }
}
