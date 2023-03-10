package com.snortmanage.springboot.snortmanage.fetcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.snortmanage.springboot.snortmanage.config.rule;

import com.snortmanage.springboot.snortmanage.usermanager.user;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class fetchController{

    private final String ruleFilePath = "/etc/snort/rules/local.rules";
    @Autowired
    SnortRuleRepo repo;

    @GetMapping("/rulefetch")
    public String fetch(ModelMap model,HttpSession session,HttpServletRequest request) {
        System.out.println("accepted from fetch");
        String uname=(String)request.getSession().getAttribute("viewer");
        if(uname!=null) {
        	model.put("regname", "Welcome" + " " + uname + " " + "!");
        }
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

    @CrossOrigin
    @PostMapping("/fetchrule")
    public ResponseEntity<List<rule>> fetchdis(@RequestBody String search, ModelMap model,HttpServletRequest request) {
        System.out.println("Your query is  " + search);
        user logobj = (user) request.getSession().getAttribute("logobj");
        System.out.println(logobj);
        //ModelAndView mv = new ModelAndView();
        List<rule> rules;

        if (search.matches("[a-zA-Z]+")) {                  //Search for protocol
            rules = repo.findByprotocol(search);
            System.out.println("here: "+ rules);
            if (rules.isEmpty()) {
                System.out.println(search + " NO match found");
                return new ResponseEntity<>(rules, HttpStatus.BAD_REQUEST);
            } else {
                int noOfRules = rules.size();
                model.put("rows", noOfRules);
                model.put("rules", rules);
                System.out.println(rules);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
                return new ResponseEntity<>(rules, HttpStatus.OK);
            }
        } else if (isNumeric(search)) {                             //Search for rule sid
            rules = (List<rule>) repo.findById(Integer.valueOf(search)).orElse(new rule());
            if (rules.equals(null)) {
                System.out.println(search + " NO match found");
                return new ResponseEntity<>(rules, HttpStatus.BAD_REQUEST);
            }else {
                int noOfRules = 1;              //Always one -- unique sid
                model.put("rows", noOfRules);
                model.put("rules", rules);
                System.out.println(rules);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
                return new ResponseEntity<>(rules, HttpStatus.OK);
            }
        } else if (isValidIPAddress(search)) {                      //search for IP ADDRESS
            rules = repo.findBysrcip(search);
            if (rules.isEmpty()) {
                System.out.println(search + " NO match found");
                return new ResponseEntity<>(rules, HttpStatus.BAD_REQUEST);
            } else {
                int noOfRules = rules.size();
                model.put("rows", noOfRules);
                model.put("rules", rules);
                System.out.println(rules);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
                return new ResponseEntity<>(rules, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/saveToFile")
    public ModelAndView saveToFile(HttpServletRequest request) {
        user logobj = (user) request.getSession().getAttribute("logobj");

        List<rule> data = new ArrayList<rule>();
        repo.findAll().forEach(rule -> data.add(rule));
        try {
            File myObj = new File(logobj.getRuleFilePath());
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
            FileWriter obj = new FileWriter(logobj.getRuleFilePath());
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
    public ModelAndView saveToDatabase(@RequestBody Map<String, String> data) {

        System.out.println("rid from script" + data);
        System.out.println(data.get("rid"));
        System.out.println(data.get("protocol"));
        System.out.println(data.get("sip"));

        //Creating a new record for each save
        rule newRecord = new rule();
        newRecord.setSid(Integer.parseInt(data.get("rid")));
        newRecord.setProtocol(data.get("protocol"));
        newRecord.setSrcip(data.get("sip"));
        newRecord.setSrc_port(data.get("sport"));
        newRecord.setDst_ip(data.get("dip"));
        newRecord.setDst_port(data.get("dport"));
        newRecord.setMessage(data.get("msg"));
        newRecord.setNum_pkts(data.get("npkts"));
        System.out.println(newRecord);
        repo.save(newRecord);

        //Deleting
        repo.deleteById(Integer.parseInt(data.get("id")));

        ModelAndView mv = new ModelAndView();
        mv.setViewName("view.jsp");

        return mv;

    }

    @PostMapping("/url2")
    public ModelAndView deleteFromDatabase(@RequestBody Map<String, String> data) {


        System.out.println("rid from script" + data);
        System.out.println(data.get("rid"));

        repo.deleteById(Integer.parseInt(data.get("rid")));

        ModelAndView mv = new ModelAndView();
        mv.setViewName("view.jsp");

        return mv;

    }

    @PostMapping("/validate")
    public String ruleValidate(ModelMap model, HttpServletRequest request) throws IOException, InterruptedException {
        user logobj = (user) request.getSession().getAttribute("logobj");
        FetchRuleModel obj = new FetchRuleModel();
        obj.setLogobj(logobj);
        String validator = obj.ruleValidation();
        model.put("acknowledge", validator);
        return "view.jsp";
    }

    @PostMapping("/deletefile")
    public String ruleFileDelete(ModelMap model, HttpServletRequest request) throws IOException, InterruptedException {
        user logobj = (user) request.getSession().getAttribute("logobj");
        FetchRuleModel obj = new FetchRuleModel();
        obj.setLogobj(logobj);
        String validator = obj.ruleFileDelete();
        model.put("acknowledge", validator);
        return "view.jsp";
    }

}