package bxtr.finder;

import java.util.List;
import java.util.Map;

/**
 * Created by bxtr on 05.02.2017.
 */
public interface Filter {

    Map<Integer, List<String>> filter(Map<Integer, List<String>> messages);
}
