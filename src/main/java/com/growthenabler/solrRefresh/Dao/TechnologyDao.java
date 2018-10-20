package com.growthenabler.solrRefresh.Dao;

import java.util.List;
import java.util.Map;

public interface TechnologyDao {

    public List<Map<String, Object>> getAllTechnologyWithSubSegment();
}
