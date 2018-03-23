package io.crossbar.autobahn.sdjx.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Administrator on 2018/3/20 0020.
 */
@Entity
public class MessageDB {
    @Id
    long id;
    String type;
    String val;
    String ntel;
    String to_ntel;
    String curr_approve_name;
    String store_msg_leixing;
    int msg_sendtime;
    String apply_name;
    String flow_ptype;
    String apply_no;
    int curr_note_id;
    int next_note_id;
    int approve_res;
    String approve_rea;
    String store_msg_id;

    public MessageDB() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getNtel() {
        return ntel;
    }

    public void setNtel(String ntel) {
        this.ntel = ntel;
    }

    public String getTo_ntel() {
        return to_ntel;
    }

    public void setTo_ntel(String to_ntel) {
        this.to_ntel = to_ntel;
    }

    public String getCurr_approve_name() {
        return curr_approve_name;
    }

    public void setCurr_approve_name(String curr_approve_name) {
        this.curr_approve_name = curr_approve_name;
    }

    public String getStore_msg_leixing() {
        return store_msg_leixing;
    }

    public void setStore_msg_leixing(String store_msg_leixing) {
        this.store_msg_leixing = store_msg_leixing;
    }

    public int getMsg_sendtime() {
        return msg_sendtime;
    }

    public void setMsg_sendtime(int msg_sendtime) {
        this.msg_sendtime = msg_sendtime;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getFlow_ptype() {
        return flow_ptype;
    }

    public void setFlow_ptype(String flow_ptype) {
        this.flow_ptype = flow_ptype;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public int getCurr_note_id() {
        return curr_note_id;
    }

    public void setCurr_note_id(int curr_note_id) {
        this.curr_note_id = curr_note_id;
    }

    public int getNext_note_id() {
        return next_note_id;
    }

    public void setNext_note_id(int next_note_id) {
        this.next_note_id = next_note_id;
    }

    public int getApprove_res() {
        return approve_res;
    }

    public void setApprove_res(int approve_res) {
        this.approve_res = approve_res;
    }

    public String getApprove_rea() {
        return approve_rea;
    }

    public void setApprove_rea(String approve_rea) {
        this.approve_rea = approve_rea;
    }

    public String getStore_msg_id() {
        return store_msg_id;
    }

    public void setStore_msg_id(String store_msg_id) {
        this.store_msg_id = store_msg_id;
    }
}
