package vttp.paf.day23.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component("breweries")
public class breweries {
    
    public List<String> brewlist = new LinkedList<>();

    public List<String> getBrewlist() {
        return brewlist;
    }

    public void setBrewlist(List<String> brewlist) {
        this.brewlist = brewlist;
    }
}
