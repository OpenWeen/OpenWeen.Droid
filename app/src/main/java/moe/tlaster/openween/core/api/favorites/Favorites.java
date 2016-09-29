package moe.tlaster.openween.core.api.favorites;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.favor.FavorListModel;
import moe.tlaster.openween.core.model.favor.FavorModel;
import moe.tlaster.openween.core.model.favor.FavorTagListModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/6.
 */
public class Favorites {
    public static void getFavorList(int count, int page, JsonCallback<FavorListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        HttpHelper.getAsync(Constants.FAVORITES_LIST, param, callback);
    }
    public static void getFavor(long id, JsonCallback<FavorModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        HttpHelper.getAsync(Constants.FAVORITES_SHOW, param, callback);
    }
    public static void getFavorListByTag(long tid, int count, int page, JsonCallback<FavorListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("tid", String.valueOf(tid));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        HttpHelper.getAsync(Constants.FAVORITES_LIST_BY_TAG, param, callback);
    }
    public static void getTags(int count, int page, JsonCallback<FavorTagListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        HttpHelper.getAsync(Constants.FAVORITES_TAGS, param, callback);
    }
    public static void addFavor(long id, JsonCallback<FavorModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.FAVORITES_CREATE, param, callback);
    }
    public static void removeFavor(long id, JsonCallback<FavorModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.FAVORITES_DESTROY, param, callback);
    }
}
