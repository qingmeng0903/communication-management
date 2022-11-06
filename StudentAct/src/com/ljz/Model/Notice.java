package com.ljz.Model;

public class Notice {

    private String noticeTitle;
    private String noticeText;
    private String noticeTime;

    public Notice() {
        super();
    }

    public Notice(String noticeTitle, String noticeText, String noticeTime) {
        this.noticeTitle = noticeTitle;
        this.noticeText = noticeText;
        this.noticeTime = noticeTime;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setNoticeText(String noticeText) {
        this.noticeText = noticeText;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}