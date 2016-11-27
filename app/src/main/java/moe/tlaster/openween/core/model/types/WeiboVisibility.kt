package moe.tlaster.openween.core.model.types

/**
 * Created by Tlaster on 2016/9/2.
 */
enum class WeiboVisibility constructor(val value: Int) {
    All(0),
    OnlyMe(1),
    OnlyFriends(2),
    SpecifiedGroup(3)
}
