package com.growthenabler.solrRefresh.DaoImpl;

import com.growthenabler.solrRefresh.Dao.IndustryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class IndustryDaoImpl implements IndustryDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Map<String, Object>> getAllIndustriesWithSubIndustry(){
        String INDUSTRY_WITH_SUB_INDUSTRIES = "SELECT mi.`name` AS industry, iss.`segment_name` AS industryLev1, iss2.`segment_name` AS industryLev2, iss3.`segment_name` AS industryLev3  FROM `mtb_industries` mi\n" +
                "LEFT JOIN `mtb_industry_sub_segment` iss ON iss.`industry_id` = mi.`id` AND iss.`parent_id` IS NULL\n" +
                "LEFT JOIN `mtb_industry_sub_segment` iss2 ON iss2.`parent_id` = iss.`id`\n" +
                "LEFT JOIN `mtb_industry_sub_segment` iss3 ON iss3.`parent_id` = iss2.`id`";
        List<Map<String, Object>> industryList = null;
        industryList = namedParameterJdbcTemplate.queryForList(INDUSTRY_WITH_SUB_INDUSTRIES, new MapSqlParameterSource());
        return industryList;
    }
}
