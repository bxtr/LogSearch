package bxtr.finder;

import bxtr.Main;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by egor on 05.02.2017.
 */
public class UserErrorFinder  implements Observer, ConsoleOutput {


    private  Pattern BEGIN_MESSAGE_PATTERN = null;
    private static final Pattern INTERMEDIATE_MESSAGE_PATTERN = Pattern.compile(".*ERROR.*");
    private static final Pattern END_MESSAGE_PATTERN = Pattern.compile(".*(INFO|DEBUG).*");
    private  String name;
    private String line;
    private int lineNumber;
    private int currentLineIndex;
    private boolean flag;
    private Map<Integer, List<String>> map;


    public UserErrorFinder(String name) {
        this.name = name;
        this.BEGIN_MESSAGE_PATTERN = Pattern.compile(".*INFO.*name=\'"+name+"\'.*");
        flag = false;
        map = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SimpleReader) {
            SimpleReader reader = (SimpleReader) o;
            line = reader.getLine();
            lineNumber = reader.getLineNumber();
            if(BEGIN_MESSAGE_PATTERN.matcher(line).find() ) {
                flag = true;
                currentLineIndex = reader.getLineNumber();
                map.put(currentLineIndex, new ArrayList<>());
            }
            if(flag && INTERMEDIATE_MESSAGE_PATTERN.matcher(line).find()) {
                map.get(currentLineIndex).add(line);
            }
            if(flag && END_MESSAGE_PATTERN.matcher(line).find() && map.get(currentLineIndex).size() > 1) {
                flag = false;
            }
        }
    }

    @Override
    public void console() {
        Main.printErrorMap(map);
    }
}
