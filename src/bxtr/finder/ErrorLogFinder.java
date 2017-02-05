package bxtr.finder;

import bxtr.Main;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Поисковик сообщений об ошибке.
 * Created by bxtr on 01.02.2017.
 */
public class ErrorLogFinder implements Observer, ConsoleOutput {
    private static final Pattern BEGIN_MESSAGE_PATTERN = Pattern.compile(".*ERROR.*");
    private static final Pattern END_MESSAGE_PATTERN = Pattern.compile(".*(INFO|DEBUG).*");

    private String line;
    private int lineNumber;
    private int currentLineIndex;
    private boolean flag;
    private Map<Integer, List<String>> map;

    public ErrorLogFinder() {
        flag = false;
        map = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SimpleReader) {
            SimpleReader reader = (SimpleReader) o;
            line = reader.getLine();
            lineNumber = reader.getLineNumber();
            if(BEGIN_MESSAGE_PATTERN.matcher(line).find()) {
                flag = true;
                currentLineIndex = reader.getLineNumber();
                map.put(currentLineIndex, new ArrayList<>());
            }
            if(flag) {
                map.get(currentLineIndex).add(line);
            }
            if(END_MESSAGE_PATTERN.matcher(line).find()) {
                flag = false;
            }

        }
    }

    @Override
    public void console() {
        Main.printErrorMap(map);
    }
}
