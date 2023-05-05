package a3b.climate.utils.terminal;

public class Screen {
    protected Terminal term;

	public Screen() {
		term = new Terminal();
	}

	public void show(View v) {
		term.clear();
		v.start(term);
	}
}
