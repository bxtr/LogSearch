package bxtr.finder;

import bxtr.Main;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Поисковик заданной подстроки.
 * Created by bxtr on 01.02.2017.
 */
public class SubstringFinder implements Observer, ConsoleOutput{
    public final Pattern SUBSTRING;

    private Map<Integer, String> map;

    public SubstringFinder(String stringToFind){
        SUBSTRING = Pattern.compile(stringToFind);
        map = new HashMap<>();
    }

    @Override
    public void console() {
        Main.printMap(map);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SimpleReader) {
            SimpleReader simpleReader = (SimpleReader) o;
            String line = simpleReader.getLine();
            if (SUBSTRING.matcher(line).find()) {
                map.put(simpleReader.getLineNumber(), line);
            }
        }
    }
}
