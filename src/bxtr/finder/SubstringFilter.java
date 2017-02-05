package bxtr.finder;

import bxtr.Main;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Поисковик заданной подстроки.
 * Created by bxtr on 01.02.2017.
 */
public class SubstringFilter implements Observer, ConsoleOutput, Filter{
    public final Pattern SUBSTRING;

    private Map<Integer, List<String>> map;
    private Map<Integer, List<String>> filterMap;

    public SubstringFilter(String stringToFind){
        SUBSTRING = Pattern.compile(stringToFind);
        map = new HashMap<>();
    }

    @Override
    public void console() {
        if(filterMap == null) {
            Main.printErrorMap(map);
        }
        Main.printErrorMap(filterMap);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SimpleReader) {
            SimpleReader simpleReader = (SimpleReader) o;
            String line = simpleReader.getLine();
            if (SUBSTRING.matcher(line).find()) {
                int lineNumber = simpleReader.getLineNumber();
                map.put(lineNumber, new ArrayList<>());
                map.get(lineNumber).add(line);
            }
        }
    }

    @Override
    public Map<Integer, List<String>> filter(Map<Integer, List<String>> messages) {
        filterMap = new HashMap<>();
        for(Integer lineIndex : messages.keySet()) {
            if (map.containsKey(lineIndex)) {
                if(!filterMap.containsKey(lineIndex)) {
                    filterMap.put(lineIndex, new ArrayList<>());
                }
                filterMap.put(lineIndex, messages.get(lineIndex));
            }
        }
        return filterMap;
    }
}
