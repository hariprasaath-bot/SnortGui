package com.snortmanage.springboot.snortmanage.admin;

import com.snortmanage.springboot.snortmanage.usermanager.UserModel;
import com.snortmanage.springboot.snortmanage.usermanager.UserRegRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    @PostMapping(value = "adminlogin")
    public String userlogin(@RequestParam Map<String, String> requestParams, ModelMap model, HttpSession session) {
        String admin = requestParams.get("adminname");
        String pass = requestParams.get("adminpassword");
        AdminModel objAdminModel = new AdminModel();
        if (admin.equals(objAdminModel.getUsername()) && admin.equals(objAdminModel.getUsername()) ) {
            return "adminpage.jsp";
        } else {
            model.put("wrongpassword", "Admin Password doesn't match,enter correct password");
            return "login.jsp";
        }
    }

    @PostMapping("/fetchuser")
    public String userfetch(@RequestParam("search term") String search, ModelMap model) {
        System.out.println("USER TABLE: Your query is  " + search);

        if (search.contains("@.com")) {                  //Search for protocol
            List<UserModel> users= repo.findByemail(search);
            if (users.isEmpty()) {
                System.out.println(search + " NO match found");
                model.put("noRule","NO match found");
            } else {
                int noOfUsers = users.size();
                model.put("rows", noOfUsers);
                model.put("users", users);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
            }
        } else if (search.toLowerCase().contains("linux") || search.toLowerCase().contains("windows") ) {                             //Search for rule sid
            List<UserModel> users= repo.findByoperatingSystem(search);
            if (users.isEmpty()) {
                System.out.println(search + " NO match found");
                model.put("noRule","NO match found");
            } else {
                int noOfUsers = users.size();
                model.put("rows", noOfUsers);
                model.put("users", users);
                String scriptdata = "onerror='tableCreate()'";
                model.put("functioncall", scriptdata);
            }
        }
        return "adminpage.jsp";
    }
    @PostMapping("/viewall")
    public String userdata(ModelMap model) {

        List<UserModel> data = new ArrayList<UserModel>();
        repo.findAll().forEach(user -> data.add(user));
        if (data.isEmpty()) {
            model.put("noRule","NO match found");
        }else {
            int noOfUsers = data.size();
            model.put("rows", noOfUsers);
            model.put("users", data);
            String scriptdata = "onerror='tableCreate()'";
            model.put("functioncall", scriptdata);
        }
        return "adminpage.jsp";
    }
    @PostMapping("/edited")
    public ModelAndView saveToDatabase(@RequestBody Map<String, String> user) {

        //Deleting old record
        String id = user.get("email");
        repo.deleteById(id);

        System.out.print(user);
        //Creating a new record for each save
        UserModel newRecord = new UserModel();
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