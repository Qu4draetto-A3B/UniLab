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

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Rappresenta un
 */
abstract class Either<L, R> {
	/**
	 * Metodo che
	 *
	 * @param <L>
	 * @param <R>
	 * @param value
	 * @return
	 */
	public static <L, R> Either<L, R> left(L value) {
		return new Either<L, R>() {
			@Override
			public <T> T map(Function<? super L, ? extends T> lFunc,
					Function<? super R, ? extends T> rFunc) {
				return lFunc.apply(value);
			}
		};
	}

	/**
	 * Metrodo che
	 *
	 * @param <L>
	 * @param <R>
	 * @param value
	 * @return
	 */
	public static <L, R> Either<L, R> right(R value) {
		return new Either<L, R>() {
			@Override
			public <T> T map(Function<? super L, ? extends T> lFunc,
					Function<? super R, ? extends T> rFunc) {
				return rFunc.apply(value);
			}

		};
	}

	/**
	 * Metodo
	 */
	private Either() {
	}

	/**
	 * Metodo
	 *
	 * @param <T>
	 * @param lFunc
	 * @param rFunc
	 * @return
	 */
	public abstract <T> T map(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc);

	public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc) {
		return this.<Either<T, R>>map(t -> left(lFunc.apply(t)), t -> (Either<T, R>) this);
	}

	/**
	 * Metodo
	 *
	 * @param <T>
	 * @param rFunc
	 * @return
	 */
	public <T> Either<L, T> mapRight(Function<? super R, ? extends T> rFunc) {
		return this.<Either<L, T>>map(t -> (Either<L, T>) this, t -> right(rFunc.apply(t)));
	}

	/**
	 * Metodo
	 *
	 * @param lFunc
	 * @param rFunc
	 */
	public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
		map(consume(lFunc), consume(rFunc));
	}

	/**
	 * Metodo
	 *
	 * @param <T>
	 * @param c
	 * @return
	 */
	private <T> Function<T, Void> consume(Consumer<T> c) {
		return t -> {
			c.accept(t);
			return null;
		};
	}
}
