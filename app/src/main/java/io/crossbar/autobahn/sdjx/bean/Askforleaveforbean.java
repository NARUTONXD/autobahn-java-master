package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7 0007.
 * 接口4bean
 */

public class Askforleaveforbean {

    /**
     * code : 1
     * msg : 申请请假成功
     * apply_no : 201803070007
     * info : [{"flow_no":"0020000000000000A9977","flow_note_id":0,"complayid":0,"departmentid":0,"tdepartmentid":0,"position_id":0}]
     */

    public String code;
    public String msg;
    public String apply_no;
    public List<InfoBean> info;
    /**
     * errcode : 10045
     * errmsg : [{"apply_leave_no":"201803090002","approve_complayid":"20","approve_departmentid":"17","approve_tdepartmentid":"21","approve_rankid":0,"approve_postitionid":85,"approve_pname":"周威云","approve_tel":"18898730808","flow_no":"0020000000000000A9977","flow_note_id":25,"approve_res":-1,"approve_addtime":1520576682},{"apply_leave_no":"201803090002","approve_complayid":"0","approve_departmentid":"0","approve_tdepartmentid":"0","approve_rankid":"6","approve_postitionid":0,"approve_pname":"主管","approve_tel":0,"flow_no":"0020000000000000A9977","flow_note_id":26,"approve_res":-1,"approve_addtime":1520576682},{"apply_leave_no":"201803090002","approve_complayid":"20","approve_departmentid":"17","approve_tdepartmentid":"21","approve_rankid":0,"approve_postitionid":"10","approve_pname":"唐婷","approve_tel":"18570495286","flow_no":"0020000000000000A9977","flow_note_id":27,"approve_res":-1,"approve_addtime":1520576682},{"apply_leave_no":"201803090002","approve_complayid":"20","approve_departmentid":"17","approve_tdepartmentid":"21","approve_rankid":0,"approve_postitionid":"13","approve_pname":"周彦东","approve_tel":"18890353552","flow_no":"0020000000000000A9977","flow_note_id":28,"approve_res":-1,"approve_addtime":1520576682}]
     */

    public String errcode;
    public List<ErrmsgBean> errmsg;

    public static class ErrmsgBean {
        /**
         * apply_leave_no : 201803090002
         * approve_complayid : 20
         * approve_departmentid : 17
         * approve_tdepartmentid : 21
         * approve_rankid : 0
         * approve_postitionid : 85
         * approve_pname : 周威云
         * approve_tel : 18898730808
         * flow_no : 0020000000000000A9977
         * flow_note_id : 25
         * approve_res : -1
         * approve_addtime : 1520576682
         */

        public String apply_leave_no;
        public String approve_complayid;
        public String approve_departmentid;
        public String approve_tdepartmentid;
        public int approve_rankid;
        public int approve_postitionid;
        public String approve_pname;
        public String approve_tel;
        public String flow_no;
        public int flow_note_id;
        public int approve_res;
        public int approve_addtime;
    }
    public static class InfoBean {
        /**
         * flow_no : 0020000000000000A9977
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
