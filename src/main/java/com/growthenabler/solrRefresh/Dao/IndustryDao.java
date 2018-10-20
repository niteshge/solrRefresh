package com.growthenabler.solrRefresh.Dao;

import java.util.List;
import java.util.Map;

public interface IndustryDao {

    public List<Map<String, Object>> getAllIndustriesWithSubIndustry();
}
