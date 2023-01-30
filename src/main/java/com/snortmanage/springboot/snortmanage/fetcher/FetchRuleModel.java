package com.snortmanage.springboot.snortmanage.fetcher;

import com.snortmanage.springboot.snortmanage.usermanager.UserController;
import com.snortmanage.springboot.snortmanage.usermanager.UserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FetchRuleModel extends UserController {

    private UserModel logobj;

    public String ruleValidation() throws IOException, InterruptedException {


        ProcessBuilder ps = new ProcessBuilder("snort", "-T", "-c", logobj.getConfFilePath());
        Process pr = ps.start();
        BufferedReader in;
        in = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
        String line;
        while ((line = in.readLine()) != null) {
               System.out.println(line);
               if (line.contains("Error")){
                   return line;
               }
        }
        pr.waitFor();
        in.close();
        return "Config file verified";
    }

    public  String ruleFileDelete(){
        try {
            Files.deleteIfExists(Paths.get(logobj.getRuleFilePath()));
        }catch(IOException e){
            return "Deletion failed"+e;
        }
        return  "File deleted successfully";
    }

    public void setLogobj(UserModel logobj) {
        this.logobj = logobj;
    }
}
