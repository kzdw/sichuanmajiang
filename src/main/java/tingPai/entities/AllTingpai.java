package tingPai.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 听牌类
 * @author Henry Zhou
 */
public class AllTingpai {
    private List<Integer> tingPais = new ArrayList<>();//已经听牌集合
    private List<TingDetail> tingDetails = new ArrayList<>();//听牌细节
    private final String LINE_BREAK = "\r\n";

    public AllTingpai(List<Integer> tingPais, List<TingDetail> tingDetails) {
        this.tingPais = tingPais;
        this.tingDetails = tingDetails;
    }

    public AllTingpai() {
    }

    public List<Integer> getTingPais() {
        return tingPais;
    }

    public void setTingPais(List<Integer> tingPais) {
        this.tingPais = tingPais;
    }

    public List<TingDetail> getTingDetails() {
        return tingDetails;
    }

    @Override
    public String toString() {
        String str = "";
        str += "听牌: " + tingPais.toString() + LINE_BREAK;
        str += "听牌细节"+LINE_BREAK;
        for (TingDetail tingDetail : tingDetails) {
            str += "-----------------------------------"+LINE_BREAK;
            str += "胡：" + tingDetail.getHupai() + LINE_BREAK;
            str += "将：" + tingDetail.getDoublePia().getDoubles().toString() +LINE_BREAK;
            for (Triple triple : tingDetail.getTriples()) {
                str += "组合：" + triple.getIntegers().toString() + LINE_BREAK;
            }
            if(tingDetail.getQiDui().size()!=0){
                str+="七对:"+ tingDetail.getQiDui().toString()+LINE_BREAK;
            }
            str += "-----------------------------------";
        }


        return str;
    }

    public void setTingDetails(List<TingDetail> tingDetails) {
        this.tingDetails = tingDetails;
    }
}
