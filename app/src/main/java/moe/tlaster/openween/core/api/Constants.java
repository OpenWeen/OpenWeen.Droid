package moe.tlaster.openween.core.api;

/**
 * Created by Tlaster on 2016/9/3.
 */
public class Constants {

    public static final String SINA_BASE_URL = "https://api.weibo.com/2/";

    // Login
    public static final String OAUTH2_ACCESS_AUTHORIZE = "https://open.weibo.cn/oauth2/authorize";

    public static final String OAUTH2_ACCESS_TOKEN = SINA_BASE_URL + "oauth2/access_token";

    // User / Account
    public static final String USER_SHOW = SINA_BASE_URL + "users/show.json";//User.GetUser
    public static final String USER_COUNTS = SINA_BASE_URL + "users/counts.json";//User.GetUserInfo
    public static final String USER_DOMAIN_SHOW = SINA_BASE_URL + "users/domain_show.json";//User.GetUserByDomain
    public static final String RATE_LIMIT_STATUS = SINA_BASE_URL + "account/rate_limit_status.json";//Account.GetLimitStatus
    public static final String GET_UID = SINA_BASE_URL + "account/get_uid.json";//Account.GetUid
    public static final String GET_USER_MAIL = SINA_BASE_URL + "account/profile/email.json";//Account.GetMail

    // Statuses
    public static final String PUBLIC_TIMELINE = SINA_BASE_URL + "statuses/public_timeline";//Public.GetPublicTimeline
    public static final String HOME_TIMELINE = SINA_BASE_URL + "statuses/home_timeline.json";//Home.GetTimeline
    public static final String USER_TIMELINE = SINA_BASE_URL + "statuses/user_timeline.json";//UserTimeline.GetUserTimeline
    public static final String BILATERAL_TIMELINE = SINA_BASE_URL + "statuses/bilateral_timeline.json";//Bilateral.GetBilateral
    public static final String TIMELINE_BATCH = SINA_BASE_URL + "statuses/timeline_batch.json";//Timeline.GetTimelineBatch
    public static final String MENTIONS = SINA_BASE_URL + "statuses/mentions.json";//Mentions.GetMentions
    public static final String MENTIONS_SHIELD = SINA_BASE_URL + "statuses/mentions/shield.json";//Mentions.Shield
    public static final String REPOST_TIMELINE = SINA_BASE_URL + "statuses/repost_timeline.json";//Repost.GetRepost
    public static final String UPDATE = SINA_BASE_URL + "statuses/update.json";//PostWeibo.Post
    public static final String UPLOAD = SINA_BASE_URL + "statuses/upload.json";//PostWeibo.PostWithPic
    public static final String REPOST = SINA_BASE_URL + "statuses/repost.json";//PostWeibo.RePost
    public static final String DESTROY = SINA_BASE_URL + "statuses/destroy.json";//PostWeibo.DeletePost
    public static final String UPLOAD_PIC = SINA_BASE_URL + "statuses/upload_pic.json";//PostWeibo.UploadPicture
    public static final String UPLOAD_URL_TEXT = SINA_BASE_URL + "statuses/upload_url_text.json";//PostWeibo.PostWithMultiPics
    public static final String QUERY_ID = SINA_BASE_URL + "statuses/queryid.json";//Query.QueryID
    public static final String QUERY_MID = SINA_BASE_URL + "statuses/querymid.json";//Query.QueryMID
    public static final String SHOW = SINA_BASE_URL + "statuses/show.json";//Query.GetStatus
    public static final String SHOW_BATCH = SINA_BASE_URL + "statuses/show_batch.json";//Query.GetStatus
    public static final String EMOTIONS = SINA_BASE_URL + "emotions.json";//Emotions.GetEmotions
    public static final String FILTER_CREATE = SINA_BASE_URL + "statuses/filter/create.json";//Filter.Create

