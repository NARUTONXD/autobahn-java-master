package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class InfobytelBean {

    /**
     * code : 1
     * msg : 查询成功
     * org : [{"organ_id":5,"organ_company":"20","organ_department":"17","organ_tdepartment":"21","organ_position":"91","organ_name":"朱杨","organ_alias":"","organ_famale":"1","organ_intime":1416758400,"organ_turntime":1421942400,"organ_newtime":667670400,"organ_contractperiod":"1416758400-1511366400","organ_type":"2","organ_positionlevel":"91","organ_years":3,"organ_place":"湖南衡阳","organ_visage":"3","organ_nation":"汉","organ_marriage":"1","organ_education":"3","organ_secrecy":0,"organ_nstitutions":"湖南信息职业技术学院","organ_majore":"软件工程","organ_qualification":"无","organ_age":27,"organ_tel":"18570606282","organ_address":"湖南省长沙市岳麓区麓枫和苑","organ_idcard":"430407199102284532","organ_qq":"237026930","organ_email":"237026930@qq.com","organ_emergencycontact":"江芳","organ_emode":"15700794015","organ_fname":"杨伟生","organ_fnewtime":-221385600,"organ_fage":55,"organ_moname":"朱荣华","organ_mnewtime":-199958400,"organ_mage":54,"organ_salary":"5500.00","organ_remarks":"","organ_isboth":0,"rankid":6,"account_isonline":1,"phone_isonline":0,"pad_isonline":0,"position_name":"网络部主管"}]
     */

    public String code;
    public String msg;
    public String errcode;
    public String errmsg;
    public List<OrgBean> org;

    public static class OrgBean {
        /**
         * organ_id : 5
         * organ_company : 20
         * organ_department : 17
         * organ_tdepartment : 21
         * organ_position : 91
         * organ_name : 朱杨
         * organ_alias :
         * organ_famale : 1
         * organ_intime : 1416758400
         * organ_turntime : 1421942400
         * organ_newtime : 667670400
         * organ_contractperiod : 1416758400-1511366400
         * organ_type : 2
         * organ_positionlevel : 91
         * organ_years : 3
         * organ_place : 湖南衡阳
         * organ_visage : 3
         * organ_nation : 汉
         * organ_marriage : 1
         * organ_education : 3
         * organ_secrecy : 0
         * organ_nstitutions : 湖南信息职业技术学院
         * organ_majore : 软件工程
         * organ_qualification : 无
         * organ_age : 27
         * organ_tel : 18570606282
         * organ_address : 湖南省长沙市岳麓区麓枫和苑
         * organ_idcard : 430407199102284532
         * organ_qq : 237026930
         * organ_email : 237026930@qq.com
         * organ_emergencycontact : 江芳
         * organ_emode : 15700794015
         * organ_fname : 杨伟生
         * organ_fnewtime : -221385600
         * organ_fage : 55
         * organ_moname : 朱荣华
         * organ_mnewtime : -199958400
         * organ_mage : 54
         * organ_salary : 5500.00
         * organ_remarks :
         * organ_isboth : 0
         * rankid : 6
         * account_isonline : 1
         * phone_isonline : 0
         * pad_isonline : 0
         * position_name : 网络部主管
         */

        public int organ_id;
        public String organ_company;
        public String organ_department;
        public String organ_tdepartment;
        public String organ_position;
        public String organ_name;
        public String organ_alias;
        public String organ_famale;
        public int organ_intime;
        public int organ_turntime;
        public int organ_newtime;
        public String organ_contractperiod;
        public String organ_type;
        public String organ_positionlevel;
        public int organ_years;
        public String organ_place;
        public String organ_visage;
        public String organ_nation;
        public String organ_marriage;
        public String organ_education;
        public int organ_secrecy;
        public String organ_nstitutions;
        public String organ_majore;
        public String organ_qualification;
        public int organ_age;
        public String organ_tel;
        public String organ_address;
        public String organ_idcard;
        public String organ_qq;
        public String organ_email;
        public String organ_emergencycontact;
        public String organ_emode;
        public String organ_fname;
        public int organ_fnewtime;
        public int organ_fage;
        public String organ_moname;
        public int organ_mnewtime;
        public int organ_mage;
        public String organ_salary;
        public String organ_remarks;
        public int organ_isboth;
        public int rankid;
        public int account_isonline;
        public int phone_isonline;
        public int pad_isonline;
        public String position_name;
    }
}
