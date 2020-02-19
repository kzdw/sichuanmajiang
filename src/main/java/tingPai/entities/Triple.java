package tingPai.entities;

import util.ListTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 坊子（例如3,4,5条，6，6,6条）
 * @author Henry Zhou
 */
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;

        Triple triple = (Triple) obj;
        if (this.sameTriple(triple)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (Integer integer : this.getIntegers()) {
            result += integer;
        }
        return result;
    }
}
