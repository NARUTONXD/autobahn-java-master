package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class ThreadContactBean {

    /**
     * code : 1
     * msg : {"department_info":{"dment_id":21,"dment_name":"网络部","dment_pid":17,"dment_company":20,"dment_describe":"集团整体网络环境、硬件维护、软件开发、外包项目验收等","dment_lofficial":"朱杨"},"staff":[{"organ_id":818,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"64","organ_name":"牛晓冬","organ_type":"1","organ_tel":"18229863760","position_id":64,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"硬件工程师","organ_alias":"n","organ_email":"1539004290@qq.com","organ_famale":"1","account_state":1,"phone_isonline":0,"pad_isonline":1,"account_teldisplay":1,"departmentname":"企划推广事业部","organ_tdepartmentname":"网络部"},{"organ_id":85,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"100","organ_name":"周威云","organ_type":"1","organ_tel":"18898730808","position_id":100,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"PHP工程师","organ_alias":"z","organ_email":"1210707261@qq.com","organ_famale":"1","account_state":1,"phone_isonline":0,"pad_isonline":0,"account_teldisplay":1,"departmentname":"企划推广事业部","organ_tdepartmentname":"网络部"}]}
     */

    public int code;
    public MsgBean msg;

    public static class MsgBean {
        /**
         * department_info : {"dment_id":21,"dment_name":"网络部","dment_pid":17,"dment_company":20,"dment_describe":"集团整体网络环境、硬件维护、软件开发、外包项目验收等","dment_lofficial":"朱杨"}
         * staff : [{"organ_id":818,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"64","organ_name":"牛晓冬","organ_type":"1","organ_tel":"18229863760","position_id":64,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"硬件工程师","organ_alias":"n","organ_email":"1539004290@qq.com","organ_famale":"1","account_state":1,"phone_isonline":0,"pad_isonline":1,"account_teldisplay":1,"departmentname":"企划推广事业部","organ_tdepartmentname":"网络部"},{"organ_id":85,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"100","organ_name":"周威云","organ_type":"1","organ_tel":"18898730808","position_id":100,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"PHP工程师","organ_alias":"z","organ_email":"1210707261@qq.com","organ_famale":"1","account_state":1,"phone_isonline":0,"pad_isonline":0,"account_teldisplay":1,"departmentname":"企划推广事业部","organ_tdepartmentname":"网络部"}]
         */

        public DepartmentInfoBean department_info;
        public List<StaffBean> staff;

        public static class DepartmentInfoBean {
            /**
             * dment_id : 21
             * dment_name : 网络部
             * dment_pid : 17
             * dment_company : 20
             * dment_describe : 集团整体网络环境、硬件维护、软件开发、外包项目验收等
             * dment_lofficial : 朱杨
             */

            public int dment_id;
            public String dment_name;
            public int dment_pid;
            public int dment_company;
            public String dment_describe;
            public String dment_lofficial;
        }

        public static class StaffBean {
            /**
             * organ_id : 818
             * organ_company : 20
             * organ_department : 17
             * organ_tdepartment : 21
             * organ_position : 64
             * organ_name : 牛晓冬
             * organ_type : 1
             * organ_tel : 18229863760
             * position_id : 64
             * position_levelid : 7
             * rank_id : 7
             * rank_level : 7
             * rank_name : 专员
             * position_name : 硬件工程师
             * organ_alias : n
             * organ_email : 1539004290@qq.com
             * organ_famale : 1
             * account_state : 1
             * phone_isonline : 0
             * pad_isonline : 1
             * account_teldisplay : 1
             * departmentname : 企划推广事业部
             * organ_tdepartmentname : 网络部
             */

            public int organ_id;
            public String organ_company;
            public String organ_department;
            public String organ_tdepartment;
            public String organ_position;
            public String organ_name;
            public String organ_type;
            public String organ_tel;
            public int position_id;
            public int position_levelid;
            public int rank_id;
            public int rank_level;
            public String rank_name;
            public String position_name;
            public String organ_alias;
            public String organ_email;
            public String organ_famale;
            public int account_state;
            public int phone_isonline;
            public int pad_isonline;
            public int account_teldisplay;
            public String departmentname;
            public String organ_tdepartmentname;
        }
    }
}
