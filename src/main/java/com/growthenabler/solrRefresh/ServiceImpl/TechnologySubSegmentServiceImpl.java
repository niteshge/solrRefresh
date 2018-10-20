package com.growthenabler.solrRefresh.ServiceImpl;

import com.growthenabler.solrRefresh.Dao.TechnologyDao;
import com.growthenabler.solrRefresh.Service.TechnologySubSegmentService;
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
public class TechnologySubSegmentServiceImpl implements TechnologySubSegmentService {

    private final Logger log = LoggerFactory.getLogger(TechnologySubSegmentServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TechnologyDao technologyDao;

    @Override
    public Map<String, Object> getAllTechnologyWithSubSegment() {
        List<Map<String, Object>> mapList = technologyDao.getAllTechnologyWithSubSegment();

        HashMap<String, Object> resultMap = new HashMap<>();
        for (Map map : mapList){

            String horizontalName = String.valueOf(map.get("horizontal"));
            if (horizontalName != null) {
                HashMap<String, Object> segment1 = new HashMap<String, Object>();
                if (!resultMap.containsKey(horizontalName)) {
                    resultMap.put(horizontalName, segment1);
                } else {
                    segment1 = (HashMap) resultMap.get(horizontalName);
                }
                //-------------first level
                String segmetnLev1 = String.valueOf(map.get("segment_name"));
                if (segmetnLev1 != null && !(segmetnLev1.equals("null"))) {
                    HashMap<String, Object> segment2 = new HashMap<>();
                    if (!segment1.containsKey(segmetnLev1)) {
                        segment1.put(segmetnLev1, segment2);
                    } else {
                        segment2 = (HashMap) segment1.get(segmetnLev1);
                    }
                    //-------------second level
                    String segmetnLev2 = String.valueOf(map.get("segment1"));
                    if (segmetnLev2 != null && !(segmetnLev2.equals("null"))) {
                        HashMap<String, Object> segment3 = new HashMap<>();
                        if (!segment2.containsKey(segmetnLev2)) {
                            segment2.put(segmetnLev2, segment3);
                        } else {
                            segment3 = (HashMap) segment2.get(segmetnLev2);
                        }
                        //------------3rd level
                        String segmetnLev5 = String.valueOf(map.get("segment_name2"));
                        if (segmetnLev5 != null && !(segmetnLev5.equals("null"))) {
                            HashMap<String, Object> segment5 = new HashMap<>();
                            if (!segment3.containsKey(segmetnLev5)) {
                                segment3.put(segmetnLev5, segment5);
                            } else {
                                segment5 = (HashMap) segment3.get(segmetnLev5);
                            }
                            String segmetnLev6 = String.valueOf(map.get("segment_name3"));
                            if (segmetnLev6 != null && !(segmetnLev6.equals("null"))) {
                                HashMap<String, Object> segment6 = new HashMap<>();
                                if (!segment5.containsKey(segmetnLev6)) {
                                    segment5.put(segmetnLev6, segment6);
                                } else {
                                    segment6 = (HashMap) segment5.get(segmetnLev6);
                                }
                                String segmetnLev3 = String.valueOf(map.get("segment_name4"));
                                if (segmetnLev3 != null && !(segmetnLev3.equals("null"))) {
                                    HashMap<String, Object> segment4 = new HashMap<>();
                                    if (!segment6.containsKey(segmetnLev3)) {
                                        segment6.put(segmetnLev3, segment4);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return resultMap;
    }
}
