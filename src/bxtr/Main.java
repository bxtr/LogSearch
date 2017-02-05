package bxtr;


import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    if(args.length != 2) {
	        System.out.println("Первый передаваемый параметр путь к файлу, второй строка для поиска.");
	        return;
        }
      //  printErrorMap(LogSearch.build().file(args[0]).find(new SubstringFinder(args[1])));
    }


    public static void printMap(Map<Integer, String> map) {
        for(Integer key : map.keySet()) {
            System.out.println(String.format("#%d %s", key, map.get(key)));
        }
    }

    public static void printErrorMap(Map<Integer, List<String>> map) {
        for(Integer key : map.keySet()) {
            System.out.println(String.format("#%d", key));
            for(String message : map.get(key)) {
                System.out.println(message);
            }
            System.out.println();
        }
    }
}
