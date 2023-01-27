package com.snortmanage.springboot.snortmanage.fetcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.snortmanage.springboot.snortmanage.config.SnortRuleConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class fetchController {

    private String ruleFilePath = "/etc/snort/rules/local.rules";
    @Autowired
    SnortRuleRepo repo;

    @GetMapping("/rulefetch")
    public String fetch(ModelMap model,HttpSession session,HttpServletRequest request) {
        System.out.println("accepted from fetch");
        String uname=(String)request.getSession().getAttribute("viewer");
        model.put("regname", "Welcome"+" "+uname+" "+"!");
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
            System.out.println(rules.get(0));
            System.out.println(rules.get(1).getSid());
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
        String scriptdata = "onerror='tableCreate()'";
        model.put("functioncall",scriptdata);
        return "view.jsp";
    }

    @RequestMapping("/saveToFile")
    public ModelAndView saveToFile() {
        List<SnortRuleConfig> data = new ArrayList<SnortRuleConfig>();
        repo.findAll().forEach(rule -> data.add(rule));
        try {
            File myObj = new File(ruleFilePath);
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
            FileWriter obj = new FileWriter(ruleFilePath);
            BufferedWriter rulefile = new BufferedWriter(obj);
            for (int i = 0; i < data.size(); i++) {
                String str = data.get(i).ruleGenerator();
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
    @PostMapping("/url")
	public ModelAndView saveToDatabase(@RequestBody Map<String,String> data)
	{
		System.out.println("accepted from .............");
		System.out.println("rid from script"+data);
		System.out.println(data.get("rid"));
		System.out.println(data.get("protocol"));
		System.out.println(data.get("sip"));
		SnortRuleConfig newRecord = new SnortRuleConfig();
		newRecord.setSid(Integer.parseInt(data.get("rid")));
		newRecord.setProtocol(data.get("protocol"));
		newRecord.setSrcip(data.get("sip"));
		newRecord.setSrc_port(data.get("sport"));
		newRecord.setDst_ip(data.get("dip"));
		newRecord.setDst_port(data.get("dport"));
		newRecord.setMessage(data.get("msg"));
		newRecord.setNum_pkts(data.get("npkts"));
		System.out.println(newRecord);
		repo.deleteById(Integer.parseInt(data.get("rid")));
		repo.save(newRecord);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("view.jsp");
		
		return mv;
		
	}
    
    @PostMapping("/url2")
	public ModelAndView deleteFromDatabase(@RequestBody Map<String,String> data)
	{
		System.out.println("accepted from url2.............");
		System.out.println("rid from script"+data);
		System.out.println(data.get("rid"));
		
		repo.deleteById(Integer.parseInt(data.get("rid")));
		
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