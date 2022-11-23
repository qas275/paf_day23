package vttp.paf.day23.service;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import vttp.paf.day23.model.*;

import vttp.paf.day23.repositories.BeerRepo;

@Service
public class BeerService {
    @Autowired
    private BeerRepo repo;

    @Autowired
    @Qualifier("RedisTemplate")
    RedisTemplate<String, breweries> beerRedis;

    public breweries searchBrewList(String style){
        breweries breweries = new breweries();
        boolean styleInRedis = beerRedis.hasKey(style);
        if(styleInRedis){
            System.out.println("IN REDIS!!!!!");
            breweries = (breweries) beerRedis.opsForValue().get(style);
        }else{
            System.out.println("NOT IN REDIS!!!!!");
            SqlRowSet rs = repo.searchBrewQueryForRS(style);
            while(rs.next()){
                breweries.brewlist.add(rs.getString("BREWERY"));
            }
            beerRedis.opsForValue().setIfAbsent(style, breweries, Duration.ofMinutes(5));
        }
        return breweries;
    }

    public List<String> searchStyles(){
        SqlRowSet stylesRS = repo.searchStylesRowSet();
        List<String> styles = new LinkedList<>();
        while(stylesRS.next()){
            styles.add(stylesRS.getString("style_name"));
        }
        return styles;
    }
}
