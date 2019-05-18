package com.zmh.springbootdemo.domain;

public class History {
    private String userid;// name
    private String newsid;

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the newsid
     */
    public String getNewsid() {
        return newsid;
    }

    /**
     * @param newsid the newsid to set
     */
    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

}