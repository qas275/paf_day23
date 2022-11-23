package vttp.paf.day23.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static vttp.paf.day23.repositories.Queries.*;

@Repository
public class BeerRepo {
    
    @Autowired
    private JdbcTemplate template;
    
    public SqlRowSet searchStylesRowSet(){
        SqlRowSet rs = template.queryForRowSet(SQL_SEARCH_ALL_STYLE);
        return rs;
    }

    public SqlRowSet searchBrewQueryForRS(String style){
        String styleFormat = "%" + style + "%";
        SqlRowSet rs = template.queryForRowSet(SQL_SEARCH_BREWERIES_BY_STYLE, styleFormat);
        return rs;
    }
}
