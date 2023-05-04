package a3b.climate.cli;

import java.util.Optional;

import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.Terminal;

public abstract class View {
    protected static Optional<Operatore> op = Optional.empty();
    protected Terminal term;

    protected View() {
        term = new Terminal();
        System.err.printf("Starting %s\n", super.toString());
    }

    public abstract void start();
}
