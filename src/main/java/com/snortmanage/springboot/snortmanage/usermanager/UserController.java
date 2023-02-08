package com.snortmanage.springboot.snortmanage.usermanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {

    public user logobj;
    @Autowired
    private UserRegRepo repo;

    @GetMapping(value = {"register", ""})
    public String register() {
        return "register.jsp";
    }

    @GetMapping(value = {"login", ""})
    public String login() {
        return "login.jsp";
    }
    @GetMapping(value = {"logout", ""})
    public String logout(HttpSession session) {
    	session.setAttribute("viewer", null);
        return "login.jsp";
    }
    @GetMapping(value = {"changepass", ""})
    public String changepass(ModelMap model,HttpServletRequest request) {
    	String uname = (String) request.getSession().getAttribute("viewer");
    	model.put("userinfo","Hello"+" "+uname+". "+"enter the following to change password.");
        return "password.jsp";
    }
    @PostMapping(value = {"newpass",""})
    public String newpass(@RequestParam Map<String, String> requestParams, ModelMap model, HttpSession session,HttpServletRequest request) {
    	String uname = (String) request.getSession().getAttribute("viewer");
    	logobj = repo.findByusername(uname);
        String currentpass = requestParams.get("currentpass");
        String newpass = requestParams.get("newpass");
        if (BCrypt.checkpw(currentpass, logobj.getPassword())) {
            logobj.pathSetter();
            logobj.setPassword(newpass);
            repo.save(logobj);
            model.put("newpassack","Your password has been changed.");
        	return "password.jsp";
        } else {
            model.put("wrongpass", "Password doesn't match,enter correct password");
            return "password.jsp";
        }
    	
    }
    

    @GetMapping(value = {"home", ""})
    public String home(ModelMap model, HttpServletRequest request) {
        String uname = (String) request.getSession().getAttribute("viewer");
        if (uname != null) {
            model.put("regname", "Welcome" + " " + uname + " " + "!");
            return "home.jsp";
        }
        else{
        	return "login.jsp";
        }
    }

    @PostMapping(value = "userregpost")

    public ResponseEntity<String> userreg(@RequestBody user regobj, HttpSession session, ModelMap model) {
        regobj.setOpertatingSystem();
        System.out.println("Finding your Operating System " + regobj.getOperatingSystem());
        repo.save(regobj);
        model.put("userregrepoAckn", "User registered successfully");
        String result = "success";
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "userloginpost")
    public ResponseEntity<String> userlogin(@RequestBody Map<String, String> requestParams, ModelMap model, HttpSession session) {
        System.out.println(requestParams);
        logobj = repo.findByusername(requestParams.get("username"));
        String pass = requestParams.get("password");
        if (BCrypt.checkpw(pass, logobj.getPassword())) {
            logobj.pathSetter();
            String str = logobj.getUsername();
            System.out.println(logobj);
            session.setAttribute("viewer", str);
            session.setAttribute("logobj", logobj);
            model.put("regname", "welcome"+" "+logobj.getUsername()+" "+"!");
            String result = "success";
            return new ResponseEntity<>(result,HttpStatus.OK);
        } else {
            model.put("wrongpassword", "Password doesn't match,enter correct password");
            String result = "fail";
            return new ResponseEntity<>(result,HttpStatus.FORBIDDEN);
        }
    }

}
