package cc.mrbird.gaoxz.controller;

import afu.org.checkerframework.checker.oigj.qual.O;
import cc.mrbird.gaoxz.dao.FlightDao;
import cc.mrbird.gaoxz.domain.FlightEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FlightRestController {

	private String message = "Hello World";

	@Autowired
	private FlightDao flightDao;

	@RequestMapping("/history")
	public Map welcome(@RequestParam String targetDate, @RequestParam String code) {

//		System.out.println(code);
//		System.out.println(targetDate);

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
	public Map findAll(@RequestParam String targetDate, @RequestParam String code, @RequestParam String departureCity, @RequestParam String arriveCity) {

	    System.out.println(targetDate);
        System.out.println(code);
        System.out.println(departureCity);
        System.out.println(arriveCity);

        targetDate = targetDate.replace("\"", "");
        targetDate = targetDate.replace("[", "");
        targetDate = targetDate.replace("]", "");
        targetDate = targetDate.replace("null", "");

        code = code.replace("\"", "");
        code = code.replace("[", "");
        code = code.replace("]", "");
        code = code.replace("null", "");

        departureCity = departureCity.replace("null", "");
        arriveCity = arriveCity.replace("null", "");

		List result = flightDao.getTheNewestData(targetDate,departureCity, arriveCity, code);

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

        Collections.sort(targetDates,new Comparator<String>(){
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });

        Collections.sort(codes,new Comparator<String>(){
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });

        Collections.sort(departureCities,new Comparator<String>(){
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });

        Collections.sort(arriveCities,new Comparator<String>(){
            public int compare(String arg0, String arg1) {
                return arg0.compareTo(arg1);
            }
        });


        Map<String, List> resultMap = new HashMap<String,List>();
		resultMap.put("targetDates",targetDates );
		resultMap.put("departureCities",departureCities );
		resultMap.put("arriveCities",arriveCities );
		resultMap.put("codes",codes );

		return resultMap;
	}

}