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
package a3b.climate.utils.terminal;

import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Involucro che racchiude <code>System.in</code> e <code>System.out</code>, aggiungendo varie funzionalita'
 * @see java.io.Console
 */
public class Terminal {
	private PrintStream out;
	private Scanner in;

	public Terminal() {
		out = System.out;
		in = new Scanner(System.in);

		String os = System.getProperty("os.name");
		try {
			if (os.contains("Windows")) {
				new ProcessBuilder("REG ADD HKCU\\CONSOLE /f /v VirtualTerminalLevel /t REG_DWORD /d 1")
					.inheritIO()
					.start()
					.waitFor();
			}
		} catch (Exception e) {
			out.printf("Errore nella modifica del registro 'VirtualTerminalLevel'\n");
			e.printStackTrace();
		}
	}

	/**
	 * Pulisce il terminale con il codice ANSI "\033[H\033[2J"
	 */
	public void clear() {
		printf("\033[H\033[2J");
	}

	/**
	 * Formatta e stampa una stringa nel terminale
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 */
	public void printf(String str, Object... args) {
		out.printf(str, args);
	}

	/**
	 * Formatta e stampa una stringa nel terminale, con un a capo finale
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 */
	public void printfln(String str, Object... args) {
		printf(str + System.lineSeparator(), args);
	}

	/**
	 * Legge una linea di testo dal terminale
	 * @return Una stringa dall'utente
	 */
	public String readLine() {
		printf("> ");
		String s = in.nextLine();
		return s == null ? "" : s;
	}

	/**
	 * Stampa una stringa e aspetta una risposta dall'utente
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Una stringa dall'utente
	 */
	public String readLine(String str, Object... args) {
		printfln(str + System.lineSeparator() + "> ", args);
		String s = readLine();
		return s == null ? "" : s;
	}

	/**
	 * Stampa una stringa e aspetta una risposta dall'utente, ritornando <code>def</code> nel caso l'utente inserisca una stringa vuota
	 * @param def Il valore di ritorno se l'utente non inserisce nulla o una stringa vuota
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Una stringa dall'utente, oppure <code>def</code> se la stringa e' vuota
	 */
	public String readLineOrDefault(String def, String str, Object... args) {
		String out = readLine(str, args).trim();
		return out.isBlank() ? def : out;
	}

	/**
	 * Stampa una stringa e aspetta una risposta dall'utente, controllando la stringa inserita usando <code>fn</code>
	 * @param fn Condizione booleana, se <code>true</code> continua a chiedere una stringa all'utente
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Una stringa dall'utente
	 */
	public String readWhile(Predicate<String> fn, String str, Object... args) {
		String out = readLine(str, args);
		while (fn.test(out)) {
			out = readLine(str, args);
		}
		return out;
	}

	/**
	 * Chiede all'utente una domanda con risposta Si/No
	 * @param yes Se <code>true</code> la risposta di default e' Si, se <code>false</code> e' No
	 * @param str La stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return <code>true</code> se Si, <code>false</code> se No
	 */
	public boolean promptUser(boolean yes, String str, Object... args) {
		String yn = "[y/N]";
		String def = "N";
		if (yes) {
			yn = "[Y/n]";
			def = "Y";
		}

		String in = readLine(str + " " + yn, args).trim();
		in = in.isBlank() ? def : in;

		boolean res = false;
		res |= in.equalsIgnoreCase("y");
		res |= in.equalsIgnoreCase("yes");
		res |= in.equalsIgnoreCase("s");
		res |= in.equalsIgnoreCase("si");

		return res;
	}

	/**
	 * Metodo super generico, capace di assorbire le capacita' dei precedenti
	 * @param fn Condizione booleana, se e' <code>true</code> continua a chiedere un input
	 * @param def Il valore di ritorno predefinito
	 * @param str Domanda per l'utente
	 * @param args Valori per l'iterpolazione di <code>str</code>
	 * @return Un'errore se l'utente preme CTRL+D, altrimenti la stringa dall'utente
	 *//*
	public Either<? extends Throwable, String> read(Predicate<String> fn, String def, String str, Object... args) {
		String out;
		 do {
			out = readLine(str, args);
			if (out == null) {
				return Either.left(new NullPointerException());
			}
		} while (fn.test(out));
		return Either.right(out.isBlank() ? def : out);
	}*/
}