    // Comments
    public static final String COMMENTS_TIMELINE = SINA_BASE_URL + "comments/timeline.json";//Comments.GetComment,Comment.GetCommentSince
    public static final String COMMENTS_MENTIONS = SINA_BASE_URL + "comments/mentions.json";//Comments.GetCommentMentions
    public static final String COMMENTS_TO_ME = SINA_BASE_URL + "comments/to_me.json";//Comments.GetCommentToMe
    public static final String COMMENTS_BY_ME = SINA_BASE_URL + "comments/by_me.json";//Comments.GetCommentByMe
    public static final String COMMENTS_SHOW = SINA_BASE_URL + "comments/show.json";//Comments.GetCommentStatus
    public static final String COMMENTS_CREATE = SINA_BASE_URL + "comments/create.json";//Comments.PostComment
    public static final String COMMENTS_REPLY = SINA_BASE_URL + "comments/reply.json";//Comments.Reply
    public static final String COMMENTS_DESTROY = SINA_BASE_URL + "comments/destroy.json";//Comments.Delete
    public static final String COMMENTS_BATCH = SINA_BASE_URL + "comments/show_batch.json";//Comments.Batch
    public static final String COMMENTS_DESTROY_BATCH = SINA_BASE_URL + "comments/destroy_batch.json";//Comments.DeleteBatch

    // Favorites
    public static final String FAVORITES_CREATE = SINA_BASE_URL + "favorites/create.json";//Favorites.AddFavor
    public static final String FAVORITES_DESTROY = SINA_BASE_URL + "favorites/destroy.json";//Favorites.RemoveFavor
    public static final String FAVORITES_LIST = SINA_BASE_URL + "favorites.json";//Favorites.GetFavorList
    public static final String FAVORITES_SHOW = SINA_BASE_URL + "favorites/show.json";//Favorites.GetFavor
    public static final String FAVORITES_LIST_BY_TAG = SINA_BASE_URL + "favorites/by_tags.json";//Favorites.GetFavorListByTag
    public static final String FAVORITES_TAGS = SINA_BASE_URL + "favorites/tags.json";//Favorites.GetTags
    public static final String FAVORITES_DESTROY_BATCH = SINA_BASE_URL + "favorites/destroy_batch.json";//Favorites.RemoveFavors
    public static final String FAVORITES_TAGS_UPDATE = SINA_BASE_URL + "avorites/tags/update.json";//Favorites.UpdateTags

    // Blocks
    public static final String BLOCKS_LIST = SINA_BASE_URL + "blocks/blocking.json";//Blocks.GetBlocksList
    public static final String BLOCKS_CREATE = SINA_BASE_URL + "blocks/create.json";//Blocks.AddBlock
    public static final String BLOCKS_DESTROY = SINA_BASE_URL + "blocks/destroy.json";//Blocks.RemoveBlock
    public static final String BLOCKS_EXISTS = SINA_BASE_URL + "blocks/exists.json";//Blocks.IsBlocked

    // Search
    public static final String SEARCH_TOPICS = SINA_BASE_URL + "search/topics.json";//Topics.GetSearchTopics
    public static final String SEARCH_STATUSES = SINA_BASE_URL + "search/statuses.json";//Search.SearchStatus
    public static final String SEARCH_USERS = SINA_BASE_URL + "search/users.json";//Search.SearchUsers
    public static final String SEARCH_SUGGESTIONS_AT_USERS = SINA_BASE_URL + "search/suggestions/at_users.json";//Search.SuggestAtUser

