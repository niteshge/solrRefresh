package com.growthenabler.solrRefresh.home;

import com.growthenabler.solrRefresh.Dao.JsonDataSolr;
import com.growthenabler.solrRefresh.Service.IndustrySubSegmentService;
import com.growthenabler.solrRefresh.Service.TechnologySubSegmentService;
import com.growthenabler.solrRefresh.ServiceImpl.TechnologySubSegmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    TechnologySubSegmentService technologySubSegmentService;

    @Autowired
    IndustrySubSegmentService industrySubSegmentService;

    @Autowired
    JsonDataSolr jsonDataSolr;

    public void home(){
        log.info("hello");
        Map<String, Object> technologySubSegmentResult =  technologySubSegmentService.getAllTechnologyWithSubSegment();
        log.info(technologySubSegmentResult.toString());

        Map<String, Object> industrySubSegmentResult =  industrySubSegmentService.getAllIndustriesWithSubIndustry();
        log.info(industrySubSegmentResult.toString());
        for (Map.Entry<String,Object> entry : industrySubSegmentResult.entrySet()) {
            log.info("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            String repl = entry.getValue().toString().replaceAll("(\\r|\\n|\\r\\n)+","");
            industrySubSegmentResult.put(entry.getKey(),repl);
        }
        log.info(industrySubSegmentResult.toString());

        Long updateStatus = jsonDataSolr.updateTechnologyAndIndustryToJsonSolrData(technologySubSegmentResult.toString(),industrySubSegmentResult.toString());
        if(updateStatus>0){
            log.info("Successfull updated in the json data table");
        }else{
            log.error("update in the json data table failed");
        }

    }

}
