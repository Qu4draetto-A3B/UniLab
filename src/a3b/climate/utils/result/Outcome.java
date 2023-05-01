package a3b.climate.utils.result;

import java.util.function.Supplier;

public class Outcome<T, E extends Throwable> implements Blunder<T, E> {
	private final T CONTENT;
	private final E ERROR;

	public Outcome(T content, E error) {
		this.CONTENT = content;
		this.ERROR = error;
	}

	public Outcome(T content) {
		this(content, null);
	}

	public Outcome(E error) {
		this(null, error);
	}

	@Override
	public boolean isValid() {
		return ERROR == null;
	}

	@Override
	public E getError() {
		return ERROR;
	}

	@Override
	public T get() {
		if (CONTENT == null) {
			panic();
		}
		return CONTENT;
	}

	@Override
	public T getOr(T other) {
		return CONTENT != null ? CONTENT : other;
	}

	@Override
	public T getOrElse(Supplier<T> fn) {
		return CONTENT != null ? CONTENT : fn.get();
	}

	@Override
	public T except() {
		return CONTENT;
	}

	@Override
	public void panic() {
		throw new Panic(ERROR);
	}
}
