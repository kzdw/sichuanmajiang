package fan.entities;

import fan.enumType.FanType;

/**
 * 单个番对象
 *
 * @author Henry Zhou
 */
public class SingleFan {
    private FanType fanType; //番类型
    private Integer fanCount;//几番

    public SingleFan(FanType fanType, Integer fanCount) {
        this.fanType = fanType;
        this.fanCount = fanCount;
    }

    public SingleFan() {
    }

    public FanType getFanType() {
        return fanType;
    }

    public void setFanType(FanType fanType) {
        this.fanType = fanType;
    }

    public Integer getFanCount() {
        return fanCount;
    }

    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }
}
