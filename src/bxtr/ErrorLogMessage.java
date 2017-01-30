package bxtr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by basic on 29.01.2017.
 */
public class ErrorLogMessage {
    private static final String BEGIN_MESSAGE_PATTERN = ".*ERROR.*";
    private static final String END_MESSAGE_PATTERN = ".*(INFO|DEBUG).*";

    public Map<Integer, List<String>> find(String filename) {
        Map<Integer, List<String>> map = new HashMap<>();
        if(filename == null) {
            return map;
        }
        Pattern beginPattern = Pattern.compile(BEGIN_MESSAGE_PATTERN);
        Pattern endPattern = Pattern.compile(END_MESSAGE_PATTERN);
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineIndex = 1;
            boolean flag = false;
            int currentLineIndex = 0;
            while ((line = reader.readLine()) != null) {
                if(beginPattern.matcher(line).find()) {
                    flag = true;
                    currentLineIndex = lineIndex;
                    map.put(currentLineIndex, new ArrayList<>());
                }
                if(endPattern.matcher(line).find()) {
                    flag = false;
                }
                if(flag) {
                    map.get(currentLineIndex).add(line);
                }
                if(beginPattern.matcher(line).find() && map.get(currentLineIndex).size() > 1) {
                    currentLineIndex = lineIndex;
                    map.put(currentLineIndex, new ArrayList<>());
                }
                lineIndex++;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }
}
