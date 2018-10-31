package cc.mrbird.gaoxz.controller;

import cc.mrbird.gaoxz.dao.FlightDao;
import cc.mrbird.gaoxz.domain.FlightEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List welcome(Map<String, Object> model) {

		FlightEx flights= flightDao.getTheHistoryDataOfTheFlightInTargetDate("2018-11-13", "ZH9114");

		List<String> keyList = new ArrayList<String>();
		keyList.addAll(flights.getDetail().keySet());

		List result = new ArrayList();
        for (String key:keyList
             ) {

            Map one = new HashMap();
            one.put("name", "x");

            List b = new ArrayList();
            b.add(key);
            b.add(flights.getDetail().get(key));

            one.put("value", b);

            result.add(one);

        }

		return result;
	}

	@RequestMapping("/findAll")
	public List findAll(Map<String, Object> model) {
		List result = flightDao.getTheNewestData("2018-12-29","", "", "");

		return result;
	}

}