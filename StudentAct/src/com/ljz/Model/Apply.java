package com.ljz.Model;

public class Apply {
    private String applyname;
    private String acttypename;
    private String applydesc;
    private String applytime;

    public Apply() {
        super();
    }


    public Apply(String applyname,String applydesc,String applytime){
        super();
        this.applyname = applyname;
        this.applydesc = applydesc;
        this.applytime = applytime;
    }


    public String getApplyName() {
        return applyname;
    }


    public String getActtypename() {
        return acttypename;
    }
    public String getApplyDesc() {
        return applydesc;
    }

    public String getApplytime() {
        return applytime;
    }



}

