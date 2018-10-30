package cc.mrbird.gaoxz.dao;

import cc.mrbird.gaoxz.domain.FlightEx;

import java.util.List;

/**
 * Created by summer on 2017/5/5.
 */
public interface FlightDao {

    public List getTargetDates_distinct(String targetDate, String departureCity, String arriveCity, String code);

    public List getDepartureCity_distinct(String targetDate, String departureCity, String arriveCity, String code);

    public List getArriveCity_distinct(String targetDate, String departureCity, String arriveCity, String code);

    public List getCode_distinct(String targetDate, String departureCity, String arriveCity, String code);

    public List getTheNewestData(String targetDate, String departureCity, String arriveCity, String code);

    public FlightEx getTheHistoryDataOfTheFlightInTargetDate(String targetDate, String code);

}