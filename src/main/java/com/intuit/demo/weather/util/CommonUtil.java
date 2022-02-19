package com.intuit.demo.weather.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommonUtil {

    /**
     * Get the DateTime value from String .
     * @param datetimestr
     * @return
     */
    public static LocalDateTime getDateTimeFromStr(String datetimestr) {
        System.out.println("datetimestr" + datetimestr);
        String[] datetimeArr = datetimestr.split("\\s+");
        String[] dateArr = datetimeArr[0].split("-");
        String hour = datetimeArr[1];

        LocalDateTime dateTime = LocalDateTime.of(9999 , Integer.valueOf(dateArr[0]), Integer.valueOf(dateArr[1]), Integer.valueOf(hour), 0);
        System.out.println("dateTime" + dateTime.toString());
        return dateTime;
    }

}
