package a3b.climate.cli;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class ComandoAree implements View
{
    //metodi
    public void start(Terminal term)
    {
        Deque<double[]> coords = new ArrayDeque<double[]>();
        List<String> args = Arrays.asList(App.line.getOptionValues("lista-aree"));

        for (String string : args) {
			if (!string.contains(":")) {
				continue;
			}

            String[] coord = string.split(":");
            if (coord.length == 2) {
                coords.push(new double[] {Double.parseDouble(coord[0]), Double.parseDouble(coord[1])});
            }
            args.remove(string);
        }

		Deque<Long> geoids = new LinkedList<Long>();

        for (String s : args) {
            long l;
            try {
                l = Long.parseLong(s);
				geoids.push(l);
            } catch (Exception e) {
                break;
            }
        }
    }
}
