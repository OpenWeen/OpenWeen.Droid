package moe.tlaster.openween.core.api.directMessage;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.directmessage.DirectMessageListModel;
import moe.tlaster.openween.core.model.directmessage.DirectMessageModel;
import moe.tlaster.openween.core.model.directmessage.DirectMessageUserListModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/6.
 */
public class DirectMessage {
    public static void getUserList(int count, int cursor, JsonCallback<DirectMessageUserListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("cursor", String.valueOf(cursor));
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES_USER_LIST, param, callback);
    }
    public static void getUserList(JsonCallback<DirectMessageUserListModel> callback) {
        getUserList(20, 0, callback);
    }
    public static void getConversation(String uid, long since_id, long max_id, int count, int page, JsonCallback<DirectMessageListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", uid);
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES_CONVERSATION, param, callback);
    }
    public static void getConversation(String uid, int count, int page, JsonCallback<DirectMessageListModel> callback) {
        getConversation(uid, 0, 0, count, page, callback);
    }
    public static void getDirectMessages(long since_id, long max_id, int count, int page, JsonCallback<DirectMessageListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        HttpHelper.getAsync(Constants.DIRECT_MESSAGES, param, callback);
    }
    public static void getDirectMessages(int count, int page, JsonCallback<DirectMessageListModel> callback) {
        getDirectMessages(0, 0, 20, 1, callback);
    }
    public static void send(long uid, String text, long id, JsonCallback<DirectMessageModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("text", text);
        if (id > 0) param.put("id", String.valueOf(id));
        HttpHelper.postAsync(Constants.DIRECT_MESSAGES_SEND, param, callback);
    }
    public static void send(long uid, String text, JsonCallback<DirectMessageModel> callback) {
        send(uid, text, -1, callback);
    }
}
