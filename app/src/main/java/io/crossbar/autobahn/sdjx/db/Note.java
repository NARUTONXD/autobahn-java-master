package io.crossbar.autobahn.sdjx.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Note {

    @Id
    long id;

    String html_css;
    String flow_no;
    String rankid;

    String flow_note_id;
    String arr_complayid;
    String arr_departmentid;
    String arr_tdepartmentid;
    String arr_position_id;
    String arr_pname;
    String arr_tel;

    int PROCESS_COUNT;//流程号

    public Note(long id, String html_css, String flow_no, String rankid, String flow_note_id, String arr_complayid, String arr_departmentid, String arr_tdepartmentid, String arr_position_id, String arr_pname, String arr_tel, int PROCESS_COUNT) {
        this.id = id;
        this.html_css = html_css;
        this.flow_no = flow_no;
        this.rankid = rankid;
        this.flow_note_id = flow_note_id;
        this.arr_complayid = arr_complayid;
        this.arr_departmentid = arr_departmentid;
        this.arr_tdepartmentid = arr_tdepartmentid;
        this.arr_position_id = arr_position_id;
        this.arr_pname = arr_pname;
        this.arr_tel = arr_tel;
        this.PROCESS_COUNT = PROCESS_COUNT;
    }

    public String getFlow_no() {
        return flow_no;
    }

    public void setFlow_no(String flow_no) {
        this.flow_no = flow_no;
    }

    public String getRankid() {
        return rankid;
    }

    public void setRankid(String rankid) {
        this.rankid = rankid;
    }

    public int getPROCESS_COUNT() {
        return PROCESS_COUNT;
    }

    public void setPROCESS_COUNT(int PROCESS_COUNT) {
        this.PROCESS_COUNT = PROCESS_COUNT;
    }

    public String getHtml_css() {
        return html_css;
    }

    public void setHtml_css(String html_css) {
        this.html_css = html_css;
    }
    public String getFlow_note_id() {
        return flow_note_id;
    }

    public void setFlow_note_id(String flow_note_id) {
        this.flow_note_id = flow_note_id;
    }

    public String getArr_complayid() {
        return arr_complayid;
    }

    public void setArr_complayid(String arr_complayid) {
        this.arr_complayid = arr_complayid;
    }

    public String getArr_departmentid() {
        return arr_departmentid;
    }

    public void setArr_departmentid(String arr_departmentid) {
        this.arr_departmentid = arr_departmentid;
    }

    public String getArr_tdepartmentid() {
        return arr_tdepartmentid;
    }

    public void setArr_tdepartmentid(String arr_tdepartmentid) {
        this.arr_tdepartmentid = arr_tdepartmentid;
    }

    public String getArr_position_id() {
        return arr_position_id;
    }

    public void setArr_position_id(String arr_position_id) {
        this.arr_position_id = arr_position_id;
    }

    public String getArr_pname() {
        return arr_pname;
    }

    public void setArr_pname(String arr_pname) {
        this.arr_pname = arr_pname;
    }

    public String getArr_tel() {
        return arr_tel;
    }

    public void setArr_tel(String arr_tel) {
        this.arr_tel = arr_tel;
    }




    public Note() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }




}
