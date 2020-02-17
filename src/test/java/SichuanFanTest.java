import fan.Fan;
import fan.SichuanFan;
import fan.entities.Allshoupai;
import fan.entities.Gang;
import fan.entities.Peng;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class SichuanFanTest {
    @Test
    public void yiTiaoLongTest() {
        List<Integer> shoupai = Arrays.asList(
               2,2,2,3,3,3,5,6,7,7,7
        );
        Peng peng = new Peng(Arrays.asList(5,5,5));
        List<Peng> pengs = Arrays.asList(peng);
        Gang gang = new Gang(Arrays.asList(11,11,11,11));
        List<Gang> gangs = Arrays.asList(gang);
        Allshoupai allshoupai = new Allshoupai(shoupai, pengs,gangs,2);
        Fan fan = new SichuanFan();
        Integer integer = fan.findFan(allshoupai);
        assertEquals(2,(int)integer);
    }
}
