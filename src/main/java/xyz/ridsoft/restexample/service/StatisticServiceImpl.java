package xyz.ridsoft.restexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ridsoft.restexample.dao.StatisticMapper;
import xyz.ridsoft.restexample.dto.StatisticDto;

import java.util.HashMap;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    
    @Autowired
    private StatisticMapper uMapper;

    @Override
    public HashMap<String, Object> yearloginNum(String year) {
        HashMap<String, Object> retVal = new HashMap<String, Object>();

        try {
            retVal = uMapper.selectYearLogin(year);
            retVal.put("year", year);
            retVal.put("is_success", true);

        } catch (Exception e) {
            retVal.put("totCnt", -999);
            retVal.put("year", year);
            retVal.put("is_success", false);
        }

        return retVal;
    }

    @Override
    public List<StatisticDto> getMonthlyRequest() {
        return uMapper.getMonthlyRequest();
    }

    @Override
    public List<StatisticDto> getDailyRequest() {
        return uMapper.getDailyRequest();
    }

    @Override
    public List<StatisticDto> getDailyLoginRequest() {
        return uMapper.getDailyLoginRequest();
    }

    @Override
    public List<StatisticDto> getMonthlyLoginByOrg() {
        return uMapper.getMonthlyLoginByOrg();
    }

    @Override
    public List<StatisticDto> getLoginsWithDate() {
        return uMapper.getLoginsWithDate();
    }

}