    // Friendships
    public static final String FRIENDSHIPS_FRIENDS = SINA_BASE_URL + "friendships/friends.json";//Friends.GetFriends
    public static final String FRIENDSHIPS_FOLLOWERS = SINA_BASE_URL + "friendships/followers.json";//Friends.GetFollowers
    public static final String FRIENDSHIPS_CREATE = SINA_BASE_URL + "friendships/create.json";//Friends.Follow
    public static final String FRIENDSHIPS_DESTROY = SINA_BASE_URL + "friendships/destroy.json";//Friends.UnFollow
    public static final String FRIENDSHIPS_FRIENDS_IN_COMMON = SINA_BASE_URL + "friendships/friends/in_common.json";//Friends.GetFriendsInCommon
    public static final String FRIENDSHIPS_FRIENDS_BILATERAL = SINA_BASE_URL + "friendships/friends/bilateral.json";//Friends.GetBliateral
    public static final String FRIENDSHIPS_FOLLOWERS_ACTIVE = SINA_BASE_URL + "friendships/followers/active.json";//Friends.GetActiveFollowers
    public static final String FRIENDSHIPS_FRIENDS_CHAIN = SINA_BASE_URL + "friendships/friends_chain/followers.json";//Friends.GetFriendsChain
    public static final String FRIENDSHIPS_GROUPS = SINA_BASE_URL + "friendships/groups.json";//Groups.GetGroups
    public static final String FRIENDSHIPS_GROUPS_IS_MEMBER = SINA_BASE_URL + "friendships/groups/is_member.json";//Groups.IsMember
    public static final String FRIENDSHIPS_GROUPS_TIMELINE = SINA_BASE_URL + "friendships/groups/timeline.json";//Groups.GetGroupTimeline
    public static final String FRIENDSHIPS_GROUPS_CREATE = SINA_BASE_URL + "friendships/groups/create.json";//Groups.CreateGroup
    public static final String FRIENDSHIPS_GROUPS_DESTROY = SINA_BASE_URL + "friendships/groups/destroy.json";//Groups.DeleteGroup
    public static final String FRIENDSHIPS_GROUPS_MEMBERS_ADD = SINA_BASE_URL + "friendships/groups/members/add.json";//Groups.AddToGroup
    public static final String FRIENDSHIPS_GROUPS_MEMBERS_DESTROY = SINA_BASE_URL + "friendships/groups/members/destroy.json";//Groups.RemoveFromGroup

    // Direct Message
    public static final String DIRECT_MESSAGES = SINA_BASE_URL + "direct_messages.json";//DirectMessages.GetDirectMessages
    public static final String DIRECT_MESSAGES_USER_LIST = SINA_BASE_URL + "direct_messages/user_list.json";//DirectMessage.GetUserList
    public static final String DIRECT_MESSAGES_CONVERSATION = SINA_BASE_URL + "direct_messages/conversation.json";//DirectMessage.GetConversation
    public static final String DIRECT_MESSAGES_SEND = SINA_BASE_URL + "direct_messages/new.json";//DirectMessage.Send
    public static final String DIRECT_MESSAGES_THUMB_PIC = "https://upload.api.weibo.com/2/mss/msget_thumbnail?fid=%d&access_token=%s&high=%d&width=%d";//TODO: replace %d and %s
    public static final String DIRECT_MESSAGES_ORIG_PIC = "https://upload.api.weibo.com/2/mss/msget?fid=%d&access_token=%s";//TODO: replace %d and %s
    public static final String DIRECT_MESSAGES_UPLOAD_PIC = "https://upload.api.weibo.com/2/mss/upload.json?tuid=";//DirectMessage.SendPicture

    // Remind
    public static final String REMIND_UNREAD_COUNT = "https://rm.api.weibo.com/2/remind/unread_count.json";//Remind.GetUnRead
    public static final String REMIND_UNREAD_SET_COUNT = "https://rm.api.weibo.com/2/remind/set_count.json";//Remind.ClearUnRead

    // Attitude
    public static final String ATTITUDE_CREATE = SINA_BASE_URL + "attitudes/create.json";//Attitudes.Like
    public static final String ATTITUDE_DESTROY = SINA_BASE_URL + "attitudes/destroy.json";//Attitudes.UnLike

    // Short Url
    public static final String SHORT_URL_SHORTEN = SINA_BASE_URL + "short_url/shorten.json";//ShortUrl.Shorten
    public static final String SHORT_URL_EXPAND = SINA_BASE_URL + "short_url/expand.json";//ShortUrl.Expand
    public static final String SHORT_URL_INFO = SINA_BASE_URL + "short_url/info.json";//ShortUrl.Info
}
