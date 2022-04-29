package xyz.ridsoft.restexample.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface StatisticMapper {

    public HashMap<String, Object> selectYearLogin(String year);

}
