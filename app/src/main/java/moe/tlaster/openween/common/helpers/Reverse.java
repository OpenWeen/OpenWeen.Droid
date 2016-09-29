package moe.tlaster.openween.common.helpers;

import com.annimon.stream.Stream;
import com.annimon.stream.function.UnaryOperator;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by Asahi on 2016/9/27.
 */

public final class Reverse<T> implements UnaryOperator<Stream<T>> {

    @Override
    public Stream<T> apply(Stream<T> stream) {
        final Iterator<? extends T> iterator = stream.iterator();
        final ArrayDeque<T> deque = new ArrayDeque<T>();
        while (iterator.hasNext()) {
            deque.addFirst(iterator.next());
        }
        return Stream.of(deque.iterator());
    }
}