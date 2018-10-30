package cc.mrbird.gaoxz.domain;

import java.io.Serializable;
import java.util.Map;

public class FlightEx implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;

    private Flight target;
    private Map<String,String> detail;

    public Flight getTarget() {
        return target;
    }

    public void setTarget(Flight target) {
        this.target = target;
    }

    public Map<String, String> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, String> detail) {
        this.detail = detail;
    }
}

