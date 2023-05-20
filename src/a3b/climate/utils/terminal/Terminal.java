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

import a3b.climate.utils.result.Either;

import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Wrapper around <code>System.console()</code> to add more functionalities
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
	 * Clears the console with "\033[H\033[2J" ANSI escape code
	 */
	public void clear() {
		printf("\033[H\033[2J");
	}

	/**
	 * Prints a formatted string to the console
	 * @param str The string to print
	 * @param args Values for string interpolation
	 */
	public void printf(String str, Object... args) {
		out.printf(str, args);
	}

	/**
	 * Prints a formatted string to the console, with a final newline
	 * @param str The string to print
	 * @param args Values for string interpolation
	 */
	public void printfln(String str, Object... args) {
		printf(str + System.lineSeparator(), args);
	}

	/**
	 * Reads a line from the user's console.
	 * @return The string from the user
	 */
	public String readLine() {
		printf("> ");
		String s = in.nextLine();
		return s == null ? "" : s;
	}

	/**
	 * Prints a string to the user and waits a response
	 * @param str The string to print
	 * @param args Values for string interpolation
	 * @return The string from the user
	 */
	public String readLine(String str, Object... args) {
		printfln(str + System.lineSeparator() + "> ", args);
		String s = readLine();
		return s == null ? "" : s;
	}

	/**
	 * Prints a string to the user and waits a response, if no response is given returns <code>def</code>
	 * @param def The default return value
	 * @param str The string to print
	 * @param args Values for string interpolation
	 * @return The string from the user
	 */
	public String readLineOrDefault(String def, String str, Object... args) {
		String out = readLine(str, args).trim();
		return out.isBlank() ? def : out;
	}

	/**
	 * Prints a string to the user and waits a response, checking the user's input with the provided function
	 * @param fn Boolean condition, if <code>true</code> keeps asking some input
	 * @param str The string to print
	 * @param args Values for string interpolation
	 * @return The string from the user
	 */
	public String readWhile(Predicate<String> fn, String str, Object... args) {
		String out = readLine(str, args);
		while (fn.test(out)) {
			out = readLine(str, args);
		}
		return out;
	}

	/**
	 * Prompts the user with a yes or no question.
	 * @param yes Whether the default for absent user action is yes or no, respectively <code>true</code> or <code>false</code>
	 * @param str Question for the user
	 * @param args Values for string interpolation
	 * @return <code>true</code> if user sent "y" or "yes", <code>false</code> otherwise
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
	 * @return
	 */
	public Either<? extends Throwable, String> read(Predicate<String> fn, String def, String str, Object... args) {
		String out;
		 do {
			out = readLine(str, args);
			if (out == null) {
				return Either.left(new NullPointerException());
			}
		} while (fn.test(out));
		return Either.right(out.isBlank() ? def : out);
	}
}
