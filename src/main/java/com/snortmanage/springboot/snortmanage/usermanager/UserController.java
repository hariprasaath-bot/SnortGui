package com.snortmanage.springboot.snortmanage.usermanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {

    public UserModel logobj;
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

    @GetMapping(value = {"home", ""})
    public String home(ModelMap model, HttpServletRequest request) {
        String uname = (String) request.getSession().getAttribute("viewer");
        if (uname != null) {
            model.put("regname", "Welcome" + " " + uname + " " + "!");
        }
        return "home.jsp";
    }

    @PostMapping(value = "userregpost")
    public String userreg(UserModel regobj, HttpSession session, ModelMap model) {
        regobj.setOpertatingSystem();
        System.out.println("Finding your Operating System " + regobj.getOperatingSystem());
        repo.save(regobj);
        String str = regobj.getUsername();
        model.put("userregrepoAckn", "User registered successfully");
        session.setAttribute("viewer", str);
        if (str != null) {
            model.put("regname", "Welcome" + " " + str + " " + "!");
        }
        return "login.jsp";
    }

    @PostMapping(value = "userloginpost")
    public String userlogin(@RequestParam Map<String, String> requestParams, ModelMap model, HttpSession session) {
        logobj = repo.findByusername(requestParams.get("username"));
        String pass = requestParams.get("password");
        if (BCrypt.checkpw(pass, logobj.getPassword())) {
            logobj.pathSetter();
            session.setAttribute("logobj", logobj);
            return "home.jsp";
        } else {
            model.put("wrongpassword", "Password doesn't match,enter correct password");
            return "login.jsp";
        }
    }

}
