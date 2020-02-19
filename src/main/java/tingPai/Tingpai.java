package tingPai;

import tingPai.entities.AllTingpai;
import tingPai.entities.TingDetail;

import java.util.Arrays;
import java.util.List;

/**
 * 听牌抽象类
 * 筒：1-9
 * 条：11-19
 * 万：21-29
 */
public interface Tingpai {
    List<Integer> allDistintPai = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            21, 21, 23, 24, 25, 26, 27, 28, 29
    );
    List<Integer> tong = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9
    );
    List<Integer> tiao = Arrays.asList(
            11, 12, 13, 14, 15, 16, 17, 18, 19
    );
    List<Integer> wan = Arrays.asList(
            21, 21, 23, 24, 25, 26, 27, 28, 29
    );

    /**
     * 是否已经胡牌
     *
     * @param shoupai 手牌(已经把当前需要判断的牌插入进来)
     * @return 是否胡牌
     */
    List<TingDetail> isTingCurrentPai(List<Integer> shoupai, Integer currentPai);

    /**
     * 听牌有哪些
     * @param shoupai 手牌
     * @return 听牌有哪些
     */
    AllTingpai tingPais(List<Integer> shoupai);

    /**
     * 是否听牌
     * @param shoupai 手牌（未把待停牌插入，即参数为1,4,7,10,13张）
     * @return
     */
    boolean isTingPai(List<Integer> shoupai);


    /**
     * 是否胡牌
     * @param shoupai 手牌（已经把待停牌插入，即参数为2,5,8,11,14张）
     * @return
     */
    boolean isHuPai(List<Integer> shoupai);
}
