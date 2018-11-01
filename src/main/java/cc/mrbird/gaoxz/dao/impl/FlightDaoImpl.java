package cc.mrbird.gaoxz.dao.impl;


import cc.mrbird.gaoxz.dao.FlightDao;
import cc.mrbird.gaoxz.domain.Flight;
import cc.mrbird.gaoxz.domain.FlightEx;
import com.mongodb.client.DistinctIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by summer on 2017/5/5.
 */
@Primary
@Component
public class FlightDaoImpl implements FlightDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List getTargetDates_distinct(String targetDate, String departureCity, String arriveCity, String code){
        String whoAmI = "targetDate";
        targetDate = "";

        Query query = this.getQuery(targetDate, departureCity, arriveCity, code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,whoAmI));

        DistinctIterable<String> result = mongoTemplate.getCollection("products").distinct(whoAmI, query.getQueryObject(), String.class);

        return result.into(new ArrayList());
    }
    @Override
    public List getDepartureCity_distinct(String targetDate, String departureCity, String arriveCity, String code){
        String whoAmI = "departureCity";
        departureCity = "";

        Query query = this.getQuery(targetDate, departureCity, arriveCity, code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,whoAmI));
        DistinctIterable<String> result = mongoTemplate.getCollection("products").distinct(whoAmI, query.getQueryObject(), String.class);

        return result.into(new ArrayList());
    }
    @Override
    public List getArriveCity_distinct(String targetDate, String departureCity, String arriveCity, String code){
        String whoAmI = "arriveCity";
        arriveCity = "";

        Query query = this.getQuery(targetDate, departureCity, arriveCity, code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,whoAmI));
        DistinctIterable<String> result = mongoTemplate.getCollection("products").distinct(whoAmI, query.getQueryObject(), String.class);

        return result.into(new ArrayList());
    }
    @Override
    public List getCode_distinct(String targetDate, String departureCity, String arriveCity, String code){
        String whoAmI = "code";
        code = "";

        Query query = this.getQuery(targetDate, departureCity, arriveCity, code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,whoAmI));
        DistinctIterable<String> result = mongoTemplate.getCollection("products").distinct(whoAmI, query.getQueryObject(), String.class);

        return result.into(new ArrayList());
    }
    @Override
    public List getTheNewestData(String targetDate, String departureCity, String arriveCity, String code){
        Query query = this.getQuery(targetDate, departureCity, arriveCity, code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,"updateTime"));

        List<Flight> flights =  mongoTemplate.find(query, Flight.class, "products");

        Map<String, Flight> mapNewAll = new HashMap<String, Flight>();

        for (Flight flight :
                flights) {
            if (mapNewAll.containsKey(flight.getCode() + flight.getTargetDate())){
                Flight king = mapNewAll.get(flight.getCode() + flight.getTargetDate());
                if(king.getUpdateTime().toString().compareTo(flight.getUpdateTime().toString()) < 0){
                    mapNewAll.put(flight.getCode() + flight.getTargetDate(),flight);
                }
            }else{
                mapNewAll.put(flight.getCode() + flight.getTargetDate(),flight);
            }

        }

        List<Flight> result = new ArrayList<Flight>();
        result.addAll(mapNewAll.values());

        return result;
    }
    @Override
    public FlightEx getTheHistoryDataOfTheFlightInTargetDate(String targetDate, String code){
        Query query=getQuery(targetDate,"","", code);
        Sort.Direction direction=false? Sort.Direction.ASC: Sort.Direction.DESC;
        query.with(new Sort(direction,"updateTime"));
        List<Flight> flights=  mongoTemplate.find(query, Flight.class, "products");

        FlightEx result = new FlightEx();
        Map<String, String> detail = new LinkedHashMap<String, String>();
        result.setDetail(detail);

        for (Flight flight:
                flights) {
            if(StringUtils.isEmpty(result.getTarget())){
                result.setTarget(flight);
            }

            detail.put(flight.getUpdateTime(), flight.getPrice());
        }

        return result;
    }

    private Query getQuery(String targetDate, String departureCity, String arriveCity, String code){
        Criteria criteria = new Criteria();
        Query query = new Query();

        if(StringUtils.isEmpty(targetDate) == false){
            criteria.and("targetDate").in(targetDate.split(","));
//            query.addCriteria(Criteria.where("targetDate").is(targetDate));
        }

        if(StringUtils.isEmpty(departureCity) == false){
            criteria.and("departureCity").is(departureCity);
//            query.addCriteria(Criteria.where("departureCity").is(departureCity));
        }

        if(StringUtils.isEmpty(arriveCity) == false){
            criteria.and("arriveCity").is(arriveCity);
//            query.addCriteria(Criteria.where("arriveCity").is(arriveCity));
        }

        if(StringUtils.isEmpty(code) == false){
            criteria.and("code").in(code.split(","));
//            query.addCriteria(Criteria.where("code").is(code));
        }

        criteria.and("updateTime").gt("2018-10-30 00:00:00"); //filter some test data
        return query.addCriteria(criteria);

    }

//
//    public List getRoutesInTargetDate(String targetDate){
//        Criteria criteria = new Criteria();
//        Query query = new Query();
//
//        DistinctIterable<String> result = mongoTemplate.getCollection("products").distinct("updateTime", String.class);
//
//        return result.into(new ArrayList());
//    };
//
//    public List getTheNewestDataOfTheRoutesInTargetDate(String targetDate,String routes);
//
//    public List getTheHistoryDataOfTheFlightInTargetDate(String targetDate,String code);

}