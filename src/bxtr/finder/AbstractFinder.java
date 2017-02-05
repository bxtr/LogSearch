package bxtr.finder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Абстрактный класс, реализующий поиск по заданным паттернам.
 * Created by bxtr on 01.02.2017.
 */
public abstract class AbstractFinder {

    Pattern beginPattern;
    Pattern endPattern;

    AbstractFinder(Pattern beginPattern, Pattern endPattern) {
        this.beginPattern = beginPattern;
        this.endPattern = endPattern;
    }

    public Map<Integer, List<String>> find(String filename) {
        Map<Integer, List<String>> map = new HashMap<>();
        if(filename == null) {
            return map;
        }
        try(LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(filename)))) {
            String line;
            boolean flag = false;
            int currentLineIndex = 0;
            while ((line = reader.readLine()) != null) {
                if(beginPattern.matcher(line).find()) {
                    flag = true;
                    currentLineIndex = reader.getLineNumber();
                    map.put(currentLineIndex, new ArrayList<>());
                }
                if(flag) {
                    map.get(currentLineIndex).add(line);
                }
                if(endPattern.matcher(line).find()) {
                    flag = false;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }
}
