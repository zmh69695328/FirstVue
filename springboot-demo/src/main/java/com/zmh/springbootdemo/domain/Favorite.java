package com.zmh.springbootdemo.domain;

public class Favorite {
    private String userid;// name
    private String newsid;
    private String _id;

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

    /**
     * @return the _id
     */
    public String get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(String _id) {
        this._id = _id;
    }

}