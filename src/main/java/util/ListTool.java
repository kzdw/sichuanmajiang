package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTool {
    /**
     * 两个list是否一样（忽略顺序）
     *
     * @param list
     * @param list1
     * @author Henry Zhou
     * @return
     */
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

}
