package com.growthenabler.solrRefresh.DaoImpl;

import com.growthenabler.solrRefresh.Dao.JsonDataSolr;
import com.growthenabler.solrRefresh.home.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JsonDataSolrImpl implements JsonDataSolr {

    private final Logger log = LoggerFactory.getLogger(JsonDataSolrImpl.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Long updateTechnologyAndIndustryToJsonSolrData(String technologySubSegments, String industrySubSegments) {
        try {
            log.info("updating into mtb_json_data_for_solr");
            String sql = "update mtb_json_data_for_solr \n" +
                    "set industries_master_json_data=:industrySubSegments, \n" +
                    "technologies_master_json_data=:technologySubSegments \n" +
                    "where id=:id";
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("id", 1);
            parameter.put("technologySubSegments", technologySubSegments);
            parameter.put("industrySubSegments", industrySubSegments);
            int row = namedParameterJdbcTemplate.update(sql, parameter);
            return (long) row;
        } catch (Exception e) {
            log.info(e.getMessage());
            return 0L;
        }
    }
}
