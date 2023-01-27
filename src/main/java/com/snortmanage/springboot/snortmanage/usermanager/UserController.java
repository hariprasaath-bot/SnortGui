package com.snortmanage.springboot.snortmanage.usermanager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private UserModel uobj;
	
	@Autowired
	private UserRegRepo repo;
	
	@GetMapping(value = {"register",""})
    public String register() {
        return "register.jsp";
	}
	@GetMapping(value = {"login",""})
    public String login() {
        return "login.jsp";
	}
	@GetMapping(value = {"home",""})
    public String home() {
        return "home.jsp";
	}
    @PostMapping(value = "userregpost")
    public String userreg(UserModel uobj,HttpSession session,ModelMap model) {
    	repo.save(uobj);
        String str=uobj.getUsername();
        model.put("userregrepoAckn", "User registered successfully");
    	session.setAttribute("viewer", str);
    	if(str!=null) {
        	model.put("regname", "Welcome" + " " + str + " " + "!");	
        }
    	return "login.jsp";
    }
    @PostMapping(value = "userloginpost")
    public String userlogin(@RequestParam Map<String, String> requestParams,ModelMap model){
    	UserModel obj=repo.findByusername(requestParams.get("username"));
    	String pass = requestParams.get("password");
    	if(obj.getPassword().equals(pass)){
    		return "home.jsp";
    	}
    	else {
    		model.put("wrongpassword","Password doesnt match,enter correct password");
    		return "login.jsp";
    	}
    }
}
