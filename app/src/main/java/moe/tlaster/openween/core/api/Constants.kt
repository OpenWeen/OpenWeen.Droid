package moe.tlaster.openween.core.api

/**
 * Created by Tlaster on 2016/9/3.
 */
object Constants {

    val SINA_BASE_URL = "https://api.weibo.com/2/"

    // Login
    val OAUTH2_ACCESS_AUTHORIZE = "https://open.weibo.cn/oauth2/authorize"

    val OAUTH2_ACCESS_TOKEN = SINA_BASE_URL + "oauth2/access_token"

    // User / Account
    val USER_SHOW = SINA_BASE_URL + "users/show.json"//User.GetUser
    val USER_COUNTS = SINA_BASE_URL + "users/counts.json"//User.GetUserInfo
    val USER_DOMAIN_SHOW = SINA_BASE_URL + "users/domain_show.json"//User.GetUserByDomain
    val RATE_LIMIT_STATUS = SINA_BASE_URL + "account/rate_limit_status.json"//Account.GetLimitStatus
    val GET_UID = SINA_BASE_URL + "account/get_uid.json"//Account.GetUid
    val GET_USER_MAIL = SINA_BASE_URL + "account/profile/email.json"//Account.GetMail

    // Statuses
    val PUBLIC_TIMELINE = SINA_BASE_URL + "statuses/public_timeline"//Public.GetPublicTimeline
    val HOME_TIMELINE = SINA_BASE_URL + "statuses/home_timeline.json"//Home.GetTimeline
    val USER_TIMELINE = SINA_BASE_URL + "statuses/user_timeline.json"//UserTimeline.GetUserTimeline
    val BILATERAL_TIMELINE = SINA_BASE_URL + "statuses/bilateral_timeline.json"//Bilateral.GetBilateral
    val TIMELINE_BATCH = SINA_BASE_URL + "statuses/timeline_batch.json"//Timeline.GetTimelineBatch
    val MENTIONS = SINA_BASE_URL + "statuses/mentions.json"//Mentions.GetMentions
    val MENTIONS_SHIELD = SINA_BASE_URL + "statuses/mentions/shield.json"//Mentions.Shield
    val REPOST_TIMELINE = SINA_BASE_URL + "statuses/repost_timeline.json"//Repost.GetRepost
    val UPDATE = SINA_BASE_URL + "statuses/update.json"//PostWeibo.Post
    val UPLOAD = SINA_BASE_URL + "statuses/upload.json"//PostWeibo.PostWithPic
    val REPOST = SINA_BASE_URL + "statuses/repost.json"//PostWeibo.RePost
    val DESTROY = SINA_BASE_URL + "statuses/destroy.json"//PostWeibo.DeletePost
    val UPLOAD_PIC = SINA_BASE_URL + "statuses/upload_pic.json"//PostWeibo.UploadPicture
    val UPLOAD_URL_TEXT = SINA_BASE_URL + "statuses/upload_url_text.json"//PostWeibo.PostWithMultiPics
    val QUERY_ID = SINA_BASE_URL + "statuses/queryid.json"//Query.QueryID
    val QUERY_MID = SINA_BASE_URL + "statuses/querymid.json"//Query.QueryMID
    val SHOW = SINA_BASE_URL + "statuses/show.json"//Query.GetStatus
    val SHOW_BATCH = SINA_BASE_URL + "statuses/show_batch.json"//Query.GetStatus
    val EMOTIONS = SINA_BASE_URL + "emotions.json"//Emotions.GetEmotions
    val FILTER_CREATE = SINA_BASE_URL + "statuses/filter/create.json"//Filter.Create

    // Comments
    val COMMENTS_TIMELINE = SINA_BASE_URL + "comments/timeline.json"//Comments.GetComment,Comment.GetCommentSince
    val COMMENTS_MENTIONS = SINA_BASE_URL + "comments/mentions.json"//Comments.GetCommentMentions
    val COMMENTS_TO_ME = SINA_BASE_URL + "comments/to_me.json"//Comments.GetCommentToMe
    val COMMENTS_BY_ME = SINA_BASE_URL + "comments/by_me.json"//Comments.GetCommentByMe
    val COMMENTS_SHOW = SINA_BASE_URL + "comments/show.json"//Comments.GetCommentStatus
    val COMMENTS_CREATE = SINA_BASE_URL + "comments/create.json"//Comments.PostComment
    val COMMENTS_REPLY = SINA_BASE_URL + "comments/reply.json"//Comments.Reply
    val COMMENTS_DESTROY = SINA_BASE_URL + "comments/destroy.json"//Comments.Delete
    val COMMENTS_BATCH = SINA_BASE_URL + "comments/show_batch.json"//Comments.Batch
    val COMMENTS_DESTROY_BATCH = SINA_BASE_URL + "comments/destroy_batch.json"//Comments.DeleteBatch

    // Favorites
    val FAVORITES_CREATE = SINA_BASE_URL + "favorites/create.json"//Favorites.AddFavor
    val FAVORITES_DESTROY = SINA_BASE_URL + "favorites/destroy.json"//Favorites.RemoveFavor
    val FAVORITES_LIST = SINA_BASE_URL + "favorites.json"//Favorites.GetFavorList
    val FAVORITES_SHOW = SINA_BASE_URL + "favorites/show.json"//Favorites.GetFavor
    val FAVORITES_LIST_BY_TAG = SINA_BASE_URL + "favorites/by_tags.json"//Favorites.GetFavorListByTag
    val FAVORITES_TAGS = SINA_BASE_URL + "favorites/tags.json"//Favorites.GetTags
    val FAVORITES_DESTROY_BATCH = SINA_BASE_URL + "favorites/destroy_batch.json"//Favorites.RemoveFavors
    val FAVORITES_TAGS_UPDATE = SINA_BASE_URL + "avorites/tags/update.json"//Favorites.UpdateTags

