package com.kuaiba.site.service;

import com.kuaiba.site.core.exception.SecurityException;
import com.kuaiba.site.front.vo.BookmarkVO;

/**
 * 业务审核
 * @author larry.qi
 */
public interface Auditable {
	
	 /**
     * 审核拒绝
     * @param id
     * @param remark
     */
    void auditRecmds(Long id, String remark) throws SecurityException;
    
    /**
     * 审核通过
     * @param id
     * @param remark
     */
    void auditRecmds(Long id, BookmarkVO vo) throws SecurityException;

}
