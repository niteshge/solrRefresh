package com.growthenabler.solrRefresh.DaoImpl;

import com.growthenabler.solrRefresh.Dao.TechnologyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class TechnologyDaoImpl implements TechnologyDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Map<String, Object>> getAllTechnologyWithSubSegment() {
        String TECHNOLOGY_WITH_SUB_SEGMENT = "SELECT mh.`name` AS horizontal, tss.`segment_name` AS segment_name, tss2.`segment_name` AS segment1, tss3.`segment_name` AS segment_name2, tss4.`segment_name` AS segment_name3, tss5.`segment_name` AS segment_name4 FROM `mtb_horizontal` mh\n" +
                "LEFT JOIN `mtb_technology_sub_segment` tss ON tss.`technology_id` = mh.`id` AND tss.`parent_id` = 0\n" +
                "LEFT JOIN `mtb_technology_sub_segment` tss2 ON tss2.`parent_id` = tss.`id`\n" +
                "LEFT JOIN `mtb_technology_sub_segment` tss3 ON tss3.`parent_id` = tss2.`id`\n" +
                "LEFT JOIN `mtb_technology_sub_segment` tss4 ON tss4.`parent_id` = tss3.`id`\n" +
                "LEFT JOIN `mtb_technology_sub_segment` tss5 ON tss5.`parent_id` = tss4.`id`";
        List<Map<String, Object>> technologyList = null;
        technologyList = namedParameterJdbcTemplate.queryForList(TECHNOLOGY_WITH_SUB_SEGMENT, new MapSqlParameterSource());
        return technologyList;
    }
}
