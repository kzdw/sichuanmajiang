package util;

import tingPai.entities.RemoveTripleContinuesResult;
import tingPai.entities.RemoveTripleResult;
import tingPai.entities.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 听牌工具类
 * @author Henry Zhou
 */
public class TingpaiTool {
    /**
     * 三个数是否连续
     *
     * @param threeInt 三个数
     * @return 是否连续
     */
    public static boolean isThreeIntContinus(List<Integer> threeInt) {
        if (threeInt.size() != 3) {
            throw new IllegalArgumentException("参数必须为三个");
        }
        threeInt = threeInt.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < threeInt.size(); i++) {
            int currentInt = threeInt.get(i);
            int nextInt;
            if (i < threeInt.size() - 1) {
                nextInt = threeInt.get(i + 1);
            } else {
                return true;
            }
            if ((nextInt - currentInt) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断单个数是否相同
     *
     * @param threeInt 三个整数
     * @return 是否相同
     */
    public static boolean isThreeIntTheSame(List<Integer> threeInt) {
        if (threeInt.size() != 3) {
            throw new IllegalArgumentException("参数必须为三个");
        }
        threeInt = threeInt.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < threeInt.size(); i++) {
            int currentInt = threeInt.get(i);
            int nextInt;
            if (i < threeInt.size() - 1) {
                nextInt = threeInt.get(i + 1);
            } else {
                return true;
            }
            if ((nextInt - currentInt) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 移除指定的一对
     *
     * @param shoupai   手牌
     * @param removeInt 需要移除的值
     * @return 已经移除对子的手牌
     */
    public static List<Integer> findshoupaiWithouCouple(List<Integer> shoupai, int removeInt) {
        List<Integer> result = shoupai.stream().sorted().collect(Collectors.toList());
        int count = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
            if (result.get(i) == removeInt) {
                result.remove(i);
                count++;
                if (count == 2) {
                    return result;
                }
            }
        }
        return shoupai.stream().sorted().collect(Collectors.toList());
    }


    /**
     * 移除指定的三个
     *
     * @param shoupai 手牌
     * @return 已经移除三个的手牌
     */
    public static RemoveTripleResult findShoupaiWithoutTriple(List<Integer> shoupai) {
        List<Triple> triples = new ArrayList<>();
        List<Integer> result = shoupai.stream().sorted().collect(Collectors.toList());
        RemoveTripleResult removeTripleResult = new RemoveTripleResult(result, triples);
        for (int i = result.size() - 3; i >= 0; i--) {
            if (result.get(i) == result.get(i + 1) && result.get(i) == result.get(i + 2)) {
                Triple triple = new Triple(Arrays.asList(result.get(i), result.get(i), result.get(i)));
                triples.add(triple);
                result.remove(i + 2);
                result.remove(i + 1);
                result.remove(i);
                i = i - 2;
            }
        }
        return removeTripleResult;
    }

    /**
     * 对手牌三个三个进行分组
     *
     * @param shoupai 手牌
     * @return 三个三个进行分组
     */
    public static List<List<Integer>> splitTriples(List<Integer> shoupai) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < shoupai.size(); i++) {
            current.add(shoupai.get(i));
            if ((i + 1) % 3 == 0) {
                List<Integer> temp = new ArrayList<>();
                temp.addAll(current);
                result.add(temp);
                current.clear();
            }


        }
        return result;
    }

    /**
     * 移除所有的对子，返回剩下的单个
     *
     * @param shoupaiOrg 原始手牌
     * @return 返回剩下的单个
     */
    public static Integer removeAllCouples(List<Integer> shoupaiOrg) {

        List<Integer> shoupai = new ArrayList<>();
        shoupai.addAll(shoupaiOrg.stream().sorted().collect(Collectors.toList()));
        for (int i = shoupai.size() - 1; i >= 0; i--) {
            int currentInt = shoupai.get(i);
            if (i > 0 && currentInt == shoupai.get(i - 1)) {
                shoupai.remove(i);
                shoupai.remove(i - 1);
                i--;
            }
        }
        if (shoupai.size() == 1) {
            return shoupai.get(0);
        } else {
            return -1;
        }
    }


    /**
     * 以第一个开头，移除递增的数；例如第一个为2，那么移除2,3,4
     *
     * @param sortedShoupais 手牌
     */
    private static void removeAlltripleContinues(List<Integer> sortedShoupais, List<Triple> triples) {
        if (sortedShoupais.size() > 0) {
            int first = sortedShoupais.get(0);
            int secondIndex = sortedShoupais.indexOf(first + 1);
            int thirdIndex = sortedShoupais.indexOf(first + 2);
            if (secondIndex != -1 && thirdIndex != -1) {
                List<Integer> integers = new ArrayList<>();
                Triple triple = new Triple(integers);
                triples.add(triple);
                integers.add(sortedShoupais.get(0));
                if (thirdIndex > secondIndex) {
                    integers.add(sortedShoupais.get(secondIndex));
                    integers.add(sortedShoupais.get(thirdIndex));
                    sortedShoupais.remove(thirdIndex);
                    sortedShoupais.remove(secondIndex);
                } else {
                    integers.add(sortedShoupais.get(thirdIndex));
                    integers.add(sortedShoupais.get(secondIndex));
                    sortedShoupais.remove(secondIndex);
                    sortedShoupais.remove(thirdIndex);
                }

                sortedShoupais.remove(0);
                removeAlltripleContinues(sortedShoupais, triples);
            }


        }

    }

    /**
     * 是否手牌全为三张连续
     *
     * @param shoupais 手牌
     * @return
     */
    public static RemoveTripleContinuesResult isAlltripleContinues(List<Integer> shoupais) {
        List<Integer> sortedShoupai = shoupais.stream().collect(Collectors.toList());
        List<Triple> triples = new ArrayList<>();
        removeAlltripleContinues(sortedShoupai,triples);

        RemoveTripleContinuesResult removeTripleContinuesResult = new RemoveTripleContinuesResult(
                sortedShoupai.size() == 0,
                triples
        );
        return removeTripleContinuesResult;
    }

}
