package fan;

import fan.enumType.FanType;

/**
 * 算番配置
 *
 * @author Henry Zhou
 */
public class ConfigFan {
    private FanType fanType;//番类型
    private boolean valid;//当前类型是否有效，比如一条龙，板板高，大多数地区不算番，即为false
    private int singleFan;//单个算成多少番，例如一般情况下 大对子 算一番，但是有些地区算两番，这个需要配置

    public ConfigFan() {
    }

    public ConfigFan(FanType fanType, boolean valid, int singleFan) {
        this.fanType = fanType;
        this.valid = valid;
        this.singleFan = singleFan;
    }

    public FanType getFanType() {
        return fanType;
    }

    public void setFanType(FanType fanType) {
        this.fanType = fanType;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getSingleFan() {
        return singleFan;
    }

    public void setSingleFan(int singleFan) {
        this.singleFan = singleFan;
    }
}
