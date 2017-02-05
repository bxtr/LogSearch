package bxtr.finder;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

/**
 * Поисковик сообщений об ошибке.
 * Created by bxtr on 01.02.2017.
 */
public class ErrorLogFinder implements Observer {
    private static final String BEGIN_MESSAGE_PATTERN = ".*ERROR.*";
    private static final String END_MESSAGE_PATTERN = ".*(INFO|DEBUG).*";
/*
    public ErrorLogFinder() {
        super(Pattern.compile(BEGIN_MESSAGE_PATTERN), Pattern.compile(END_MESSAGE_PATTERN));
    }*/

    @Override
    public void update(Observable o, Object arg) {

    }
}
