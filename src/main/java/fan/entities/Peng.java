package fan.entities;

import java.util.ArrayList;
import java.util.List;

public class Peng {
    List<Integer> singPengs = new ArrayList<>();

    public Peng(List<Integer> singPengs) {
        this.singPengs = singPengs;
    }

    public Peng() {
    }

    public List<Integer> getSingPengs() {
        return singPengs;
    }

    public void setSingPengs(List<Integer> singPengs) {
        this.singPengs = singPengs;
    }
}
