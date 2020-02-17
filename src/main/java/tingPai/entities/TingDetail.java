package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

public class TingDetail {
    private List<Triple> triples = new ArrayList<>();
    private DoublePia doublePia = new DoublePia();
    private Integer hupai = -1;
    private boolean hupaiYesNot = false;
    private List<Integer> qiDui = new ArrayList<>();

    public TingDetail() {
    }

    public TingDetail(List<Triple> triples, DoublePia doublePia, Integer hupai, boolean hupaiYesNot) {
        this.triples = triples;
        this.doublePia = doublePia;
        this.hupai = hupai;
        this.hupaiYesNot = hupaiYesNot;
    }

    public TingDetail(Integer hupai, boolean hupaiYesNot, List<Integer> qiDui) {
        this.hupai = hupai;
        this.hupaiYesNot = hupaiYesNot;
        this.qiDui = qiDui;
    }

    public List<Triple> getTriples() {
        return triples;
    }

    public void setTriples(List<Triple> triples) {
        this.triples = triples;
    }

    public DoublePia getDoublePia() {
        return doublePia;
    }

    public void setDoublePia(DoublePia doublePia) {
        this.doublePia = doublePia;
    }

    public Integer getHupai() {
        return hupai;
    }

    public void setHupai(Integer hupai) {
        this.hupai = hupai;
    }

    public boolean isHupaiYesNot() {
        return hupaiYesNot;
    }

    public void setHupaiYesNot(boolean hupaiYesNot) {
        this.hupaiYesNot = hupaiYesNot;
    }

    public List<Integer> getQiDui() {
        return qiDui;
    }

    public void setQiDui(List<Integer> qiDui) {
        this.qiDui = qiDui;
    }

//    public List<Triple> findTriplWithoutthreeSame() {
//        List<Triple> tripleResult = triples.stream().filter(triple -> !triple.threeIntTheSame())
//                .collect(Collectors.toList());
//        return tripleResult;
//    }
}
