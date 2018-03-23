package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class AskforleaveBean {

    /**
     * code : 1
     * msg : 根据二级类型和天数初始化流程点成功
     * org : [{"flow_no":"0020000000000000A9960","flow_name":"集团请假流程小于等于3天","info":[{"flow_note_id":"52","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"85","approve_pname":"周威云","approve_tel":"18898730808","html_css":""},{"flow_note_id":"53","flow_no":"0020000000000000A9960","rankid":"6","postitionid":"0","approve_pname":"主管","approve_tel":"0","html_css":"red"},{"flow_note_id":"54","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"10","approve_pname":"唐婷","approve_tel":"18570495286","html_css":""},{"flow_note_id":"55","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"13","approve_pname":"周彦东","approve_tel":"18890353552","html_css":""},{"flow_note_id":"56","flow_no":"0020000000000000A9960","rankid":"6","postitionid":"0","approve_pname":"主管","approve_tel":"0","html_css":"red"}]},{"flow_no":"0020000000000000A9965","flow_name":"集团请假流程小于等于3天第二条","info":[{"flow_note_id":"62","flow_no":"0020000000000000A9965","rankid":"6","postitionid":"0","approve_pname":"主管","approve_tel":"0","html_css":"red"},{"flow_note_id":"63","flow_no":"0020000000000000A9965","rankid":"0","postitionid":"10","approve_pname":"唐婷","approve_tel":"18570495286","html_css":""}]}]
     */

    public String code;
    public String msg;
    public String errcode;
    public String errmsg;
    public List<OrgBean> org;

    public static class OrgBean {
        /**
         * flow_no : 0020000000000000A9960
         * flow_name : 集团请假流程小于等于3天
         * info : [{"flow_note_id":"52","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"85","approve_pname":"周威云","approve_tel":"18898730808","html_css":""},{"flow_note_id":"53","flow_no":"0020000000000000A9960","rankid":"6","postitionid":"0","approve_pname":"主管","approve_tel":"0","html_css":"red"},{"flow_note_id":"54","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"10","approve_pname":"唐婷","approve_tel":"18570495286","html_css":""},{"flow_note_id":"55","flow_no":"0020000000000000A9960","rankid":"0","postitionid":"13","approve_pname":"周彦东","approve_tel":"18890353552","html_css":""},{"flow_note_id":"56","flow_no":"0020000000000000A9960","rankid":"6","postitionid":"0","approve_pname":"主管","approve_tel":"0","html_css":"red"}]
         */

        public String flow_no;
        public String flow_name;
        public List<InfoBean> info;

        public static class InfoBean {
            /**
             * flow_note_id : 52
             * flow_no : 0020000000000000A9960
             * rankid : 0
             * postitionid : 85
             * approve_pname : 周威云
             * approve_tel : 18898730808
             * html_css :
             */

            public String flow_note_id;
            public String flow_no;
            public String rankid;
            public String postitionid;
            public String approve_pname;
            public String approve_tel;
            public String html_css;
        }
    }
}
