package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class SecondContactsBean {

    /**
     * code : 1
     * msg : {"department_info":{"dment_id":17,"dment_name":"企划推广事业部","dment_pid":0,"dment_company":20,"dment_describe":"负责集团品牌推广、建设；集团各类推广、企划活动等。","dment_lofficial":"胡婷"},"department":[{"dment_id":23,"dment_name":"品牌部","dment_pid":17,"dment_company":20,"dment_describe":"","dment_lofficial":"易思翔","count":6,"count_on_line":0},{"dment_id":22,"dment_name":"活动部","dment_pid":17,"dment_company":20,"dment_describe":"负责集团、各分子公司的各种线下线上活动策划、运营","dment_lofficial":"朱琰","count":4,"count_on_line":0},{"dment_id":21,"dment_name":"网络部","dment_pid":17,"dment_company":20,"dment_describe":"集团整体网络环境、硬件维护、软件开发、外包项目验收等","dment_lofficial":"朱杨","count":7,"count_on_line":2},{"dment_id":24,"dment_name":"媒介部","dment_pid":17,"dment_company":20,"dment_describe":"负责媒体部分的工资体系","dment_lofficial":"李楠竹","count":2,"count_on_line":0},{"dment_id":25,"dment_name":"设计部","dment_pid":17,"dment_company":20,"dment_describe":"负责产品、活动的设计工作","dment_lofficial":"黄娜","count":6,"count_on_line":0}]}
     */

    public int code;
    public String errcode;
    public String errmsg;
    public MsgBean msg;

    public static class MsgBean {
        /**
         * department_info : {"dment_id":17,"dment_name":"企划推广事业部","dment_pid":0,"dment_company":20,"dment_describe":"负责集团品牌推广、建设；集团各类推广、企划活动等。","dment_lofficial":"胡婷"}
         * department : [{"dment_id":23,"dment_name":"品牌部","dment_pid":17,"dment_company":20,"dment_describe":"","dment_lofficial":"易思翔","count":6,"count_on_line":0},{"dment_id":22,"dment_name":"活动部","dment_pid":17,"dment_company":20,"dment_describe":"负责集团、各分子公司的各种线下线上活动策划、运营","dment_lofficial":"朱琰","count":4,"count_on_line":0},{"dment_id":21,"dment_name":"网络部","dment_pid":17,"dment_company":20,"dment_describe":"集团整体网络环境、硬件维护、软件开发、外包项目验收等","dment_lofficial":"朱杨","count":7,"count_on_line":2},{"dment_id":24,"dment_name":"媒介部","dment_pid":17,"dment_company":20,"dment_describe":"负责媒体部分的工资体系","dment_lofficial":"李楠竹","count":2,"count_on_line":0},{"dment_id":25,"dment_name":"设计部","dment_pid":17,"dment_company":20,"dment_describe":"负责产品、活动的设计工作","dment_lofficial":"黄娜","count":6,"count_on_line":0}]
         */

        public DepartmentInfoBean department_info;
        public List<DepartmentBean> department;
        public List<SecondContactsBean.MsgBean.StaffBean> staff;
        public static class DepartmentInfoBean {
            /**
             * dment_id : 17
             * dment_name : 企划推广事业部
             * dment_pid : 0
             * dment_company : 20
             * dment_describe : 负责集团品牌推广、建设；集团各类推广、企划活动等。
             * dment_lofficial : 胡婷
             * 　"all_on_line":3,
             　　"all_count":27
             */

            public int dment_id;
            public String dment_name;
            public int dment_pid;
            public int dment_company;
            public String dment_describe;
            public String dment_lofficial;
            public int all_on_line;
            public int all_count;
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
        public static class DepartmentBean {
            /**
             * dment_id : 23
             * dment_name : 品牌部
             * dment_pid : 17
             * dment_company : 20
             * dment_describe :
             * dment_lofficial : 易思翔
             * count : 6
             * count_on_line : 0
             */

            public int dment_id;
            public String dment_name;
            public int dment_pid;
            public int dment_company;
            public String dment_describe;
            public String dment_lofficial;
            public int count;
            public int count_on_line;
        }
    }
}
