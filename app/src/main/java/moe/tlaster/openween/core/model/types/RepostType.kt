package moe.tlaster.openween.core.model.types

/**
 * Created by Tlaster on 2016/9/2.
 */
enum class RepostType constructor(val value: Int) {
    None(0),
    CommentCurrentWeibo(1),
    CommentOriginWeibo(2),
    CommentAll(3)
}
