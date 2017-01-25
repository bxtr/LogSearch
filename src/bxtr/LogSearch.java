package bxtr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для поиска нужных строк в логах.
 * Created by bxtr on 25.01.2017.
 */
public class LogSearch {
    private String filename = null;

    private LogSearch() {
        //empty
    }

    /**
     * Возращает экземпляр класс.
     * @return
     */
    public static LogSearch build() {
        return new LogSearch();
    }

    /**
     * Метод осуществляющий поиск заданной строки в документе.
     * @param stringToFind нужная строка
     * @return мапу, где ключ номер строки, а значение сообщение в этой строке.
     */
    public Map<Integer, String> search(String stringToFind) {
        Map<Integer, String> map = new HashMap<>();
        if(filename == null || stringToFind == null) {
            return map;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineIndex = 1;
            while ((line = reader.readLine()) != null) {
                if(line.contains(stringToFind)) {
                    map.put(lineIndex, line);
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

    /**
     * Фабричный метод задающий файл, в котором будет вестись поиск.
     * @param filename полный путь до файла.
     * @return
     */
    public LogSearch file(String filename) {
        this.filename = filename;
        return this;
    }
}
