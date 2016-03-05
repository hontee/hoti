package com.kuaiba.site.db.dao;

import com.kuaiba.site.db.entity.Track;
import com.kuaiba.site.db.entity.TrackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrackMapper {
	
    int countByExample(TrackExample example);

    int deleteByExample(TrackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Track record);

    List<Track> selectByExample(TrackExample example);

    Track selectByPrimaryKey(Long id);

    int updateByExample(@Param("record") Track record, @Param("example") TrackExample example);

    int updateByPrimaryKey(Track record);
}