package com.snortmanage.springboot.snortmanage.starter;

import com.snortmanage.springboot.snortmanage.alerts.alertRepo;
import com.snortmanage.springboot.snortmanage.usermanager.UserController;
import com.snortmanage.springboot.snortmanage.usermanager.UserModel;
import com.snortmanage.springboot.snortmanage.alerts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
public class snortStartController {

	@Autowired
	private alertRepo repos;

    @GetMapping(value="/snortstart")
    public  String snortStartPage(ModelMap model,HttpServletRequest request) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        List<String> list = new ArrayList<>();
        for (NetworkInterface netint : Collections.list(nets))
            list.add(displayInterfaceInformation(netint));
        model.put("networkinterface",list);
        model.put("rows",list.size());
    	String uname=(String)request.getSession().getAttribute("viewer");
    	if(uname!=null) {
        	model.put("regname", "Welcome" + " " + uname + " " + "!");	
        }
    	return "snortstart.jsp";
    }
    @PostMapping(value={"/start","/snortstar"})
    public String snortStarter(snortStartModel obj, ModelMap model, HttpServletRequest request) throws IOException, InterruptedException {
        UserModel logobj = (UserModel) request.getSession().getAttribute("logobj");

        @PostMapping(value={"/start","/snortstar"})
    public String snortStarter(snortStartModel obj, ModelMap model) throws IOException, InterruptedException {
        String alert = "";
        obj.setLogobj(logobj);
        obj.setRepos(repos);
        System.out.println(obj);
        alert = obj.snortStarter();
        model.put("AlertMessage",alert);
        return "snortstart.jsp";
    }
    public static boolean isNumeric(String strNum) {
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @PostMapping("/searchAlert")
    public String searchAlert(@RequestParam("var1") String var, ModelMap model)
    {
    	if(var.equals("TCP") | var.equals("UDP") | var.equals("ICMP")) {
    		List<alertModel> data = repos.findByProtocol(var);
    		//System.out.println(data);
    		model.put("data", data);
    	}
    	else if (var.matches("[a-zA-Z]+")) {
    		List<alertModel> data = repos.findByMsg(var);
    		//System.out.println(data);
    		model.put("data", data);
    	}
    	else if(var.length()>=6 & isNumeric(var))
    	{
    		List<alertModel> data = repos.findByRid(var);
    		model.put("data", data);
    	}
    	else if(isNumeric(var))
    	{
    		List<alertModel> data = repos.findByPriority(var);
    		model.put("data", data);
    	}


    	return "snortstart.jsp";
    }

    static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        String s = new String();
        s = netint.getName();
        return s;
    }

    }
