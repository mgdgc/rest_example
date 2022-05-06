package xyz.ridsoft.restexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xyz.ridsoft.restexample.dto.StatisticDto;
import xyz.ridsoft.restexample.service.StatisticService;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class StatisticController {

    @Autowired
    private StatisticService service;

    @GetMapping("/api/requests/monthly")
    public List<StatisticDto> monthlyRequest() {
        return service.getMonthlyRequest();
    }

    @GetMapping("/api/requests/daily")
    public List<StatisticDto> dailyRequest() {
        return service.getDailyRequest();
    }

    @GetMapping("/api/logins/daily")
    public List<StatisticDto> dailyLoginRequest() {
        return service.getDailyLoginRequest();
    }

    @GetMapping("/api/logins/monthly/by-org")
    public List<StatisticDto> monthlyLoginByOrg() {
        return service.getMonthlyLoginByOrg();
    }

    @Value("${open_api_service_key}")
    private String openApiServiceKey;

    @GetMapping("/api/logins/daily/without-holiday")
    public List<StatisticDto> dailyLoginWithoutHoliday() throws Exception {
        List<StatisticDto> stats = service.getLoginsWithDate();

        ArrayList<Integer> years = new ArrayList<>();
        for (StatisticDto s : stats) {
            Calendar calendar = convertDateString(s.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            if (!years.contains(year)) {
                years.add(year);
            }
        }

        ArrayList<String> holidays = new ArrayList<>();
        for (Integer y : years) {
            String s = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo" +
                    "?ServiceKey=" + openApiServiceKey +
                    "&solYear=" + y;

            URL url = new URL(s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            conn.connect();

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());
            NodeList dates = doc.getElementsByTagName("locdate");

            for (int i = 0; i < dates.getLength(); i++) {
                Node locdate = dates.item(i);
                holidays.add(locdate.getTextContent().substring(2, 8));
            }

            conn.disconnect();
        }

        for (String s : holidays) {
            System.out.println(s);
        }

        ArrayList<StatisticDto> result = new ArrayList<>();
        for (StatisticDto s : stats) {
            if (!holidays.contains(s.getDate())) {
                result.add(s);
            }
        }


        return stats;
    }

    private Calendar convertDateString(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyMMdd");
        Date date = format.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
