package tingPai.entities;

import util.ListTool;

import java.util.ArrayList;
import java.util.List;

public class Triple {
    private List<Integer> integers = new ArrayList<>();

    public Triple() {
    }

    public Triple(List<Integer> integers) {
        this.integers = integers;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public boolean threeIntTheSame() {
        return integers.get(0) == integers.get(1) && integers.get(1) == integers.get(2);

    }

    public boolean sameTriple(Triple toCompare) {
        return ListTool.checkDiffrent(integers, toCompare.getIntegers());
    }
}
