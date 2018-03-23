package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class PersondataBean {

    /**
     * code : 1
     * msg : {"staff":{"organ_id":85,"organ_name":"周威云","organ_type":"1","organ_tel":"18898730808","organ_email":"1210707261@qq.com","organ_newtime":591379200,"organ_famale":"1","dment_name":"企划推广事业部","position_name":"PHP工程师","account_isonline":1,"phone_isonline":0,"pad_isonline":0}}
     */

    public int code;
    public String errcode;
    public String errmsg;
    public MsgBean msg;

    public static class MsgBean {
        /**
         * staff : {"organ_id":85,"organ_name":"周威云","organ_type":"1","organ_tel":"18898730808","organ_email":"1210707261@qq.com","organ_newtime":591379200,"organ_famale":"1","dment_name":"企划推广事业部","position_name":"PHP工程师","account_isonline":1,"phone_isonline":0,"pad_isonline":0}
         */

        public StaffBean staff;

        public static class StaffBean {
            /**
             * organ_id : 85
             * organ_name : 周威云
             * organ_type : 1
             * organ_tel : 18898730808
             * organ_email : 1210707261@qq.com
             * organ_newtime : 591379200
             * organ_famale : 1
             * dment_name : 企划推广事业部
             * position_name : PHP工程师
             * account_isonline : 1
             * phone_isonline : 0
             * pad_isonline : 0
             */

            public int organ_id;
            public String organ_name;
            public String organ_type;
            public String organ_tel;
            public String organ_email;
            public int organ_newtime;
            public String organ_famale;
            public String dment_name;
            public String position_name;
            public int account_isonline;
            public int phone_isonline;
            public int pad_isonline;
        }
    }
}
