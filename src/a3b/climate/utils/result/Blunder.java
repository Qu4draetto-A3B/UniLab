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
