/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate.utils.result;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Result<T> implements Blunder<T, Integer> {
	private final int error;
	private final String message;
	private final T content;
	private final String fullMessage;

	public Result(T content) {
		this(0, "OK", content);
	}

	public Result(int err, String msg) {
		this(err, msg, null);
	}

	public Result(int err, String msg, T content) {
		this.error = err;
		this.message = msg;
		this.content = content;
		this.fullMessage = String.format("%s [%d] %s", content != null ? content.getClass() : "NULL", error, message);
	}

	public boolean isValid() {
		return error == 0;
	}

	@Override
	public boolean isError() {
		return error != 0;
	}

	@Override
	public void ifValid(BiConsumer<T, Integer> fn) throws NullPointerException {
		if (isValid()) {
			fn.accept(content, error);
		}
	}

	@Override
	public void ifError(BiConsumer<T, Integer> fn) throws NullPointerException {
		if (isError()) {
			fn.accept(content, error);
		}
	}

	@Override
	public Integer getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getFullMessage() {
		return fullMessage;
	}

	@Override
	public T get() {
		if (content == null) {
			panic();
		}
		return content;
	}

	@Override
	public T getOr(T other) {
		return content != null ? content : other;
	}

	@Override
	public T getOrElse(Supplier<T> fn) {
		return content != null ? content : fn.get();
	}

	@Override
	public T except() {
		return content;
	}

	@Override
	public void panic() {
		throw new Panic(fullMessage);
	}
}