    // Blocks
    val BLOCKS_LIST = SINA_BASE_URL + "blocks/blocking.json"//Blocks.GetBlocksList
    val BLOCKS_CREATE = SINA_BASE_URL + "blocks/create.json"//Blocks.AddBlock
    val BLOCKS_DESTROY = SINA_BASE_URL + "blocks/destroy.json"//Blocks.RemoveBlock
    val BLOCKS_EXISTS = SINA_BASE_URL + "blocks/exists.json"//Blocks.IsBlocked

    // Search
    val SEARCH_TOPICS = SINA_BASE_URL + "search/topics.json"//Topics.GetSearchTopics
    val SEARCH_STATUSES = SINA_BASE_URL + "search/statuses.json"//Search.SearchStatus
    val SEARCH_USERS = SINA_BASE_URL + "search/users.json"//Search.SearchUsers
    val SEARCH_SUGGESTIONS_AT_USERS = SINA_BASE_URL + "search/suggestions/at_users.json"//Search.SuggestAtUser

    // Friendships
    val FRIENDSHIPS_FRIENDS = SINA_BASE_URL + "friendships/friends.json"//Friends.GetFriends
    val FRIENDSHIPS_FOLLOWERS = SINA_BASE_URL + "friendships/followers.json"//Friends.GetFollowers
    val FRIENDSHIPS_CREATE = SINA_BASE_URL + "friendships/create.json"//Friends.Follow
    val FRIENDSHIPS_DESTROY = SINA_BASE_URL + "friendships/destroy.json"//Friends.UnFollow
    val FRIENDSHIPS_FRIENDS_IN_COMMON = SINA_BASE_URL + "friendships/friends/in_common.json"//Friends.GetFriendsInCommon
    val FRIENDSHIPS_FRIENDS_BILATERAL = SINA_BASE_URL + "friendships/friends/bilateral.json"//Friends.GetBliateral
    val FRIENDSHIPS_FOLLOWERS_ACTIVE = SINA_BASE_URL + "friendships/followers/active.json"//Friends.GetActiveFollowers
    val FRIENDSHIPS_FRIENDS_CHAIN = SINA_BASE_URL + "friendships/friends_chain/followers.json"//Friends.GetFriendsChain
    val FRIENDSHIPS_GROUPS = SINA_BASE_URL + "friendships/groups.json"//Groups.GetGroups
    val FRIENDSHIPS_GROUPS_IS_MEMBER = SINA_BASE_URL + "friendships/groups/is_member.json"//Groups.IsMember
    val FRIENDSHIPS_GROUPS_TIMELINE = SINA_BASE_URL + "friendships/groups/timeline.json"//Groups.GetGroupTimeline
    val FRIENDSHIPS_GROUPS_CREATE = SINA_BASE_URL + "friendships/groups/create.json"//Groups.CreateGroup
    val FRIENDSHIPS_GROUPS_DESTROY = SINA_BASE_URL + "friendships/groups/destroy.json"//Groups.DeleteGroup
    val FRIENDSHIPS_GROUPS_MEMBERS_ADD = SINA_BASE_URL + "friendships/groups/members/add.json"//Groups.AddToGroup
    val FRIENDSHIPS_GROUPS_MEMBERS_DESTROY = SINA_BASE_URL + "friendships/groups/members/destroy.json"//Groups.RemoveFromGroup

    // Direct Message
    val DIRECT_MESSAGES = SINA_BASE_URL + "direct_messages.json"//DirectMessages.GetDirectMessages
    val DIRECT_MESSAGES_USER_LIST = SINA_BASE_URL + "direct_messages/user_list.json"//DirectMessage.GetUserList
    val DIRECT_MESSAGES_CONVERSATION = SINA_BASE_URL + "direct_messages/conversation.json"//DirectMessage.GetConversation
    val DIRECT_MESSAGES_SEND = SINA_BASE_URL + "direct_messages/new.json"//DirectMessage.Send
    val DIRECT_MESSAGES_THUMB_PIC = "https://upload.api.weibo.com/2/mss/msget_thumbnail?fid=%d&access_token=%s&high=%d&width=%d"//TODO: replace %d and %s
    val DIRECT_MESSAGES_ORIG_PIC = "https://upload.api.weibo.com/2/mss/msget?fid=%d&access_token=%s"//TODO: replace %d and %s
    val DIRECT_MESSAGES_UPLOAD_PIC = "https://upload.api.weibo.com/2/mss/upload.json?tuid="//DirectMessage.SendPicture

    // Remind
    val REMIND_UNREAD_COUNT = "https://rm.api.weibo.com/2/remind/unread_count.json"//Remind.GetUnRead
    val REMIND_UNREAD_SET_COUNT = "https://rm.api.weibo.com/2/remind/set_count.json"//Remind.ClearUnRead

    // Attitude
    val ATTITUDE_CREATE = SINA_BASE_URL + "attitudes/create.json"//Attitudes.Like
    val ATTITUDE_DESTROY = SINA_BASE_URL + "attitudes/destroy.json"//Attitudes.UnLike

    // Short Url
    val SHORT_URL_SHORTEN = SINA_BASE_URL + "short_url/shorten.json"//ShortUrl.Shorten
    val SHORT_URL_EXPAND = SINA_BASE_URL + "short_url/expand.json"//ShortUrl.Expand
    val SHORT_URL_INFO = SINA_BASE_URL + "short_url/info.json"//ShortUrl.Info
}
