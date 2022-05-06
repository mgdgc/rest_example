package xyz.ridsoft.restexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {

    private String date;
    private String month;
    private String org;
    private int count;

}
