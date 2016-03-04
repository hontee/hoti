package com.kuaiba.site.db.entity;

import java.io.Serializable;
import java.util.Date;

public class BookmarkFollow implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long uid;

    private Long fid;

    private Date created;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}