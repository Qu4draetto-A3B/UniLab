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
import java.util.function.Predicate;

/**
 * Classe involucro di <code>System.console()</code> per aggiungere più
 * funzionalita'
 *
 * @see java.io.Console
 */
public class Terminal {
	private Console con;

	/**
	 * Costruttore di un'istanza di Terminal
	 */
	public Terminal() {
		con = System.console();
		String os = System.getProperty("os.name");
		try {
			if (os.contains("Windows")) {
				new ProcessBuilder("REG ADD HKCU\\CONSOLE /f /v VirtualTerminalLevel /t REG_DWORD /d 1")
						.inheritIO()
						.start()
						.waitFor();
			}
		} catch (Exception e) {
			con.printf("Errore nella modifica del registro 'VirtualTerminalLevel'\n");
			e.printStackTrace();
		}
	}

	/**
	 * Libera la console con il codice di escape ANSI "\033[H\033[2J"
	 */
	public void clear() {
		con.printf("\033[H\033[2J");
	}

	/**
	 * Prints a formatted string to the console
	 *
	 * @param str  The string to print
	 * @param args Values for string interpolation
	 */
	public void printf(String str, Object... args) {
		con.printf(str, args);
	}

	/**
	 * Prints a formatted string to the console, with a final newline
	 *
	 * @param str  The string to print
	 * @param args Values for string interpolation
	 */
	public void printfln(String str, Object... args) {
		con.printf(str + System.lineSeparator(), args);
	}

	/**
	 * Reads a line from the user's console.
	 *
	 * @return The string from the user
	 */
	public String readLine() {
		String s = con.readLine("> ");
		return s == null ? "" : s;
	}

	/**
	 * Stampa a schermo una stringa e aspetta una risposta dall'utente
	 *
	 * @param str  Stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Stringa fornita dall'utente
	 */
	public String readLine(String str, Object... args) {
		String s = con.readLine(str + System.lineSeparator() + "> ", args);
		return s == null ? "" : s;
	}

	/**
	 * Stampa una stringa all'utente e aspetta una risposta, se non viene data
	 * restituisce <code>def</code>
	 *
	 * @param def  Valore restituito di default
	 * @param str  Stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Stringa fornita dall'utente
	 */
	public String readLineOrDefault(String def, String str, Object... args) {
		String out = readLine(str, args).trim();
		return out.isEmpty() ? def : out;
	}

	/**
	 * Stampa a schermo una stringa e aspetta una risposta dall'utente, nascondendo
	 * il testo digitato
	 *
	 * @param str  Stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Stringa fornita dall'utente
	 */
	public String readPassword(String str, Object... args) {
		return new String(con.readPassword(str + "\n> ", args));
	}

	/**
	 * Stampa una stringa all'utente e aspetta una risposta, controllando l'imput
	 * dell utente con la funzione fornita
	 *
	 * @param fn   Condizione booleana, se <code>true</code> continua a richedere
	 *             l'inserimento di input
	 * @param str  Stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Stringa fornita dall'utente
	 */
	public String readWhile(Predicate<String> fn, String str, Object... args) {
		String out = readLine(str, args);
		while (fn.test(out)) {
			out = readLine(str, args);
		}
		return out;
	}

	/**
	 * Stampa una stringa all'utente e aspetta una risposta, controllando l'imput
	 * dell utente con la funzione fornita e nascondendo il testo digitato
	 *
	 * @param fn   Condizione booleana, se <code>true</code> continua a richedere
	 *             l'inserimento di input
	 * @param str  Stringa da stampare
	 * @param args Valori per l'interpolazione della stringa
	 * @return Stringa fornita dall'utente
	 */
	public String readPasswordWhile(Predicate<String> fn, String str, Object... args) {
		String out = readPassword(str, args);
		while (fn.test(out)) {
			out = readPassword(str, args);
		}
		return out;
	}

	/**
	 * Richiede all'utente una domanda con risposta si'/no
	 *
	 * @param yes  Se il default per un'azione assente di un utente è si' o no,
	 *             sara' rispettivamente <code>true</code> o <code>false</code>
	 * @param str  Domanda per l'utente
	 * @param args Valori per l'interpolazione della stringa
	 * @return <code>true</code> se l'utente ha digitato "y" o "yes",
	 *         <code>false</code> altrimenti
	 */
	public boolean promptUser(boolean yes, String str, Object... args) {
		String yn = "[y/N]";
		String def = "N";
		if (yes) {
			yn = "[Y/n]";
			def = "Y";
		}

		String in = con.readLine(str + "\n" + yn + " > ", args).trim();
		in = in.isBlank() ? def : in;

		boolean res = false;
		res |= in.equalsIgnoreCase("y");
		res |= in.equalsIgnoreCase("yes");
		res |= in.equalsIgnoreCase("s");
		res |= in.equalsIgnoreCase("si");

		return res;
	}
}
