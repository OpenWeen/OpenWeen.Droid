package moe.tlaster.openween.core.model.types

/**
 * Created by Tlaster on 2016/9/2.
 */
enum class UrlType private constructor(val value: Int) {
    WebLink(0),
    Video(1),
    Music(2),
    Event(3),
    Vote(5)
}
