package tingPai;

import tingPai.entities.*;
import util.TingpaiTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 四川麻将听牌类
 * @author Henry Zhou
 */
public class SichuanTingpai implements Tingpai {
    @Override
    public List<TingDetail> isTingCurrentPai(List<Integer> shoupai, Integer currentPai) {
        List<Integer> distinctShoupai = shoupai.stream().distinct().collect(Collectors.toList());
        List<TingDetail> tingDetails = new ArrayList<>();
        for (int i = 0; i < distinctShoupai.size(); i++) {
            int toRomoveCopule = distinctShoupai.get(i);
            List<Integer> removedCoupleShoupai = TingpaiTool.findshoupaiWithouCouple(shoupai, toRomoveCopule);
            if (removedCoupleShoupai.size() < shoupai.size()) {
                removedCoupleShoupai = removedCoupleShoupai.stream().sorted().collect(Collectors.toList());
                TingDetail tingDetailPre = hupaiJudgePre(currentPai, toRomoveCopule, removedCoupleShoupai);
                if (Objects.nonNull(tingDetailPre)) {
                    tingDetails.add(tingDetailPre);
                }
                RemoveTripleResult removeTripleResult = TingpaiTool.findShoupaiWithoutTriple(removedCoupleShoupai);
                if (removeTripleResult.getTriples().size() != 0) {
                    TingDetail tingDetail = hupaiJudge(currentPai, toRomoveCopule, removeTripleResult);
                    if (Objects.nonNull(tingDetail)) {
                        tingDetails.add(tingDetail);
                    }
                }

            }
        }
        return tingDetails;
    }


    /**
     * 计算胡牌（已经移除将对及三个）
     *
     * @param currentPai         当前判断的牌
     * @param toRomoveCopule     将对
     * @param removeTripleResult 手牌（已经移除将对及三个）
     * @return
     */
    private TingDetail hupaiJudge(Integer currentPai, int toRomoveCopule, RemoveTripleResult removeTripleResult) {
        RemoveTripleContinuesResult isAllTripleContinues = TingpaiTool.isAlltripleContinues(removeTripleResult.getResult());
        if (isAllTripleContinues.isAlltripleContinues()) {
            isAllTripleContinues.getTriples().addAll(removeTripleResult.getTriples());
            TingDetail tingDetail = new TingDetail(
                    isAllTripleContinues.getTriples(),
                    new DoublePia(Arrays.asList(toRomoveCopule, toRomoveCopule)),
                    currentPai,
                    true
            );
            return tingDetail;
        }
        return null;
    }

    /**
     * 初步计算胡牌（未移除三个相同的）
     *
     * @param currentPai           当前判断的牌
     * @param toRomoveCopule       将对
     * @param removedCoupleShoupai 手牌（已经移除将对）
     * @return
     */
    private TingDetail hupaiJudgePre(Integer currentPai, int toRomoveCopule, List<Integer> removedCoupleShoupai) {
        RemoveTripleContinuesResult removeTripleContinuesResult = TingpaiTool.isAlltripleContinues(removedCoupleShoupai);
        if (removeTripleContinuesResult.isAlltripleContinues()) {
            TingDetail tingDetail = new TingDetail(
                    removeTripleContinuesResult.getTriples(),
                    new DoublePia(Arrays.asList(toRomoveCopule, toRomoveCopule)),
                    currentPai,
                    true
            );
            return tingDetail;
        }
        return null;
    }

    @Override
    public AllTingpai tingPais(List<Integer> shoupai) {
        if (shoupai.size() % 3 != 1) {
            throw new IllegalArgumentException("手牌数量必须为1,4,7,10,13张");
        }
        if (!allDistintPai.containsAll(shoupai)) {
            throw new IllegalArgumentException("手牌中包含了未知牌");
        }

        if (isThreeKindsAvailable(shoupai)) {
            return new AllTingpai();
        }

        List<Integer> tingpais = new ArrayList<>();
        List<TingDetail> tingDetailsAll = new ArrayList<>();
        //遍历所有牌
        for (Integer singlePai : allDistintPai) {
            List<Integer> conbinedShouPai = new ArrayList<>();
            conbinedShouPai.addAll(shoupai);
            conbinedShouPai.add(singlePai);
            List<TingDetail> tingDetails = this.isTingCurrentPai(conbinedShouPai, singlePai);
            tingDetailsAll.addAll(tingDetails);
            if (tingDetails.size() > 0) {
                tingpais.add(singlePai);
            }
        }
        //特殊处理七对
        int remainInt = TingpaiTool.removeAllCouples(shoupai);
        if (remainInt != -1) {
            if (!tingpais.contains(remainInt)) {
                tingpais.add(remainInt);
            }
            List<Integer> qidui = new ArrayList<>();
            qidui.addAll(shoupai);
            qidui.add(remainInt);
            TingDetail tingDetailQiDui = new TingDetail(remainInt, true, qidui.stream().sorted().collect(Collectors.toList()));
            tingDetailsAll.add(tingDetailQiDui);
        }
        AllTingpai allTingpai = new AllTingpai(tingpais, tingDetailsAll);

        return allTingpai;
    }

    @Override
    public boolean isTingPai(List<Integer> shoupai) {
        AllTingpai allTingpai = this.tingPais(shoupai);
        if (allTingpai.getTingPais().size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isHuPai(List<Integer> shoupai) {
        List<TingDetail> tingDetails = this.isTingCurrentPai(shoupai, shoupai.get(0));
        if (tingDetails.size() > 0) {
            return true;
        }
        return false;
    }


    private boolean isThreeKindsAvailable(List<Integer> shoupai) {
        List<Integer> tongs = shoupai.stream().filter(single -> tong.contains(single)).collect(Collectors.toList());
        List<Integer> tiaos = shoupai.stream().filter(single -> tiao.contains(single)).collect(Collectors.toList());
        List<Integer> wans = shoupai.stream().filter(single -> wan.contains(single)).collect(Collectors.toList());
        if (tongs.size() > 0 && tiaos.size() > 0 && wans.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
