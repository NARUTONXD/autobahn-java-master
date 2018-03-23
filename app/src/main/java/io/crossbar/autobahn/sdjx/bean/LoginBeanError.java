package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class LoginBeanError {

    /**
     * errcode : 10014
     * errmsg : 手机已经登陆，用户再次用另外一台手机登陆
     */

    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
