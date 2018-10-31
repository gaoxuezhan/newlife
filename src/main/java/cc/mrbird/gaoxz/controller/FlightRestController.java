package cc.mrbird.gaoxz.controller;

import afu.org.checkerframework.checker.oigj.qual.O;
import cc.mrbird.gaoxz.dao.FlightDao;
import cc.mrbird.gaoxz.domain.FlightEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightRestController {

	private String message = "Hello World";

	@Autowired
	private FlightDao flightDao;

	@RequestMapping("/history")
	public Map welcome(@RequestParam String targetDate, @RequestParam String code) {

		System.out.println(code);
		System.out.println(targetDate);

		Integer yMin = 10000;
		Integer yMax = 0;
		String price;

		FlightEx flights= flightDao.getTheHistoryDataOfTheFlightInTargetDate(targetDate, code);

		List<String> keyList = new ArrayList<String>();
		keyList.addAll(flights.getDetail().keySet());

		List result = new ArrayList();
        for (String key:keyList
             ) {

            Map one = new HashMap();
            one.put("name", "x");

            List b = new ArrayList();
            b.add(key);
			price = flights.getDetail().get(key);
            b.add(price);

            if(Integer.valueOf(price) > yMax){
				yMax = Integer.valueOf(price);
			}

			if(Integer.valueOf(price) < yMin){
				yMin = Integer.valueOf(price);
			}

            one.put("value", b);

            result.add(one);

        }
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", result);
		resultMap.put("yMin", yMin - 20);
		resultMap.put("yMax", yMax + 20);

		return resultMap;
	}


	@RequestMapping("/findAll")
	public Map findAll(Map<String, Object> model) {
		List result = flightDao.getTheNewestData("2018-11-13","", "", "CZ3172,CA1383");

		Map<String, List> resultMap = new HashMap<String,List>();

		resultMap.put("result", result);
		return resultMap;
	}

	@RequestMapping("/findConditions")
	public Map findConditions(Map<String, Object> model) {
		List targetDates = flightDao.getTargetDates_distinct("", "", "", "");
		List departureCities = flightDao.getDepartureCity_distinct("", "", "", "");
		List arriveCities = flightDao.getArriveCity_distinct("", "", "", "");
		List codes = flightDao.getCode_distinct("", "", "", "");

		Map<String, List> resultMap = new HashMap<String,List>();
		resultMap.put("targetDates",targetDates );
		resultMap.put("departureCities",departureCities );
		resultMap.put("arriveCities",arriveCities );
		resultMap.put("codes",codes );

		return resultMap;
	}

}