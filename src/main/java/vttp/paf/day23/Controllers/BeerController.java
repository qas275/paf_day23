package vttp.paf.day23.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.paf.day23.service.BeerService;

@Controller
@RequestMapping(path = "/")
public class BeerController {
    //TODO LOOK ASIDE CACHE
    //1. search term comes in from html
    //2. check redis if result is available, serve if available to html
    //3. if not get result from mysql
    //4. save result to redis with TTL(time to leave) 5min
    //5. serve result to html
    @Autowired
    private BeerService bSvc;

    @GetMapping(path = "/")
    public String showHome(Model model){
        List<String> styles = bSvc.searchStyles();
        System.out.println("STYLES SIZE >>>"+styles.size());
        model.addAttribute("styles", styles);
        return "index";
    }

    @PostMapping(path = "/search")
    public String searchBrew(@RequestParam String style, Model model){
        System.out.println(style);
        List<String> brewList = bSvc.searchBrewList(style).getBrewlist();
        System.out.println(brewList.size());
        model.addAttribute("breweries", brewList);
        return "breweries";
    }
}
