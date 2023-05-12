package a3b.climate.utils.result;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface Blunder<T, E> {
	T get();
	T getOr(T other);
	T getOrElse(Supplier<T> fn);
	T except();
	E getError();
	boolean isValid();
	boolean isError();
	void ifValid(BiConsumer<T, E> fn) throws NullPointerException;
	void ifError(BiConsumer<T, E> fn) throws NullPointerException;
	void panic();
}
