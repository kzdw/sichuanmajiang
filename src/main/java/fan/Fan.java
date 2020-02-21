package fan;

import fan.entities.Allshoupai;
import fan.entities.FanEntity;

import java.util.List;

/**
 * 番接口
 *
 * @author Henry Zhou
 */
public interface Fan {
    //门清中张 幺九将对 大对子 杠 一条龙 夹心五 卡二条 清一色 板板高 金钩钓 边张

    /**
     * 根据配置计算番
     *
     * @param allshoupai 所有手牌
     * @param configFans 番配置
     * @return
     */
    FanEntity findFan(Allshoupai allshoupai, List<ConfigFan> configFans);
}
