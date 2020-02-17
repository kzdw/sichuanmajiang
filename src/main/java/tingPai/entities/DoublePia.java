package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

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
