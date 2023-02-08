package com.snortmanage.springboot.snortmanage.starter;

import com.snortmanage.springboot.snortmanage.alerts.alertRepo;
import com.snortmanage.springboot.snortmanage.config.rule;
import com.snortmanage.springboot.snortmanage.usermanager.user;
import com.snortmanage.springboot.snortmanage.alerts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

@Controller
public class snortStartController {

	@Autowired
	private alertRepo repos;
	
	@CrossOrigin
    @GetMapping("/getinterface")
	@ResponseBody
    public  List<String> snortStartPage(HttpServletRequest request) throws SocketException {
    	System.out.println("called");
        user logobj = (user) request.getSession().getAttribute("logobj");
    	System.out.println("ayya:"+logobj);

        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        List<String> list1 = new ArrayList<>();
        for (NetworkInterface netint : Collections.list(nets)) {
        	
            list1.add(displayInterfaceInformation(netint));}
        /*model.put("networkinterface",list);
        model.put("rows",list.size());
    	String uname=(String)request.getSession().getAttribute("viewer");
    	if(uname!=null) {
        	model.put("regname", "Welcome" + " " + uname + " " + "!");}	
        */
        
    	return list1;
    }
	@CrossOrigin
    @PostMapping("/startSnort")
    @ResponseBody
    public String snortStarter(@RequestBody snortStartModel obj, ModelMap model, HttpServletRequest request) throws IOException, InterruptedException {
               
		user logobj = (user) request.getSession().getAttribute("logobj");
        String alert = "";
        System.out.println(logobj);
        obj.setLogobj(logobj);
        obj.setConfFilePath(logobj.getConfFilePath());
        obj.setLogFilePath(logobj.getLogFilePath());
        obj.setRepos(repos);

        if(obj.getInface().contains("[")){
            obj.setInface(obj.getInface().replace("[",""));

        }else if(obj.getInface().contains("]")){
            obj.setInface(obj.getInface().replace("]",""));
        }
        System.out.println(obj);
        alert = obj.snortStarter();
        model.put("AlertMessage",alert);
        return alert;
    }
    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @CrossOrigin
    @PostMapping("/searchAlert")
    public ResponseEntity<List<alertModel>> searchAlert(@RequestBody() String var, ModelMap model)
    {
        System.out.println("here: "+(var));
        List<alertModel> data;
    	if(var.equals("TCP") | var.equals("UDP") | var.equals("ICMP")) {
    		 data = repos.findByProtocol(var);
    		//System.out.println(data);
    		model.put("data", data);
            System.out.println("from func: "+data);
            return new ResponseEntity<>(data, HttpStatus.OK);
    	}
    	else if (var.matches("[a-zA-Z]+")) {
    		data = repos.findByMsg(var);
    		//System.out.println(data);
    		model.put("data", data);
            return new ResponseEntity<>(data, HttpStatus.OK);
    	}
    	else if(var.length()>=6 & isNumeric(var))
    	{
    		data = repos.findByRid(var);
    		model.put("data", data);
            return new ResponseEntity<>(data, HttpStatus.OK);
    	}
    	else if(isNumeric(var))
    	{
    		data = repos.findByPriority(var);
    		model.put("data", data);
            return new ResponseEntity<>(data, HttpStatus.OK);
    	}


    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        String s = new String();
        s = netint.getName();
        return s;
    }

    }
