package com.growthenabler.solrRefresh.ServiceImpl;

import com.growthenabler.solrRefresh.Dao.IndustryDao;
import com.growthenabler.solrRefresh.Dao.TechnologyDao;
import com.growthenabler.solrRefresh.Service.IndustrySubSegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IndustrySubSegmentServiceImpl implements IndustrySubSegmentService {


    private final Logger log = LoggerFactory.getLogger(TechnologySubSegmentServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    IndustryDao industryDao;

    @Override
    public Map<String, Object> getAllIndustriesWithSubIndustry() {
        List<Map<String, Object>> mapList = industryDao.getAllIndustriesWithSubIndustry();

        HashMap<String, Object> resultMap = new HashMap<>();
        for (Map map : mapList){

            String industryName = String.valueOf(map.get("industry"));
            if (industryName != null) {
                HashMap<String, Object> industry = new HashMap<String, Object>();
                if (!resultMap.containsKey(industryName)) {
                    resultMap.put(industryName, industry);
                } else {
                    industry = (HashMap) resultMap.get(industryName);
                }
                //-------------first level
                String industryLev1 = String.valueOf(map.get("industryLev1"));
                if (industryLev1 != null && !(industryLev1.equals("null"))) {
                    HashMap<String, Object> segment2 = new HashMap<>();
                    if (!industry.containsKey(industryLev1)) {
                        industry.put(industryLev1, segment2);
                    } else {
                        segment2 = (HashMap) industry.get(industryLev1);
                    }
                    //-------------second level
                    String industryLev2 = String.valueOf(map.get("industryLev2"));
                    if (industryLev2 != null && !(industryLev2.equals("null"))) {
                        HashMap<String, Object> segment3 = new HashMap<>();
                        if (!segment2.containsKey(industryLev2)) {
                            segment2.put(industryLev2, segment3);
                        } else {
                            segment3 = (HashMap) segment2.get(industryLev2);
                        }
                        //------------3rd level
                        String industryLev3 = String.valueOf(map.get("industryLev3"));
                        if (industryLev3 != null && !(industryLev3.equals("null"))) {
                            if (!segment3.containsKey(industryLev3)) {
                                segment3.put(industryLev3, industryLev3);
                            }
                        }
                    }
                }
            }
        }
        return resultMap;
    }
}
