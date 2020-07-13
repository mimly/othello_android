package mimly.othello.model;

import java.util.stream.Stream;

@FunctionalInterface
public interface Streamable<T> {

    Stream<T> stream();

}
