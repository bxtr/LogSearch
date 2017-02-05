package test;
import bxtr.Main;
import bxtr.finder.ErrorLogFinder;
import bxtr.LogSearch;
import bxtr.finder.SubstringFinder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by bxtr on 25.01.2017.
 */
public class SimpleTests {

    public static final String TEST_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\log.log";
    public static final String TEST_SERVER_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\server_log.log";
    public static final String TEST_APPLICATION_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\application.log";

    @Test
    public void ПоискЗаданнойСтрокиВФайлеНеСодержащемЕе() {
        Map<Integer, List<String>> map = LogSearch.build().file(TEST_LOG_FILE)
                .find(new SubstringFinder("Exception"));
        Main.printErrorMap(map);
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void ПоискЗаданнойСтрокиВФайлеСодержащемЕе() {
        Map<Integer, List<String>> map = LogSearch.build().file(TEST_LOG_FILE)
                .find(new SubstringFinder("filename"));
        Main.printErrorMap(map);
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void ПоискERRORСообщенийВЛоге() {
        Map<Integer, List<String>> map = LogSearch.build().file(TEST_APPLICATION_LOG_FILE)
                .find(new ErrorLogFinder());
        Main.printErrorMap(map);
        Assert.assertTrue(map.size() == 12);
    }
}
