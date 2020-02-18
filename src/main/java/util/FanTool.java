package util;

import fan.entities.Allshoupai;
import fan.entities.Gang;
import tingPai.PaiType;
import tingPai.SichuanTingpai;
import tingPai.Tingpai;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 算番工具类
 * @author Henry Zhou
 */
public class FanTool {


    /**
     * 是否是大对子
     *
     * @param allshoupai 手牌
     * @return
     */
    public static boolean isDaduizi(Allshoupai allshoupai) {
        allshoupai.findShoupaiExceptJiang();

        List<Integer> shouPaiCopy = allshoupai.getShoupai().stream().collect(Collectors.toList());
        Map<Integer, IntSummaryStatistics> groups = shouPaiCopy.stream()
                .collect(Collectors.groupingBy(singlePai -> singlePai, Collectors.summarizingInt(singlePai -> (int) singlePai)));
        int countJiang = 0;
        for (Integer key : groups.keySet()) {
            IntSummaryStatistics value = groups.get(key);
            if (value.getCount() != 3 && value.getCount() != 2) {
                return false;
            }
            if (value.getCount() == 2) {
                countJiang++;
            }
        }
        if (countJiang != 1) {
            return false;
        }
        return true;
    }

    /**
     * 是否是小七对
     *
     * @param allshoupai
     * @return
     */
    public static boolean isXiaoQiDui(Allshoupai allshoupai) {
        if(allshoupai.getShoupai().size()!=14 || allshoupai.getGangs().size()>0 || allshoupai.getPengs().size()>0){
            return false;
        }
        List<Integer> shouPaiCopy = allshoupai.getShoupai().stream().collect(Collectors.toList());
        Map<Integer, IntSummaryStatistics> groups = shouPaiCopy.stream()
                .collect(Collectors.groupingBy(singlePai -> singlePai, Collectors.summarizingInt(singlePai -> (int) singlePai)));
        for (Integer key : groups.keySet()) {
            IntSummaryStatistics value = groups.get(key);
            if (value.getCount() != 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 找到手牌中有多少个杠
     *
     * @param allshoupai 手牌
     * @return
     */
    public static List<Gang> findGangs(Allshoupai allshoupai) {
        List<Gang> gangs = new ArrayList<>();
        List<Integer> allpais = allshoupai.findAllPais();
        List<Integer> distinctShoupai = allpais.stream().distinct().collect(Collectors.toList());
        for (Integer singleDistinctPai : distinctShoupai) {
            List<Integer> gangPre = allpais.stream()
                    .filter(singlePai -> singlePai.equals(singleDistinctPai))
                    .collect(Collectors.toList());
            if (gangPre.size() == 4) {
                Gang gang = new Gang(gangPre);
                gangs.add(gang);
            }
        }
        return gangs;
    }

    /**
     * 是否是指定类型的一条龙
     *
     * @param shoupai 手牌
     * @param paiType 指定的类型
     * @return
     */
    public static boolean yiTiaoLongSpecifiedType(List<Integer> shoupai, PaiType paiType) {
        List<Integer> specifiedType = new ArrayList<>();
        switch (paiType) {
            case TONG:
                specifiedType = Tingpai.tong;
                break;
            case TIAO:
                specifiedType = Tingpai.tiao;
                break;
            case WAN:
                specifiedType = Tingpai.wan;
                break;
        }
        if (removePaisStillHu(shoupai, specifiedType)) return true;
        return false;
    }


    /**
     * 是否是指定类型的夹心五
     *
     * @param shoupai 手牌
     * @param paiType 指定的类型
     * @return
     */
    public static boolean jiaXinWu(List<Integer> shoupai, PaiType paiType) {
        List<Integer> specifiedType = new ArrayList<>();
        switch (paiType) {
            case TONG:
                specifiedType = Arrays.asList(4, 5, 6);
                break;
            case TIAO:
                specifiedType = Arrays.asList(14, 15, 16);
                break;
            case WAN:
                specifiedType = Arrays.asList(24, 25, 26);
                break;
        }
        if (removePaisStillHu(shoupai, specifiedType)) return true;
        return false;
    }


    /**
     * 是否是卡二条
     *
     * @param shoupai 手牌
     * @return
     */
    public static boolean kaErTiao(List<Integer> shoupai) {
        List<Integer> specifiedType = Arrays.asList(11, 12, 13);
        if (removePaisStillHu(shoupai, specifiedType)) return true;
        return false;
    }

    /**
     * 移除了指定的牌是否任然胡牌
     *
     * @param shoupai  手牌
     * @param toRemove 移除牌集合
     * @return
     */
    private static boolean removePaisStillHu(List<Integer> shoupai, List<Integer> toRemove) {
        SichuanTingpai sichuanTingpai = new SichuanTingpai();
        if (checkAllAvailble(shoupai, toRemove)) {
            List<Integer> removedTongResult = removeSpecifiedPais(shoupai, toRemove);
            if (sichuanTingpai.isHuPai(removedTongResult)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 从手牌中移除指定的牌（如果出现多次只移除一次）
     *
     * @param shoupai  手牌
     * @param toRomove 需要移除的牌集合
     * @return 移除后的牌（新对象）
     */
    private static List<Integer> removeSpecifiedPais(List<Integer> shoupai, List<Integer> toRomove) {
        List<Integer> result = shoupai.stream().collect(Collectors.toList());
        for (Integer singlePai : toRomove) {
            int indexOfSinglePai = result.indexOf(singlePai);
            if (indexOfSinglePai != -1) {
                result.remove(indexOfSinglePai);
            } else {
                throw new IllegalStateException("需要移除的牌".concat(String.valueOf(singlePai)).concat("未找到"));
            }

        }
        return result;

    }

    /**
     * 检查特定牌集合中的所有元素是否在手牌中出现
     *
     * @param shoupai   手牌
     * @param toBeCheck 需要检查的牌
     * @return 是否出现
     */
    private static boolean checkAllAvailble(List<Integer> shoupai, List<Integer> toBeCheck) {
        for (Integer singlePai : toBeCheck) {
            int indexOfSinglePai = shoupai.indexOf(singlePai);
            if (indexOfSinglePai < 0) {
                return false;
            }
        }
        return true;
    }

}
