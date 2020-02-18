package fan.entities;

import java.util.List;

/**
 * 番对象
 *
 * @author Henry Zhou
 */
public class FanEntity {
    private List<SingleFan> fans;//番集合
    private Integer totalFans;//所有番

    public FanEntity(List<SingleFan> fans, Integer totalFans) {
        this.fans = fans;
        this.totalFans = totalFans;
    }

    public FanEntity() {
    }

    public List<SingleFan> getFans() {
        return fans;
    }

    public void setFans(List<SingleFan> fans) {
        this.fans = fans;
    }

    public Integer getTotalFans() {
        return totalFans;
    }

    public void setTotalFans(Integer totalFans) {
        this.totalFans = totalFans;
    }
}
