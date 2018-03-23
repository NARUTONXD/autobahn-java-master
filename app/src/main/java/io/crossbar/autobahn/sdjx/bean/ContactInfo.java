package io.crossbar.autobahn.sdjx.bean;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class ContactInfo {
    public String name;

    public String getDment_lofficial() {
        return dment_lofficial;
    }

    public void setDment_lofficial(String dment_lofficial) {
        this.dment_lofficial = dment_lofficial;
    }

    public String dment_lofficial;
    public int count;



    public ContactInfo(String name,String dment_lofficial, int count, int count_onlint, int id) {
        this.name = name;
        this.count = count;
        this.count_onlint = count_onlint;
        this.id = id;
        this.dment_lofficial = dment_lofficial;
    }

    public int count_onlint;

    public int getId() {
        return id;
    }

    public int id;

    public int getCount_onlint() {
        return count_onlint;
    }
    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
