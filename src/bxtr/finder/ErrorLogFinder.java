package bxtr.finder;

import java.util.regex.Pattern;

/**
 * Поисковик сообщений об ошибке.
 * Created by bxtr on 01.02.2017.
 */
public class ErrorLogFinder extends AbstractFinder {
    private static final String BEGIN_MESSAGE_PATTERN = ".*ERROR.*";
    private static final String END_MESSAGE_PATTERN = ".*(INFO|DEBUG).*";

    public ErrorLogFinder() {
        super(Pattern.compile(BEGIN_MESSAGE_PATTERN), Pattern.compile(END_MESSAGE_PATTERN));
    }
}
