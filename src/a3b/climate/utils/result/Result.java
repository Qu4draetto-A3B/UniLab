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

/**
 * Classe che rappresenta un record
 */
public class Result<T> {
	private final int error;
	private final String message;
	private final T content;
	private final String fullMessage;

	/**
	 * Costruttore di un'istanza di Result<T>
	 *
	 * @param content Conenuto del record
	 */
	public Result(T content) {
		this(0, "OK", content);
	}

	/**
	 * Costruttore di un'istanza di Result<T>
	 *
	 * @param err Intero rappresentante un errore
	 * @param msg Messaggio relativo all'errore
	 */
	public Result(int err, String msg) {
		this(err, msg, null);
	}

	/**
	 * Costruttore di un'istanza di Result<T>
	 *
	 * @param err     Intero rappresentante un errore
	 * @param msg     Messaggio relativo all'errore
	 * @param content Contenuto del record
	 */
	public Result(int err, String msg, T content) {
		this.error = err;
		this.message = msg;
		this.content = content;
		this.fullMessage = String.format("%s [%d] %s", content != null ? content.getClass() : "NULL", error, message);
	}

	/**
	 * Metodo che determina la validita' di un record
	 *
	 * @return Booleano relativo alla validita' di un record
	 */
	public boolean isValid() {
		return error == 0;
	}

	/**
	 * Metodo che determina se un record rappresenta un errore
	 *
	 * @return Booleano che indica se un record rappresenta un errore
	 */
	public boolean isError() {
		return error != 0;
	}

	/**
	 * Meotodo che in base alla validita' di un record
	 *
	 * @param fn
	 * @throws NullPointerException
	 */
	public void ifValid(BiConsumer<T, Integer> fn) throws NullPointerException {
		if (isValid()) {
			fn.accept(content, error);
		}
	}

	/**
	 * Metodo che, nel caso in cui il record indichi un errore,
	 *
	 * @param fn
	 * @throws NullPointerException
	 */
	public void ifError(BiConsumer<T, Integer> fn) throws NullPointerException {
		if (isError()) {
			fn.accept(content, error);
		}
	}

	/**
	 * @return Errore relativo al record che esegue il metodo
	 */
	public Integer getError() {
		return error;
	}

	/**
	 * @return Messaggio relativo al record che esegue il metodo
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return Messaggio per intero relativo al record che esegue il metodo
	 */
	public String getFullMessage() {
		return fullMessage;
	}

	/**
	 * @return Contenuto del record che esegue il metodo se esiste
	 */
	public T get() {
		if (content == null) {
			panic();
		}
		return content;
	}

	/**
	 * @param other Altro elemento di tipo T
	 * @return Contenuto del record che esegue il metodo se presente, altrimenti
	 *         l'elemento fornito come parametro
	 */
	public T getOr(T other) {
		return content != null ? content : other;
	}

	/**
	 * @param fn
	 * @return Contenuto del record che esegue il metodo se presente, altrimenti il
	 *         contenuto del fornito come parametro
	 */
	public T getOrElse(Supplier<T> fn) {
		return content != null ? content : fn.get();
	}

	/**
	 * @return Contenuto del record che esegue il metodo
	 */
	public T except() {
		return content;
	}

	/**
	 * Metodo che lancia un errore
	 */
	public void panic() {
		throw new Panic(fullMessage);
	}
}
