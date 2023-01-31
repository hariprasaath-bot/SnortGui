package com.snortmanage.springboot.snortmanage.starter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Controller
public class snortStartController {
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
    public String snortStarter(snortStartModel obj, ModelMap model) throws IOException, InterruptedException {
        String alert = "";
        System.out.println(obj);
        alert = obj.snortStarter();
        model.put("AlertMessage",alert);
        return "snortstart.jsp";
    }

    static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        String s = new String();
        s = netint.getName();
        return s;
    }

    }
