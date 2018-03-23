package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class SalaryBean {

    /**
     * code : 1
     * msg : [{"salary_id":2,"salary_bid":2,"salary_name":"考勤测试","salary_beduty":"22.50","salary_daysattendance":10,"salary_bdaysattendance":12.5,"salary_actual":"22.50","salary_sickleave":"0.00","salary_basepay":"4500.00","salary_actualattendance":"3504.00","salary_achievements":"500.00","salary_performancescore":"0.00","salary_actualperformance":"0.00","salary_sumofbasicwages":"3504.00","salary_subsidies":"0.00","salary_totalsystem":"100.00","salary_highsubsidy":"0.00","salary_wagespaid":"3604.00","salary_socialsecurity":"254.00","salary_accumulationfund":"260.00","salary_greatlovefund":"45.00","salary_payroll":"3045.00","salary_employeeid":"k_2017120820","salary_time":1512734980,"salary_msupplementdays":0,"salary_otherdeductions":"0.00","salary_reissue":"0.00","salary_pattendancewages":"1504.00","salary_zattendancewages":"2000.00","salary_compassionateleave":0,"salary_marriageholiday":0,"salary_funeral":0,"salary_paternityleave":0,"salary_maternityleave":0,"salary_lackcard":0,"salary_late":0,"salary_remarks":"备注","salary_state":"正常"}]
     */

    public int code;
    public List<MsgBean> msg;

    public static class MsgBean {
        /**
         * salary_id : 2
         * salary_bid : 2
         * salary_name : 考勤测试
         * salary_beduty : 22.50
         * salary_daysattendance : 10
         * salary_bdaysattendance : 12.5
         * salary_actual : 22.50
         * salary_sickleave : 0.00
         * salary_basepay : 4500.00
         * salary_actualattendance : 3504.00
         * salary_achievements : 500.00
         * salary_performancescore : 0.00
         * salary_actualperformance : 0.00
         * salary_sumofbasicwages : 3504.00
         * salary_subsidies : 0.00
         * salary_totalsystem : 100.00
         * salary_highsubsidy : 0.00
         * salary_wagespaid : 3604.00
         * salary_socialsecurity : 254.00
         * salary_accumulationfund : 260.00
         * salary_greatlovefund : 45.00
         * salary_payroll : 3045.00
         * salary_employeeid : k_2017120820
         * salary_time : 1512734980
         * salary_msupplementdays : 0
         * salary_otherdeductions : 0.00
         * salary_reissue : 0.00
         * salary_pattendancewages : 1504.00
         * salary_zattendancewages : 2000.00
         * salary_compassionateleave : 0
         * salary_marriageholiday : 0
         * salary_funeral : 0
         * salary_paternityleave : 0
         * salary_maternityleave : 0
         * salary_lackcard : 0
         * salary_late : 0
         * salary_remarks : 备注
         * salary_state : 正常
         */

        public int salary_id;
        public int salary_bid;
        public String salary_name;
        public String salary_beduty;
        public int salary_daysattendance;
        public double salary_bdaysattendance;
        public String salary_actual;
        public String salary_sickleave;
        public String salary_basepay;
        public String salary_actualattendance;
        public String salary_achievements;
        public String salary_performancescore;
        public String salary_actualperformance;
        public String salary_sumofbasicwages;
        public String salary_subsidies;
        public String salary_totalsystem;
        public String salary_highsubsidy;
        public String salary_wagespaid;
        public String salary_socialsecurity;
        public String salary_accumulationfund;
        public String salary_greatlovefund;
        public String salary_payroll;
        public String salary_employeeid;
        public int salary_time;
        public int salary_msupplementdays;
        public String salary_otherdeductions;
        public String salary_reissue;
        public String salary_pattendancewages;
        public String salary_zattendancewages;
        public int salary_compassionateleave;
        public int salary_marriageholiday;
        public int salary_funeral;
        public int salary_paternityleave;
        public int salary_maternityleave;
        public int salary_lackcard;
        public int salary_late;
        public String salary_remarks;
        public String salary_state;
    }
}
