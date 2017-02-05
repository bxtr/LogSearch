package bxtr.finder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Абстрактный класс, реализующий поиск по заданным паттернам.
 * Created by bxtr on 01.02.2017.
 */
public class SimpleReader extends Observable implements Reader {

    Pattern beginPattern;
    Pattern endPattern;

    private int lineNumber;
    private String line;

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void read(String filename) {
        if(filename == null) {
            return;
        }
        try(LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(filename)))) {
            while ((line = reader.readLine()) != null) {
                lineNumber = reader.getLineNumber();
                this.setChanged();
                this.notifyObservers();
            }
            /*
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
            }*/
        } catch (IOException exception) {
            System.err.println("SimpleFinder.find() Exception.");
            exception.printStackTrace();
        }
    }
}
