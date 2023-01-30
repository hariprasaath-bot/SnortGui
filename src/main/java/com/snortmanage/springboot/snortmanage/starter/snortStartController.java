package com.snortmanage.springboot.snortmanage.starter;

import com.snortmanage.springboot.snortmanage.usermanager.UserController;
import com.snortmanage.springboot.snortmanage.usermanager.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Controller
public class snortStartController {

    @GetMapping(value="/snortstart")
    public  String snortStartPage(ModelMap model,HttpServletRequest request){
    	String uname=(String)request.getSession().getAttribute("viewer");
    	if(uname!=null) {
        	model.put("regname", "Welcome" + " " + uname + " " + "!");	
        }
    	return "snortstart.jsp";
    }
    @PostMapping(value={"/start","/snortstar"})
    public String snortStarter(snortStartModel obj, ModelMap model, HttpServletRequest request) throws IOException, InterruptedException {
        UserModel logobj = (UserModel) request.getSession().getAttribute("logobj");
        String alert = "";
        obj.setLogobj(logobj);
        alert = obj.snortStarter();
        model.put("AlertMessage",alert);
        return "snortstart.jsp";
    }
}
