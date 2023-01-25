package com.snortmanage.springboot.snortmanage.fetcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
            int s = rules.size();
            model.put("rows",s);
            model.put("rules", rules);
        } else if (isNumeric(search)) {
            SnortRuleConfig rule = repo.findById(Integer.valueOf(search)).orElse(new SnortRuleConfig());
            System.out.println("from data base +" + rule.getSid() + "...");
            System.out.println(rule);
            int s = 1;
            model.put("rows",s);
            model.put("rules", rule);
        } else if (isValidIPAddress(search)) {
            List<SnortRuleConfig> rules = repo.findBysrcip(search);
            System.out.println("ipaddr");
            int s = rules.size();
            model.put("rows",s);
            model.put("rules", rules);
        }
	/*	model mod = repo.findById(var).orElse(new model());
		System.out.println("from data base +"+mod.getRid()+"...");
		if(mod.getRid()=="null") {
			System.out.println("Null");
			mod.setRid("Sorry No Rule");}*/
        return "table.jsp";
    }
    
    @RequestMapping("/saveToFile")
	public ModelAndView saveToFile()
	{
		List<SnortRuleConfig> data = new ArrayList<SnortRuleConfig>();
	     repo.findAll().forEach(rule -> data.add(rule));
	     try {
	         File myObj = new File("rule.txt");
	         if (myObj.createNewFile()) {
	           System.out.println("File created: " + myObj.getName());
	         } else {
	           System.out.println("File already exists.");
	           
	         }
	       } catch (IOException e) {
	         System.out.println("An error occurred.");
	         e.printStackTrace();
	       }
	     try {
			FileWriter obj = new FileWriter("rule.txt");
			BufferedWriter rulefile = new BufferedWriter(obj);
			for(int i =0; i<data.size(); i++)
		     {
		    	 String str =data.get(i).ruleGenerator();
		    	 rulefile.write(str);
		    	 rulefile.newLine();
		    	 System.out.println(data.get(i).toString());
		    	 //System.out.println(getClass(data.get(i)));
		     }
			rulefile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view.jsp");
		return mv;
	}
    
    @PostMapping("/validate")
    public String ruleValidate(ModelMap model) throws IOException, InterruptedException {
        FetchRuleModel obj = new FetchRuleModel();
        String validator = obj.ruleValidation();
        model.put("acknowledge", validator);
        return "view.jsp";
    }

}