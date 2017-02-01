package bxtr;

import bxtr.finder.AbstractFinder;

import java.util.HashMap;
import java.util.List;
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
     * Фабричный метод задающий файл, в котором будет вестись поиск.
     * @param filename полный путь до файла.
     * @return
     */
    public LogSearch file(String filename) {
        this.filename = filename;
        return this;
    }

    /**
     * Метод делегирующий поиск поисковику.
     * @param finder поисковик
     * @return мапа ключ - строчка вхождения, значение - найденные сообщения.
     */
    public Map<Integer, List<String>> find(AbstractFinder finder) {
        if (filename == null) {
            return new HashMap<>();
        }
        return finder.find(filename);
    }
}
