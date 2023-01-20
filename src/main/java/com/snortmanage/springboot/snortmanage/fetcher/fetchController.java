package com.snortmanage.springboot.snortmanage.fetcher;

import java.util.regex.*;
import java.util.List;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class fetchController {
    @Autowired
    SnortRuleRepo repo;

    @GetMapping("/rulefetch")
    public String fetch() {
        System.out.println("accepted from fetch");

        return "view.jsp";

    }

    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidIPAddress(String ip) {

        String zeroTo255 = "(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
        String regex = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;


        Pattern p = Pattern.compile(regex);


        if (ip == null) {
            return false;
        }
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    @PostMapping("/fetchrule")
    public String fetchdis(@RequestParam("search term") String search, ModelMap model) {
        System.out.println("U entered  " + search);
        //ModelAndView mv = new ModelAndView();
        if (search.matches("[a-zA-Z]+")) {
            System.out.println("protocol");
            List<SnortRuleConfig> rules = repo.findByprotocol(search);
            System.out.println(rules);
            model.put("rules", rules);
        } else if (isNumeric(search)) {
            SnortRuleConfig rule = repo.findById(Integer.valueOf(search)).orElse(new SnortRuleConfig());
            System.out.println("from data base +" + rule.getSid() + "...");
            System.out.println(rule);
            model.put("rules", rule);
        } else if (isValidIPAddress(search)) {
            List<SnortRuleConfig> rules = repo.findBysrcip(search);
            System.out.println("ipaddr");
            model.put("rules", rules);
        }
	/*	model mod = repo.findById(var).orElse(new model());
		System.out.println("from data base +"+mod.getRid()+"...");
		if(mod.getRid()=="null") {
			System.out.println("Null");
			mod.setRid("Sorry No Rule");}*/
        return "view.jsp";
    }
    @PostMapping("/validate")
    public String ruleValidate(){

        return "view.jsp";
    }

}