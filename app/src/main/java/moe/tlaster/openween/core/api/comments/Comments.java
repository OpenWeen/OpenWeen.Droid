package moe.tlaster.openween.core.api.comments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import moe.tlaster.openween.core.api.Constants;
import moe.tlaster.openween.core.model.comment.CommentListModel;
import moe.tlaster.openween.core.model.comment.CommentModel;
import moe.tlaster.openween.core.model.status.MediaModel;
import moe.tlaster.openween.core.model.types.AuthorType;
import moe.tlaster.openween.core.model.types.SourceType;
import moe.tlaster.openween.common.helpers.HttpHelper;
import moe.tlaster.openween.common.helpers.InvalidAccessTokenException;
import moe.tlaster.openween.common.helpers.JsonCallback;

/**
 * Created by Tlaster on 2016/9/5.
 */
public class Comments {
    public static void getCommentStatus(long id, long since_id, long max_id, int count, int page, AuthorType filter_by_author, JsonCallback<CommentListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("filter_by_author", String.valueOf(filter_by_author.getValue()));
        HttpHelper.getAsync(Constants.COMMENTS_SHOW, param, callback);
    }
    public static void getCommentStatus(long id, JsonCallback<CommentListModel> callBack) {
        getCommentStatus(id, 0, 0, 20, 1, AuthorType.All, callBack);
    }
    public static void getCommentStatus(long id, int count, int page, JsonCallback<CommentListModel> callBack) {
        getCommentStatus(id, 0, 0, count, page, AuthorType.All, callBack);
    }
    public static void getCommentByMe(long since_id, long max_id, int count, int page, SourceType filter_by_source, JsonCallback<CommentListModel> callBack) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("filter_by_source", String.valueOf(filter_by_source.getValue()));
        HttpHelper.getAsync(Constants.COMMENTS_BY_ME, param, callBack);
    }
    public static void getCommentByMe(JsonCallback<CommentListModel> callBack) {
        getCommentByMe(0, 0, 50, 1, SourceType.All, callBack);
    }
    public static void getCommentByMe(int count, int page, JsonCallback<CommentListModel> callBack) {
        getCommentByMe(0, 0, count, page, SourceType.All, callBack);
    }
    public static void getCommentToMe(long since_id, long max_id, int count, int page, AuthorType filter_by_author, SourceType filter_by_source, JsonCallback<CommentListModel> callBack) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("filter_by_author", String.valueOf(filter_by_author.getValue()));
        param.put("filter_by_source", String.valueOf(filter_by_source.getValue()));
        HttpHelper.getAsync(Constants.COMMENTS_TO_ME, param, callBack);
    }
    public static void getCommentToMe(JsonCallback<CommentListModel> callback) {
        getCommentToMe(0, 0, 50, 1, AuthorType.All, SourceType.All, callback);
    }
    public static void getCommentToMe(long max_id, int count, JsonCallback<CommentListModel> callback){
        getCommentToMe(0, max_id, count, 1, AuthorType.All, SourceType.All, callback);
    }
    public static void getCommentToMe(int count, JsonCallback<CommentListModel> callback) {
        getCommentToMe(0, 0, count, 1, AuthorType.All, SourceType.All, callback);
    }
    public static void getComment(long since_id, long max_id, int count, int page, int trim_user, JsonCallback<CommentListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("trim_user", String.valueOf(trim_user));
        HttpHelper.getAsync(Constants.COMMENTS_TIMELINE, param, callback);
    }
    public static void getComment(JsonCallback<CommentListModel> callback) {
        getComment(0, 0, 50, 1, 0, callback);
    }
    public static void getComment(int count, int page, JsonCallback<CommentListModel> callback) {
        getComment(0, 0, count, page, 0, callback);
    }
    public static void getCommentMentions(long since_id, long max_id, int count, int page, AuthorType filter_by_author, SourceType filter_by_source, JsonCallback<CommentListModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("count", String.valueOf(count));
        param.put("page", String.valueOf(page));
        param.put("since_id", String.valueOf(since_id));
        param.put("max_id", String.valueOf(max_id));
        param.put("filter_by_author", String.valueOf(filter_by_author.getValue()));
        param.put("filter_by_source", String.valueOf(filter_by_source.getValue()));
        HttpHelper.getAsync(Constants.COMMENTS_MENTIONS, param, callback);
    }
    public static void getCommentMentions(JsonCallback<CommentListModel> callback) {
        getCommentMentions(0, 0, 50, 1, AuthorType.All, SourceType.All, callback);
    }

    public static void getCommentMentions(long max_id, int count, JsonCallback<CommentListModel> callback) {
        getCommentMentions(0, max_id, count, 1, AuthorType.All, SourceType.All, callback);
    }
    public static void getCommentMentions(int count, JsonCallback<CommentListModel> callback) {
        getCommentMentions(0, 0, count, 1, AuthorType.All, SourceType.All, callback);
    }
    public static void batch(JsonCallback<List<CommentModel>> callback, long... cids) {
        String cid = "";
        for (long id : cids) {
            cid += "," + String.valueOf(id);
        }
        cid = cid.substring(1);
        Map<String, String> param = new HashMap<>();
        param.put("cids", cid);
        HttpHelper.getAsync(Constants.COMMENTS_BATCH, param, callback);
    }
    public static void postComment(long id, String comment, boolean commentOri, JsonCallback<CommentModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("comment", comment);
        param.put("comment_ori", commentOri ? "1" : "0");
        HttpHelper.postAsync(Constants.COMMENTS_CREATE, param, callback);
    }
    public static void postComment(long id, String comment, JsonCallback<CommentModel> callback) {
        postComment(id, comment, false, callback);
    }
    public static void postCommentWithPic(long id, String comment, String pid, boolean commentOri, JsonCallback<CommentModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("comment", comment);
        param.put("comment_ori", commentOri ? "1" : "0");
        param.put("media", new MediaModel(pid).toString());
        param.put("source", "211160679");
        param.put("from", "1055095010");
        HttpHelper.postAsync("https://api.weibo.cn/2/comments/create", param, callback);
    }
    public static void postCommentWithPic(long id, String comment, String pid, JsonCallback<CommentModel> callback) {
        postCommentWithPic(id, comment, pid, false, callback);
    }

    public static void reply(long id, long cid, String comment, boolean comment_ori, boolean without_mention, JsonCallback<CommentModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("cid", String.valueOf(cid));
        param.put("comment", comment);
        param.put("comment_ori", comment_ori ? "1" : "0");
        param.put("without_mention", without_mention ? "1" : "0");
        HttpHelper.postAsync(Constants.COMMENTS_REPLY, param, callback);
    }
    public static void reply(long id, long cid, String comment, JsonCallback<CommentModel> callback) {
        reply(id, cid, comment, false, false, callback);
    }
    public static void replyWithPic(long id, long cid, String comment, String pid, boolean comment_ori, boolean without_mention, JsonCallback<CommentModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("cid", String.valueOf(cid));
        param.put("comment", comment);
        param.put("comment_ori", comment_ori ? "1" : "0");
        param.put("without_mention", without_mention ? "1" : "0");
        param.put("media", new MediaModel(pid).toString());
        param.put("source", "211160679");
        param.put("from", "1055095010");
        HttpHelper.postAsync("https://api.weibo.cn/2/comments/reply", param, callback);
    }

    public static void replyWithPic(long id, long cid, String comment, String pid, JsonCallback<CommentModel> callback) {
        replyWithPic(id, cid, comment, pid, false, false, callback);
    }
    public static void delete(long cid, JsonCallback<CommentModel> callback) {
        Map<String, String> param = new HashMap<>();
        param.put("cid", String.valueOf(cid));
        HttpHelper.postAsync(Constants.COMMENTS_DESTROY, param, callback);
    }
    public static void deleteBatch(JsonCallback<List<CommentModel>> callback, long... cids) {
        String cid = "";
        for (long id : cids) {
            cid += "," + String.valueOf(id);
        }
        cid = cid.substring(1);
        Map<String, String> param = new HashMap<>();
        param.put("cids", cid);
        HttpHelper.postAsync(Constants.COMMENTS_DESTROY_BATCH, param, callback);
    }
}
