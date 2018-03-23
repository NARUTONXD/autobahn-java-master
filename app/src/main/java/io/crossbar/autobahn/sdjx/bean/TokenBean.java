package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class TokenBean {
    @Override
    public String toString() {
        return "TokenBean{" +
                "ack='" + ack + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    /**
     * ack : acksendking0
     * access_token : 7ca3dd4d719b76ff608c43925710eeead82a7b4d
     */

    private String ack;
    private String access_token;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
