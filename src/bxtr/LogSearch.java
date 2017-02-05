package bxtr;


import bxtr.finder.ConsoleOutput;
import bxtr.finder.Reader;
import bxtr.finder.SimpleReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Класс для поиска нужных строк в логах.
 * Created by bxtr on 25.01.2017.
 */
public class LogSearch {
    private String filename = null;
    private Reader reader;
    private Observable observable;
    private List<Observer> observerList;

    public LogSearch(Observable observable) {
        if (observable instanceof Reader) {
            reader = (Reader) observable;
        } else {
            reader = new SimpleReader();
        }
        this.observable = observable;
        observerList = new ArrayList<>();
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

    public LogSearch find(Observer observer) {
        observable.addObserver(observer);
        observerList.add(observer);
        return this;
    }

    public void consoleLog() {
        for (Observer observer : observerList) {
            if (observer instanceof ConsoleOutput) {
                ((ConsoleOutput) observer).console();
            }
        }
    }

    public LogSearch execute() {
        if (filename != null) {
            reader.read(filename);
        }
        return this;
    }
}
