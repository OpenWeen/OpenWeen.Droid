package moe.tlaster.openween.core.model.types

/**
 * Created by Tlaster on 2016/9/2.
 */
enum class QueryType constructor(val value: Int) {
    Weibo(1),
    Comment(2),
    DirectMessage(3)
}
