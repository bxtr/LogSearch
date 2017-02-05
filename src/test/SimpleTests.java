package test;

import bxtr.LogSearch;
import bxtr.finder.ErrorLogFinder;
import bxtr.finder.SimpleReader;
import bxtr.finder.SubstringFinder;
import org.junit.Test;

/**
 * Created by bxtr on 25.01.2017.
 */
public class SimpleTests {

    public static final String TEST_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\log.log";
    public static final String TEST_SERVER_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\server_log.log";
    public static final String TEST_APPLICATION_LOG_FILE = "C:\\Users\\basic\\IdeaProjects\\LogSearch\\application.log";

    @Test
    public void ПоискЗаданнойСтрокиВФайлеНеСодержащемЕе() {
            LogSearch logSearch = new LogSearch(new SimpleReader()).file(TEST_LOG_FILE)
                    .find(new SubstringFinder("Exception")).execute();
            logSearch.consoleLog();
       // Assert.assertEquals(0, map.size());
    }

    @Test
    public void ПоискЗаданнойСтрокиВФайлеСодержащемЕе() {
        LogSearch logSearch = new LogSearch(new SimpleReader()).file(TEST_LOG_FILE)
                .find(new SubstringFinder("filename")).execute();
        logSearch.consoleLog();
    //    Main.printErrorMap(map);
     //   Assert.assertEquals(1, map.size());
    }

    @Test
    public void ПоискERRORСообщенийВЛоге() {
        LogSearch logSearch = new LogSearch(new SimpleReader()).file(TEST_APPLICATION_LOG_FILE)
                .find(new ErrorLogFinder()).execute();
        logSearch.consoleLog();
        //Assert.assertTrue(map.size() == 12);
    }
}
