package com.vitec.vhsm.domain;

import javax.persistence.*;

@Entity
public class CmdProcessingRateItem {
    @Column(name = "load")
    private String load;

    @Id
    @Column(name = "sys_date")
    private String sys_date;

    public CmdProcessingRateItem() {
    }

    public CmdProcessingRateItem(String load, String sys_date) {
        this.load = load;
        this.sys_date = sys_date;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getSys_date() {
        return sys_date;
    }

    public void setSys_date(String sys_date) {
        this.sys_date = sys_date;
    }
}
