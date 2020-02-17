package fan.entities;

import java.util.*;
import java.util.stream.Collectors;

public class Allshoupai {
    private List<Integer> shoupai = new ArrayList<>();
    private List<Peng> pengs = new ArrayList<>();
    private List<Gang> gangs = new ArrayList<>();
    private Integer hued = -1;

    public Allshoupai(List<Integer> shoupai, List<Peng> pengs, List<Gang> gangs, Integer hued) {
        this.shoupai = shoupai;
        this.pengs = pengs;
        this.gangs = gangs;
        this.hued = hued;
    }

    public Allshoupai() {
    }

    public List<Integer> getShoupai() {
        return shoupai;
    }

    public void setShoupai(List<Integer> shoupai) {
        this.shoupai = shoupai;
    }

    public List<Peng> getPengs() {
        return pengs;
    }

    public void setPengs(List<Peng> pengs) {
        this.pengs = pengs;
    }

    public List<Gang> getGangs() {
        return gangs;
    }

    public void setGangs(List<Gang> gangs) {
        this.gangs = gangs;
    }

    public Integer getHued() {
        return hued;
    }

    public void setHued(Integer hued) {
        this.hued = hued;
    }

    public void checkHuedInShoupai() {
        if (!shoupai.contains(hued)) {
            throw new IllegalStateException("手牌中未包含已经胡的牌");
        }
    }

    /**
     * 合并所有的手牌
     */
    public List<Integer> findAllPais() {
        List<Integer> all = new ArrayList<>();
        all.addAll(shoupai);
        for (Peng peng : pengs) {
            all.addAll(peng.getSingPengs());
        }
        for (Gang gang : gangs) {
            all.addAll(gang.getSingGangs());
        }

        return all;
    }

    /**
     * 找到将，并移除，返回无将手牌
     * @return
     */
    public List<Integer> findShoupaiExpectJiang(){
       List<Integer> shouPaiCopy =  shoupai.stream().collect(Collectors.toList());
        Map<Integer, IntSummaryStatistics> groups  = shouPaiCopy.stream()
                .collect(Collectors.groupingBy(singlePai->singlePai, Collectors.summarizingInt(singlePai-> (int) singlePai)));
        groups.forEach((k,v)->{if(v.getCount()==2){
            shouPaiCopy.remove(k);
        }});
        return shouPaiCopy;
    }

    public static void main(String[] args) {
        Allshoupai allshoupai = new Allshoupai();
        List<Integer> shoupai = Arrays.asList(
                4, 5, 5, 5, 6, 7, 8, 23, 24, 25, 26, 27, 28
        );
        allshoupai.setShoupai(shoupai);

        allshoupai.findShoupaiExpectJiang();
    }

}
