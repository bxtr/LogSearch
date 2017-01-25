package test;
import bxtr.LogSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by bxtr on 25.01.2017.
 */
public class SimpleTests {

    public static final String TEST_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\log.log";

    @Test
    public void ПоискЗаданнойСтрокиВФайлеНеСодержащемЕе() {
        Map<Integer, String> map = LogSearch.build().file(TEST_LOG_FILE).search("Exception");
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void ПоискЗаданнойСтрокиВФайлеСодержащемЕе() {
        Map<Integer, String> map = LogSearch.build().file(TEST_LOG_FILE).search("filename");
        Assert.assertEquals(1, map.size());
    }
}
