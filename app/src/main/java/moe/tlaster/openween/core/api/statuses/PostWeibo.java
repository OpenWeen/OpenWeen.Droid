package moe.tlaster.openween.core.api.statuses;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.status.MediaModel;
import moe.tlaster.openween.core.model.status.MessageModel;
import moe.tlaster.openween.core.model.status.PictureModel;
import moe.tlaster.openween.core.model.types.RepostType;
import moe.tlaster.openween.core.model.types.WeiboVisibility;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/8.
 */
public class PostWeibo {
    public static void post(String status, WeiboVisibility visible, String list_id, float plat, float plong, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("status", status);
        param.put("visible", String.valueOf(visible.getValue()));
        param.put("lat", String.valueOf(plat));
        param.put("long", String.valueOf(plong));
        param = checkForVisibility(visible, list_id, param);
        HttpHelper.postAsync(Constants.UPDATE, param, callback);
    }
    public static void post(String status, JsonCallback<MessageModel> callback) {
        post(status, WeiboVisibility.All, "", 0, 0, callback);
    }
    public static void postWithPic(String status, File pic, WeiboVisibility visible, String list_id, float plat, float plong, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("status", status);
        param.put("visible", String.valueOf(visible.getValue()));
        param.put("lat", String.valueOf(plat));
        param.put("long", String.valueOf(plong));
        param = checkForVisibility(visible, list_id, param);
        HttpHelper.uploadFileWithParamAsync(Constants.UPLOAD, pic, param, callback);
    }
    public static void repost(long id, String status, RepostType is_comment, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("status", TextUtils.isEmpty(status) ? "转发微博" : status);
        param.put("id", String.valueOf(id));
        param.put("is_comment", String.valueOf(is_comment.getValue()));
        HttpHelper.postAsync(Constants.REPOST, param, callback);
    }

    public static void repostWithPic(long id, String status, String pid, RepostType is_comment, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("status", TextUtils.isEmpty(status) ? "转发微博" : status);
        param.put("id", String.valueOf(id));
        param.put("is_comment", String.valueOf(is_comment.getValue()));
        param.put("media", new MediaModel(pid).toString());
        param.put("source", "211160679");
        param.put("from", "1055095010");
        HttpHelper.postAsync("https://api.weibo.cn/2/statuses/repost", param, callback);
    }
    public static void deletePost(long id, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.DESTROY, param, callback);
    }
    public static void uploadPicture(File file, JsonCallback<PictureModel> callback) {
        HttpHelper.uploadFileAsync(Constants.UPLOAD_PIC, file, callback);
    }
    public static void postWithMultiPics(String status, String pics, WeiboVisibility visible, String list_id, float plat, float plong, JsonCallback<MessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("status", status);
        param.put("pic_id", pics);
        param.put("visible", String.valueOf(visible.getValue()));
        param.put("lat", String.valueOf(plat));
        param.put("long", String.valueOf(plong));
        param = checkForVisibility(visible, list_id, param);
        HttpHelper.postAsync(Constants.UPLOAD_URL_TEXT, param, callback);
    }
    public static void postWithMultiPics(String status, String pics, JsonCallback<MessageModel> callback) {
        postWithMultiPics(status, pics, WeiboVisibility.All, "", 0 ,0, callback);
    }
    @Nullable
    private static Map<String, String> checkForVisibility(WeiboVisibility visible, String list_id, Map<String, String> param){
        if (visible == WeiboVisibility.SpecifiedGroup)
            if(!TextUtils.isEmpty(list_id))
                param.put("list_id", list_id);
        return param;
    }
}
