package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class IMmessageBean {

    /**
     * type : talk
     * val : 2312312312312
     * ntel : 13739221601
     * to_ntel : 18898730808,18773315923
     */

    private String type;
    private String val;
    private String ntel;
    private String to_ntel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getNtel() {
        return ntel;
    }

    public void setNtel(String ntel) {
        this.ntel = ntel;
    }

    public String getTo_ntel() {
        return to_ntel;
    }

    public void setTo_ntel(String to_ntel) {
        this.to_ntel = to_ntel;
    }
}
