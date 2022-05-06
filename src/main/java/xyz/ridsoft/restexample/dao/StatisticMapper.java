package xyz.ridsoft.restexample.dao;

import org.springframework.stereotype.Repository;
import xyz.ridsoft.restexample.dto.StatisticDto;

import java.util.HashMap;
import java.util.List;

@Repository
public interface StatisticMapper {

    public HashMap<String, Object> selectYearLogin(String year);
    public List<StatisticDto> getMonthlyRequest();
    public List<StatisticDto> getDailyRequest();
    public List<StatisticDto> getDailyLoginRequest();
    public List<StatisticDto> getMonthlyLoginByOrg();
    public List<StatisticDto> getLoginsWithDate();

}
