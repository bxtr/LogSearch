package bxtr.finder;

import java.util.regex.Pattern;

/**
 * Поисковик заданной подстроки.
 * Created by bxtr on 01.02.2017.
 */
public class SubstringFinder extends AbstractFinder {
    public SubstringFinder(String stringToFind) {
        super(Pattern.compile(".*" + stringToFind + ".*"), Pattern.compile(".*" + stringToFind + ".*"));
    }
}
