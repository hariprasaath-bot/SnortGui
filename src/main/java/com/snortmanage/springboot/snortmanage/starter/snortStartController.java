package com.snortmanage.springboot.snortmanage.starter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class snortStartController {
    @GetMapping(value="/snortstart")
    public  String snortStartPage(){
        return "snortstart.jsp";
    }
    @PostMapping(value={"/start","/snortstar"})
    public String snortStarter(snortStartModel obj, ModelMap model) throws IOException, InterruptedException {
        String alert = "";

        alert = obj.snortStarter();
        model.put("AlertMessage",alert);
        return "snortstart.jsp";
    }
}
