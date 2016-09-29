package moe.tlaster.openween.core.api.blocks;

import java.util.HashMap;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.block.BlockListModel;
import moe.tlaster.openween.core.model.user.UserModel;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/5.
 */
public class Blocks {
    public static void getBlocksList(int count, int page, JsonCallback<BlockListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        HttpHelper.getAsync(Constants.BLOCKS_LIST, param, callback);
    }
    public static void getBlocksList(JsonCallback<BlockListModel> callback) {
        getBlocksList(50, 1, callback);
    }
    public static void addBlock(long uid, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        HttpHelper.postAsync(Constants.BLOCKS_CREATE, param, callback);
    }
    public static void removeBlock(long uid, JsonCallback<UserModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        HttpHelper.postAsync(Constants.BLOCKS_DESTROY, param, callback);
    }
    public static void isBlocked(long uid, boolean invert, JsonCallback<String> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("uid", String.valueOf(uid));
        param.put("invert", invert? "0" : "1");
        HttpHelper.getAsync(Constants.BLOCKS_EXISTS, param, callback);
    }
    public static void isBlocked(long uid, JsonCallback<String> callback) {
        isBlocked(uid, false, callback);
    }

}
