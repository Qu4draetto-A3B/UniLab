package a3b.climate.utils.terminal;

import java.io.Console;
import java.io.IOException;
import java.util.function.Predicate;

public class Terminal {
	private Console con;

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

	public void clear() {
		con.printf("\033[H\033[2J");
	}

	public void printf(String str, Object... args) {
		con.printf(str, args);
	}

	public void printfln(String str, Object... args) {
		con.printf(str + "\n", args);
	}

	public String readLine() {
		return con.readLine("> ");
	}

	public String readLine(String str, Object... args) {
		return con.readLine(str + "\n> ", args);
	}

	public String readLineOrDefault(String def, String str, Object... args) {
		String out = readLine(str, args).strip();
		return out.isEmpty() ? def : out;
	}

	public String readPassword(String str, Object... args) {
		return new String(con.readPassword(str + "\n> ", args));
	}

	public String readWhile(Predicate<String> fn, String str, Object... args) {
		String out = readLine(str, args);
		while (fn.test(out)) {
			out = con.readLine(str, args);
		}
		return out;
	}

	public String readPasswordWhile(Predicate<String> fn, String str, Object... args) {
		String out = readPassword(str, args);
		while (fn.test(out)) {
			out = readPassword(str, args);
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

		String out = con.readLine(str + "\n" + yn + " > ", args).strip();
		out = out.isEmpty() ? def : out;

		boolean res = false;
		res |= out.equalsIgnoreCase("y");
		res |= out.equalsIgnoreCase("yes");
		res |= out.equalsIgnoreCase("s");
		res |= out.equalsIgnoreCase("si");

		return res;
	}
}
