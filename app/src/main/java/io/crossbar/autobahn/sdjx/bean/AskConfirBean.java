package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class AskConfirBean {

    /**
     * code : 1
     * msg : {"staff":[{"organ_id":5,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"91","organ_name":"朱杨","organ_type":"2","organ_tel":"18570606282","position_id":91,"position_levelid":6,"rank_id":6,"rank_level":6,"rank_name":"主管","position_name":"网络部主管","organ_alias":"","organ_email":"237026930@qq.com","account_isonline":1,"phone_isonline":0,"pad_isonline":0},{"organ_id":13,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"64","organ_name":"周彦东","organ_type":"2","organ_tel":"18890353552","position_id":64,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"硬件工程师","organ_alias":"","organ_email":"1103911950@qq.com","account_isonline":0,"phone_isonline":0,"pad_isonline":0},{"organ_id":818,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"64","organ_name":"牛晓冬","organ_type":"1","organ_tel":"18229863760","position_id":64,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"硬件工程师","organ_alias":"n","organ_email":"1539004290@qq.com","account_isonline":1,"phone_isonline":1,"pad_isonline":0},{"organ_id":820,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"64","organ_name":"彭旭权","organ_type":"1","organ_tel":"15116193179","position_id":64,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"硬件工程师","organ_alias":"p","organ_email":"1014330341@qq.com","account_isonline":1,"phone_isonline":1,"pad_isonline":0},{"organ_id":10,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"92","organ_name":"唐婷","organ_type":"2","organ_tel":"18570495286","position_id":92,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"前端工程师","organ_alias":"t","organ_email":"1789995913@qq.com","account_isonline":1,"phone_isonline":0,"pad_isonline":0},{"organ_id":819,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"92","organ_name":"曾思萍","organ_type":"0","organ_tel":"18823383132","position_id":92,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"前端工程师","organ_alias":"z","organ_email":"879685145@qq.com","account_isonline":0,"phone_isonline":0,"pad_isonline":0},{"organ_id":85,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"100","organ_name":"周威云","organ_type":"1","organ_tel":"18898730808","position_id":100,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"PHP工程师","organ_alias":"z","organ_email":"1210707261@qq.com","account_isonline":1,"phone_isonline":0,"pad_isonline":0},{"organ_id":817,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"100","organ_name":"张依凡","organ_type":"2","organ_tel":"18773315923","position_id":100,"position_levelid":7,"rank_id":7,"rank_level":7,"rank_name":"专员","position_name":"PHP工程师","organ_alias":"z","organ_email":"316764395@qq.com","account_isonline":1,"phone_isonline":0,"pad_isonline":0}]}
     */

    public int code;
    public MsgBean msg;

    public static class MsgBean {
        public List<StaffBean> staff;

        public static class StaffBean {
            /**
             * organ_id : 5
             * organ_company : 20
             * organ_department : 17
             * organ_tdepartment : 21
             * organ_position : 91
             * organ_name : 朱杨
             * organ_type : 2
             * organ_tel : 18570606282
             * position_id : 91
             * position_levelid : 6
             * rank_id : 6
             * rank_level : 6
             * rank_name : 主管
             * position_name : 网络部主管
             * organ_alias :
             * organ_email : 237026930@qq.com
             * account_isonline : 1
             * phone_isonline : 0
             * pad_isonline : 0
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
            public int account_isonline;
            public int phone_isonline;
            public int pad_isonline;
        }
    }
}
