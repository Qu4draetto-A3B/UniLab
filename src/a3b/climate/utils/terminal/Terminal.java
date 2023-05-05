package a3b.climate.utils.terminal;

import java.io.Console;
import java.util.function.Predicate;

public class Terminal {
    private Console con;

    public Terminal() {
        con = System.console();
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
}
