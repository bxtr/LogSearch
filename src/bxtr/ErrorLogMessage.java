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
            while ((line = reader.readLine()) != null) {
                if(beginPattern.matcher(line).find()) {
                    int currentLineIndex = lineIndex;
                    map.put(currentLineIndex, new ArrayList<>());
                    map.get(currentLineIndex).add(line);
                    while ((line = reader.readLine()) != null &&
                            !endPattern.matcher(line).find()){
                        map.get(currentLineIndex).add(line);
                        lineIndex++;
                    }
                }
                lineIndex++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return map;
    }
}
