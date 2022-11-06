package com.ljz.Model;

public class Member {
    private String membername;
    private String membersex;
    private int membertel;
    private int memberage;
    private String membermajor;

    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Member(String membername,String membersex,int membertel,int memberage,String membermajor) {
        this.membername = membername;
        this.membersex = membersex;
        this.membertel = membertel;
        this.memberage = memberage;
        this.membermajor = membermajor;

    }

    public String getMemberName() {
        return membername;
    }

    public String getMemberSex() {
        return membersex;
    }

    public int getMemberTel() {
        return membertel;
    }

    public int getMemberAge() {
        return memberage;
    }

    public String getMemberMajor() {
        return membermajor;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public void setMembersex(String membersex) {
        this.membersex = membersex;
    }

    public void setMembertel(int membertel) {
        this.membertel = membertel;
    }

    public void setMemberage(int memberage) {
        this.memberage = memberage;
    }

    public void setMembermajor(String membermajor) {
        this.membermajor = membermajor;
    }
}
