package fan;

import fan.entities.Allshoupai;
import fan.entities.FanEntity;
import fan.entities.Gang;
import fan.entities.SingleFan;
import tingPai.PaiType;
import tingPai.SichuanTingpai;
import tingPai.Tingpai;
import tingPai.entities.TingDetail;
import tingPai.entities.Triple;
import util.FanTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 四川麻将番算法类
 *
 * @author Henry Zhou
 */
public class SichuanFan implements Fan {
    @Override
    public FanEntity findFan(Allshoupai allshoupai, List<ConfigFan> configFans) {
        allshoupai.checkHuedInShoupai();
        int totalFans = 0;
        List<SingleFan> fans = new ArrayList<>();
        for (ConfigFan configFan : configFans) {
            Integer currentFan = 0;
            if (configFan.isValid()) {
                switch (configFan.getFanType()) {
                    case GANG:
                        currentFan = this.gang(allshoupai) * configFan.getSingleFan();
                        break;
                    case DA_DUI_ZI:
                        currentFan = this.daDuiZi(allshoupai) * configFan.getSingleFan();
                        break;
                    case QING_YI_SE:
                        currentFan = this.qingYiSe(allshoupai) * configFan.getSingleFan();
                        break;
                    case JIA_XIN_WU:
                        currentFan = this.jiaXinWu(allshoupai) * configFan.getSingleFan();
                        break;
                    case KA_ER_TIAO:
                        currentFan = this.kaErTiao(allshoupai) * configFan.getSingleFan();
                        break;
                    case JIN_GOU_DIAO:
                        currentFan = this.jinGouDiao(allshoupai) * configFan.getSingleFan();
                        break;
                    case MEN_QING:
                        currentFan = this.menQing(allshoupai) * configFan.getSingleFan();
                        break;
                    case YI_JIU_JIANG_DUI:
                        currentFan = this.yiJiuJiangDui(allshoupai) * configFan.getSingleFan();
                        break;
                    case BAN_BAN_GAO:
                        currentFan = this.banBanGao(allshoupai) * configFan.getSingleFan();
                        break;
                    case YI_TIAO_LONG:
                        currentFan = this.yiTiaoLong(allshoupai) * configFan.getSingleFan();
                        break;
                    case XIAO_QI_DUI:
                        currentFan = this.xiaoQiDui(allshoupai) * configFan.getSingleFan();
                        break;
                }
                SingleFan singleFan = new SingleFan(configFan.getFanType(), currentFan);
                if (currentFan != 0) {
                    fans.add(singleFan);
                    totalFans += currentFan;
                }
            }
        }
        FanEntity fanEntity = new FanEntity(
                fans,
                totalFans
        );
        return fanEntity;
    }


    /**
     * 幺九将对
     *
     * @param allshoupai 手牌
     * @return 几个幺九将对
     */
    private Integer yiJiuJiangDui(Allshoupai allshoupai) {
        List<Integer> allPais = allshoupai.findAllPais();
        Long count = allPais.stream().filter(singlePai -> Arrays.asList(1, 9, 11, 19, 21, 29).contains(singlePai)).count();
        if (count == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 门清
     *
     * @param allshoupai 手牌
     * @return 几个门清
     */
    private Integer menQing(Allshoupai allshoupai) {
        if (allshoupai.getShoupai().size() == 14) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 小七对
     *
     * @param allshoupai 手牌
     * @return 几个小七对
     */
    private Integer xiaoQiDui(Allshoupai allshoupai) {
        if (FanTool.isXiaoQiDui(allshoupai)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 板板高
     *
     * @param allshoupai 手牌
     * @return 几个板板高
     */
    private Integer banBanGao(Allshoupai allshoupai) {
        SichuanTingpai sichuanTingpai = new SichuanTingpai();
        List<TingDetail> tingDetails = sichuanTingpai.isTingCurrentPai(allshoupai.getShoupai(), allshoupai.getShoupai().get(0));
        Integer countBanBanGao = 0;
        for (TingDetail tingDetail : tingDetails) {
            List<Triple> triples = tingDetail.getTriples()
                    .stream()
                    .filter(triple -> !triple.threeIntTheSame())
                    .collect(Collectors.toList());
            if (triples.size() == 4) {
                Long count = triples.stream().filter(triple -> triple.sameTriple(triples.get(0))).count();
                if (count == 4) {
                    countBanBanGao = 2;
                }
            }
            if (singleBanbanGao(triples) && countBanBanGao != 2) {
                countBanBanGao = 1;
            }
        }
        return countBanBanGao;
    }


    /**
     * 是否是清一色
     *
     * @param allshoupai 手牌
     * @return 几个清一色
     */
    private Integer qingYiSe(Allshoupai allshoupai) {
        List<Integer> allPai = allshoupai.findAllPais();
        if (Tingpai.tong.containsAll(allPai) ||
                Tingpai.tiao.containsAll(allPai) ||
                Tingpai.wan.containsAll(allPai)
                ) {
            return 1;
        }
        return 0;
    }

    /**
     * 是否是金钩钓
     *
     * @param allshoupai 手牌
     * @return 几个金钩钓
     */
    private Integer jinGouDiao(Allshoupai allshoupai) {
        if (allshoupai.getShoupai().size() == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 是否是大对子
     *
     * @param allshoupai 手牌
     * @return 有几个大对子
     */
    private Integer daDuiZi(Allshoupai allshoupai) {
        if (FanTool.isDaduizi(allshoupai)) {
            return 1;
        }
        return 0;
    }

    /**
     * 有几个杠
     *
     * @param allshoupai 手牌
     * @return
     */
    private Integer gang(Allshoupai allshoupai) {
        List<Gang> gangs = FanTool.findGangs(allshoupai);
        return gangs.size();
    }

    /**
     * 有几个一条龙
     *
     * @param allshoupai 手牌
     * @return 几个一条龙
     */
    private Integer yiTiaoLong(Allshoupai allshoupai) {
        List<Integer> shoupai = allshoupai.getShoupai();
        if (FanTool.yiTiaoLongSpecifiedType(shoupai, PaiType.TONG) ||
                FanTool.yiTiaoLongSpecifiedType(shoupai, PaiType.TIAO) ||
                FanTool.yiTiaoLongSpecifiedType(shoupai, PaiType.WAN)
                ) {
            return 1;
        }
        return 0;
    }

    /**
     * 夹心五
     *
     * @param allshoupai 手牌
     * @return 几个夹心五
     */
    private Integer jiaXinWu(Allshoupai allshoupai) {
        if (allshoupai.getHued() == 5 ||
                allshoupai.getHued() == 15 ||
                allshoupai.getHued() == 25
                ) {
            List<Integer> shoupai = allshoupai.getShoupai();
            if (FanTool.jiaXinWu(shoupai, PaiType.TONG) ||
                    FanTool.jiaXinWu(shoupai, PaiType.TIAO) ||
                    FanTool.jiaXinWu(shoupai, PaiType.WAN)
                    ) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 卡二条
     *
     * @param allshoupai
     * @return 几个卡二条
     */
    private Integer kaErTiao(Allshoupai allshoupai) {
        if (allshoupai.getHued() != 12) {
            return 0;
        }
        if (FanTool.kaErTiao(allshoupai.getShoupai())) {
            return 1;
        }
        return 0;
    }

    /**
     * 是否有单个板板高
     *
     * @param org 手牌中的坊子
     * @return
     */
    private boolean singleBanbanGao(List<Triple> org) {
        List<Triple> distincted = org.stream().distinct().collect(Collectors.toList());
        if (org.size() != distincted.size()) {
            return true;
        } else {
            return false;
        }
    }

}
