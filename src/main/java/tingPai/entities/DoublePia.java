package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 将（即麻将中胡牌必须要有一对中的一对）
 * @author Henry Zhou
 */
public class DoublePia {
  private   List<Integer> doubles = new ArrayList<>();

    public DoublePia() {
    }

    public DoublePia(List<Integer> doubles) {
        this.doubles = doubles;
    }

    public List<Integer> getDoubles() {
        return doubles;
    }

    public void setDoubles(List<Integer> doubles) {
        this.doubles = doubles;
    }
}
