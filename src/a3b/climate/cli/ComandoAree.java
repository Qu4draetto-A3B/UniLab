import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class ComandoAree implements View
{
    //metodi
    public void start(Terminal term)
    {
        Deque<double[]> coords = new ArrayDeque<double[]>();
        List<String> args = Arrays.asList(App.line.getOptionValue("lista-aree", "").split(","));

        for (String string : args) {
            String[] coord = string.split(":");
            if (coord.length == 2) {
                coords.push(new double[] {Double.parseDouble(coord[0]), Double.parseDouble(coord[1])});
            }
            args.remove(string);
        }
        
        for (String s : args) {
            long l;
            double d;
            try {
                l = Long.parseLong(s);
            } catch (Exception e) {
                break;
            }
            
            try {
                d = Double.parseDouble(s);
            } catch (Exception e) {
                break;
            }
        }
    }
}