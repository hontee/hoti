package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class GroupBookmark implements Serializable {

	private static final long serialVersionUID = -5050584085999709617L;

	private Long gid;

    private Long bmid;

    private Date created;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getBmid() {
        return bmid;
    }

    public void setBmid(Long bmid) {
        this.bmid = bmid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}