package com.snortmanage.springboot.snortmanage.admin;

import com.snortmanage.springboot.snortmanage.config.rule;
import com.snortmanage.springboot.snortmanage.fetcher.SnortRuleRepo;
import com.snortmanage.springboot.snortmanage.usermanager.user;
import com.snortmanage.springboot.snortmanage.usermanager.UserRegRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserRegRepo repo;

    @Autowired
    private SnortRuleRepo ruleRepo;

    @PostMapping(value = "adminlogin")
    public String userlogin(@RequestParam Map<String, String> requestParams, ModelMap model, HttpSession session) {
        String admin = requestParams.get("adminname");
        String pass = requestParams.get("adminpassword");
        AdminModel objAdminModel = new AdminModel();
        if (admin.equals(objAdminModel.getUsername()) && admin.equals(objAdminModel.getUsername())) {
            return "adminpage.jsp";
        } else {
            model.put("wrongpassword", "Admin Password doesn't match,enter correct password");
            return "login.jsp";
        }
    }

    @CrossOrigin
    @PostMapping("/fetchuser")
    public ResponseEntity<List<user>> userfetch(@RequestBody() String search, ModelMap model) {
        System.out.println("USER TABLE: Your query is  " + search);
        List<user> users;
        if (search.contains("@.com")) {                  //Search for protocol
             users = repo.findByemail(search);
            if (users.isEmpty()) {
                System.out.println(search + " NO match found");
                model.put("noRule", "NO match found");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                int noOfUsers = users.size();
                model.put("rows", noOfUsers);
                model.put("users", users);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
//                System.out.println((users));
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        } else if (search.toLowerCase().contains("linux") || search.toLowerCase().contains("windows")) {                             //Search for rule sid
            users = repo.findByoperatingSystem(search);
            if (users.isEmpty()) {
                System.out.println(search + " NO match found");
                model.put("noRule", "NO match found");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                int noOfUsers = users.size();
                model.put("rows", noOfUsers);
                model.put("users", users);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
//                System.out.println((users));
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @PostMapping("/viewall")
    public ResponseEntity<List<user>>  userdata(ModelMap model) {

        List<user> data = new ArrayList<user>();
        repo.findAll().forEach(user -> data.add(user));
        if (data.isEmpty()) {
            model.put("noRule", "NO match found");
        } else {
            int noOfUsers = data.size();
            model.put("rows", noOfUsers);
            model.put("users", data);
            String scriptdata = "onerror='tableCreate()'";
            model.put("functioncall", scriptdata);
        }
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PostMapping("/edited")
    public ModelAndView saveToDatabase(@RequestBody Map<String, String> user) {

        //Deleting old record
        String id = user.get("email");
        repo.deleteById(id);

        System.out.print(user);
        //Creating a new record for each save
        com.snortmanage.springboot.snortmanage.usermanager.user newRecord = new user();
        newRecord.setEmail((user.get("email")));
        newRecord.setOperatingSystem(user.get("operatingsystem"));
        newRecord.setPassword("root");
        newRecord.setUsername(user.get("username"));
        System.out.println(newRecord);

        repo.save(newRecord);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("adminpage.jsp");

        return mv;
    }

    @PostMapping("/deleteby")
    public ModelAndView deleteFromUserDatabase(@RequestBody Map<String, String> data) {

        System.out.println("email from script" + data);
        String id = data.get("email");
        repo.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("adminpage.jsp");

        return mv;
    }

}