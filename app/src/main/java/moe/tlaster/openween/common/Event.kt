package moe.tlaster.openween.common

/**
 * Created by Asahi on 16/11/27.
 */
class Event<T> {
    private val handlers = arrayListOf<(Event<T>.(T) -> Unit)>()
    operator fun plusAssign(handler: Event<T>.(T) -> Unit) { handlers.add(handler) }
    fun minusAssign(handler: Event<T>.(T) -> Unit) { handlers.remove(handler) }
    fun invoke(value: T) { for (handler in handlers) handler(value) }
}