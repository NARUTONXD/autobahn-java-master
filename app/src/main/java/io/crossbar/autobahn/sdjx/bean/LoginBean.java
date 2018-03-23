package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class LoginBean {
    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", org=" + org +
                '}';
    }

    /**
     * code : 1
     * msg : phone登陆成功
     * org : null
     */

    private String code;
    private String msg;
    private Object org;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOrg() {
        return org;
    }

    public void setOrg(Object org) {
        this.org = org;
    }
}
