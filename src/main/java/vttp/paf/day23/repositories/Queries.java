package vttp.paf.day23.repositories;


public class Queries {
    
    public static final String SQL_SEARCH_BREWERIES_BY_STYLE = "select beers.id as ID, styles.style_name as STYLE, brew.name as BREWERY from beers join styles on beers.style_id = styles.id join breweries as brew on beers.brewery_id = brew.id where styles.style_name like ? order by beers.id limit 10";

    public static final String SQL_SEARCH_ALL_STYLE = "select distinct style_name from styles order by style_name";


}
