package io.crossbar.autobahn.sdjx.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class Person  implements Serializable {

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

    public Person(int organ_id, String organ_company, String organ_department, String organ_tdepartment, String organ_position, String organ_name, String organ_type, String organ_tel, int position_id, int position_levelid, int rank_id, int rank_level, String rank_name, String position_name, String organ_alias, String organ_email, String organ_famale, int account_state, int phone_isonline, int pad_isonline, int account_teldisplay, String departmentname, String organ_tdepartmentname) {
        this.organ_id = organ_id;
        this.organ_company = organ_company;
        this.organ_department = organ_department;
        this.organ_tdepartment = organ_tdepartment;
        this.organ_position = organ_position;
        this.organ_name = organ_name;
        this.organ_type = organ_type;
        this.organ_tel = organ_tel;
        this.position_id = position_id;
        this.position_levelid = position_levelid;
        this.rank_id = rank_id;
        this.rank_level = rank_level;
        this.rank_name = rank_name;
        this.position_name = position_name;
        this.organ_alias = organ_alias;
        this.organ_email = organ_email;
        this.organ_famale = organ_famale;
        this.account_state = account_state;
        this.phone_isonline = phone_isonline;
        this.pad_isonline = pad_isonline;
        this.account_teldisplay = account_teldisplay;
        this.departmentname = departmentname;
        this.organ_tdepartmentname = organ_tdepartmentname;
    }

    public int getOrgan_id() {
        return organ_id;
    }

    public void setOrgan_id(int organ_id) {
        this.organ_id = organ_id;
    }

    public String getOrgan_company() {
        return organ_company;
    }

    public void setOrgan_company(String organ_company) {
        this.organ_company = organ_company;
    }

    public String getOrgan_department() {
        return organ_department;
    }

    public void setOrgan_department(String organ_department) {
        this.organ_department = organ_department;
    }

    public String getOrgan_tdepartment() {
        return organ_tdepartment;
    }

    public void setOrgan_tdepartment(String organ_tdepartment) {
        this.organ_tdepartment = organ_tdepartment;
    }

    public String getOrgan_position() {
        return organ_position;
    }

    public void setOrgan_position(String organ_position) {
        this.organ_position = organ_position;
    }

    public String getOrgan_name() {
        return organ_name;
    }

    public void setOrgan_name(String organ_name) {
        this.organ_name = organ_name;
    }

    public String getOrgan_type() {
        return organ_type;
    }

    public void setOrgan_type(String organ_type) {
        this.organ_type = organ_type;
    }

    public String getOrgan_tel() {
        return organ_tel;
    }

    public void setOrgan_tel(String organ_tel) {
        this.organ_tel = organ_tel;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getPosition_levelid() {
        return position_levelid;
    }

    public void setPosition_levelid(int position_levelid) {
        this.position_levelid = position_levelid;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public int getRank_level() {
        return rank_level;
    }

    public void setRank_level(int rank_level) {
        this.rank_level = rank_level;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getOrgan_alias() {
        return organ_alias;
    }

    public void setOrgan_alias(String organ_alias) {
        this.organ_alias = organ_alias;
    }

    public String getOrgan_email() {
        return organ_email;
    }

    public void setOrgan_email(String organ_email) {
        this.organ_email = organ_email;
    }

    public String getOrgan_famale() {
        return organ_famale;
    }

    public void setOrgan_famale(String organ_famale) {
        this.organ_famale = organ_famale;
    }

    public int getAccount_state() {
        return account_state;
    }

    public void setAccount_state(int account_state) {
        this.account_state = account_state;
    }

    public int getPhone_isonline() {
        return phone_isonline;
    }

    public void setPhone_isonline(int phone_isonline) {
        this.phone_isonline = phone_isonline;
    }

    public int getPad_isonline() {
        return pad_isonline;
    }

    public void setPad_isonline(int pad_isonline) {
        this.pad_isonline = pad_isonline;
    }

    public int getAccount_teldisplay() {
        return account_teldisplay;
    }

    public void setAccount_teldisplay(int account_teldisplay) {
        this.account_teldisplay = account_teldisplay;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getOrgan_tdepartmentname() {
        return organ_tdepartmentname;
    }

    public void setOrgan_tdepartmentname(String organ_tdepartmentname) {
        this.organ_tdepartmentname = organ_tdepartmentname;
    }
}
