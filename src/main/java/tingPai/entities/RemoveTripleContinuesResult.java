package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 手牌移除连续坊子结果类
 * @author Henry Zhou
 *
 */
public class RemoveTripleContinuesResult {
    private boolean alltripleContinues;
    private List<Triple> triples = new ArrayList<>();

    public RemoveTripleContinuesResult(boolean alltripleContinues, List<Triple> triples) {
        this.alltripleContinues = alltripleContinues;
        this.triples = triples;
    }

    public RemoveTripleContinuesResult() {
    }

    public boolean isAlltripleContinues() {
        return alltripleContinues;
    }

    public void setAlltripleContinues(boolean alltripleContinues) {
        this.alltripleContinues = alltripleContinues;
    }

    public List<Triple> getTriples() {
        return triples;
    }

    public void setTriples(List<Triple> triples) {
        this.triples = triples;
    }
}
