import fan.ConfigFan;
import fan.Fan;
import fan.SichuanFan;
import fan.entities.Allshoupai;
import fan.entities.Gang;
import fan.entities.Peng;
import fan.enumType.FanType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * 四川麻将算番测试
 *
 * @author Henry Zhou
 */
public class SichuanFanTest {
    List<ConfigFan> configFans = Arrays.asList(
            new ConfigFan(FanType.GANG, true, 1),
            new ConfigFan(FanType.DA_DUI_ZI, true, 1),
            new ConfigFan(FanType.QING_YI_SE, true, 2),
            new ConfigFan(FanType.JIA_XIN_WU, true, 1),
            new ConfigFan(FanType.KA_ER_TIAO, true, 1),
            new ConfigFan(FanType.JIN_GOU_DIAO, true, 1),
            new ConfigFan(FanType.MEN_QING, true, 1),
            new ConfigFan(FanType.YI_JIU_JIANG_DUI, true, 1),
            new ConfigFan(FanType.BAN_BAN_GAO, true, 1),
            new ConfigFan(FanType.YI_TIAO_LONG, true, 2),
            new ConfigFan(FanType.XIAO_QI_DUI, true, 2)

    );

    @Test
    public void gangTest() {
        List<Integer> shoupai = Arrays.asList(2, 2, 2, 3, 3, 3, 5, 6, 7, 7, 7);//胡牌包含 杠 x 2
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(5, 5, 5)));
        List<Gang> gangs = Arrays.asList(new Gang(Arrays.asList(11, 11, 11, 11)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, gangs, 2);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(2, (int) integer);
    }

    @Test
    public void daDuiziTest() {
        List<Integer> shoupai = Arrays.asList(1, 1, 1, 2, 2, 2, 8, 8, 8, 21, 21);//胡牌包含大对子
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(5, 5, 5)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 21);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void qingYiSeTest() {
        List<Integer> shoupai = Arrays.asList(11, 12, 13, 14, 14, 14, 16, 16);//胡牌包含清一色
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(15, 15, 15)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 16);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(2, (int) integer);
    }

    @Test
    public void jaiXinWuTest() {
        List<Integer> shoupai = Arrays.asList(14, 15, 16, 22, 22, 22, 16, 16);//胡牌包含 夹心五
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(21, 21, 21)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 15);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void kaErTiaoTest() {
        List<Integer> shoupai = Arrays.asList(11, 12, 13, 14, 14, 14, 6, 6);//胡牌包含卡二条
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(15, 15, 15)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 12);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void jinGouDiaoTest() {
        List<Integer> shoupai = Arrays.asList(6, 6);//胡牌包含大对子 金钩钓
        List<Peng> pengs = Arrays.asList(
                new Peng(Arrays.asList(15, 15, 15)),
                new Peng(Arrays.asList(16, 16, 16)),
                new Peng(Arrays.asList(18, 18, 18)),
                new Peng(Arrays.asList(19, 19, 19)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 6);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(2, (int) integer);
    }

    @Test
    public void mengQingTest() {
        List<Integer> shoupai = Arrays.asList(11, 12, 13, 14, 14, 14,15,15,15,18,18,18,1,1);//胡牌包含门清
        Allshoupai allshoupai = new Allshoupai(shoupai, Arrays.asList(), Arrays.asList(), 1);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void yiJiuJiangDuiTest() {
        List<Integer> shoupai = Arrays.asList(3,4,5,14, 14,14,18,18,18,12,12);//胡牌包含一九将对
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(15, 15, 15)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 12);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void banbanGaoTest() {
        List<Integer> shoupai = Arrays.asList(1,2,3,1,2,3,18,18,18,12,12);//胡牌包含板板高
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(15, 15, 15)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 12);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(1, (int) integer);
    }

    @Test
    public void yiTiaoLongTest() {
        List<Integer> shoupai = Arrays.asList(1,2,3,4,5,6,7,8,9,12,12);//胡牌包含一条龙
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(15, 15, 15)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 12);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(2, (int) integer);
    }

    @Test
    public void xiaoQiDuiTest() {
        List<Integer> shoupai = Arrays.asList(1,1,2,2,4,4,8,8,9,9,12,12,13,13);//胡牌包含小七对 门清
        Allshoupai allshoupai = new Allshoupai(shoupai, Arrays.asList(), Arrays.asList(), 12);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(3, (int) integer);
    }


    @Test
    public void jiaXinWuAndYiTiaoLongAndGangTest() {
        List<Integer> shoupai = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 21, 21);//胡牌包含番 一条龙 夹心五 杠
        List<Peng> pengs = Arrays.asList(new Peng(Arrays.asList(5, 5, 5)));
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs, Arrays.asList(), 5);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai, configFans).getTotalFans();
        assertEquals(4, (int) integer);
    }



}
