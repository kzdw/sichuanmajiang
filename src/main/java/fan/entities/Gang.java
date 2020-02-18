package fan.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 杠对象
 *
 * @author Henry Zhou
 */
public class Gang {
    List<Integer> singGangs = new ArrayList<>();

    public Gang(List<Integer> singGangs) {
        this.singGangs = singGangs;
    }

    public Gang() {
    }

    public List<Integer> getSingGangs() {
        return singGangs;
    }

    public void setSingGangs(List<Integer> singGangs) {
        this.singGangs = singGangs;
    }
}
