package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19 0019.
 * 请假接口4返回值
 */

public class InterfacefourBean {

    /**
     * code : 1
     * msg : 申请请假成功
     * apply_no : 201803190020
     * info : [{"flow_no":"0020000000000000A9971","flow_note_id":0,"complayid":0,"departmentid":0,"tdepartmentid":0,"position_id":0}]
     */

    public String code;
    public String errcode;
    public String msg;
    public String apply_no;
    public List<InfoBean> info;

    public static class InfoBean {
        /**
         * flow_no : 0020000000000000A9971
         * flow_note_id : 0
         * complayid : 0
         * departmentid : 0
         * tdepartmentid : 0
         * position_id : 0
         */

        public String flow_no;
        public int flow_note_id;
        public int complayid;
        public int departmentid;
        public int tdepartmentid;
        public int position_id;
    }
}
