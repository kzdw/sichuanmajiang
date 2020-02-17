package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTool {

    public static boolean checkDiffrent(List<Integer> list, List<Integer> list1) {
        long st = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<>(list.size() + list1.size());
        if (list.size() != list1.size()) {
            return false;
        }
        for (Integer str : list) {
            map.put(str, 1);
        }
        for (Integer str : list1) {
            Integer cc = map.get(str);
            if (null != cc) {
                continue;
            }
            return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        ListTool listTool = new ListTool();
//      boolean is =  listTool.checkDiffrent(Arrays.asList(1,2,3),Arrays.asList(1,3,4));
//      System.out.println(is);
//    }

}
