package com.snortmanage.springboot.snortmanage.config;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;
import com.snortmanage.springboot.snortmanage.fetcher.SnortRuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class snortRuleController {

    private int sid = 100000;
    private SnortRuleConfig robj;
    @Autowired
    private SnortRuleRepo repo;
    @GetMapping(value={"home",""})
    public  String snortForm(ModelMap model){
        return "home.jsp";
    }

    @GetMapping(value={"rulegen"})
    public String snortGen(){
        return "rulegen.jsp";
    }
    @PostMapping(value={"rulepost","rulegen"})
    public String snortGenHandle(SnortRuleConfig obj,ModelMap model) throws IOException {
        robj = obj;
        robj.setSid(sid+=1);
        String drule = robj.ruleGenerator();
        repo.save(robj);
        model.put("generatedrule","The generated rule is: "+drule);
        return "rulegen.jsp";
    }
    @PostMapping(value={"rulefile"})
    public String snortGenfile(ModelMap model) throws IOException {
        robj.addRuleFile();
        model.put("acknmessag","the local.rules rule file generated");
        return "rulegen.jsp";
    }

}
