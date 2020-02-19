package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 移除坊子结果类
 * @author Henry Zhou
 */
public class RemoveTripleResult {
    private List<Integer> result = new ArrayList<>();//移除后的结果
    private List<Triple> triples = new ArrayList<>();//被移除的triple集合

    public RemoveTripleResult(List<Integer> result, List<Triple> triples) {
        this.result = result;
        this.triples = triples;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }

    public List<Triple> getTriples() {
        return triples;
    }

    public void setTriples(List<Triple> triples) {
        this.triples = triples;
    }
}
