package xyz.ridsoft.restexample.service;

import org.springframework.stereotype.Service;
import xyz.ridsoft.restexample.dto.StatisticDto;

import java.util.HashMap;
import java.util.List;

public interface StatisticService {

    public HashMap<String,Object> yearloginNum (String year);
    public List<StatisticDto> getMonthlyRequest();
    public List<StatisticDto> getDailyRequest();
    public List<StatisticDto> getDailyLoginRequest();
    public List<StatisticDto> getMonthlyLoginByOrg();
    public List<StatisticDto> getLoginsWithDate();

}
