package bxtr;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    if(args.length != 2) {
	        System.out.println("Первый передаваемый параметр путь к файлу, второй строка для поиска.");
	        return;
        }
        printMap(LogSearch.build().file(args[0]).search(args[1]));
    }


    private static void printMap(Map<Integer, String> map) {
        for(Integer key : map.keySet()) {
            System.out.println(String.format("#%d %s", key, map.get(key)));
        }
    }
}
