package a3b.climate.utils.result;

import java.util.function.Supplier;

public interface Blunder<T, E> {
	T get();
	T getOr(T other);
	T getOrElse(Supplier<T> fn);
	T except();
	E getError();
	boolean isValid();
	void panic();
}
