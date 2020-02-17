import org.junit.Test;
import tingPai.SichuanTingpai;
import tingPai.Tingpai;
import tingPai.entities.AllTingpai;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SichuanTingpaiTest {
    @Test
    public void tingpaiTest01() {
        List<Integer> shoupai = Arrays.asList(
                3, 3, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 16
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(2, 4, 5, 7, 8));
    }

    @Test
    public void tingpaiTest02() {
        List<Integer> shoupai = Arrays.asList(
                4, 5, 5, 5, 6, 7, 8, 23, 24, 25, 26, 27, 28
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(3, 4, 6, 9));
    }

    @Test
    public void tingpaiTest03() {
        List<Integer> shoupai = Arrays.asList(
                2, 3, 4, 5, 6, 7, 8, 9, 9, 9
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(1, 2, 4, 5, 7, 8));
    }

    @Test
    public void tingpaiTest04() {
        List<Integer> shoupai = Arrays.asList(
                4, 5, 5, 5, 5, 6, 7
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(3, 4, 6, 7));
    }

    @Test
    public void tingpaiTest05() {
        List<Integer> shoupai = Arrays.asList(
                3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void tingpaiTest06() {
        List<Integer> shoupai = Arrays.asList(
                1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9
        );
        Tingpai tingpai = new SichuanTingpai();
        AllTingpai allTingpai = tingpai.tingPais(shoupai);
        assertEquals(allTingpai.getTingPais(), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

}
