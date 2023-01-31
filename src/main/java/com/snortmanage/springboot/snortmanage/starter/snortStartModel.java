package com.snortmanage.springboot.snortmanage.starter;

import com.snortmanage.springboot.snortmanage.usermanager.UserController;
import com.snortmanage.springboot.snortmanage.usermanager.UserModel;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.snortmanage.springboot.snortmanage.alerts.alertModel;
import com.snortmanage.springboot.snortmanage.alerts.alertRepo;

import java.io.*;
import java.lang.*;

//snort compile code: snort -A console -i wlp3s0 -q -c /etc/snort/test_snort.conf
//snort -A full -l /home/hariprasaath/versions/miniproject_bash -i wlp3s0  -c /etc/snort/test_snort.conf
public class snortStartModel extends UserController {
	
	
	
    private String snortMode;
    private String compArg;

    private String logMode;

    private UserModel logobj;

    private String inface;

    private String logComp;

    public String getSnortMode() {
        return snortMode;
    }

    public void setSnortMode(String snortMode) {
        this.snortMode = snortMode;
    }

    public String getLogMode() {
        return logMode;
    }

    public void setLogMode(String logMode) {
        this.logMode = logMode;
    }

    public String getInface() {
        return inface;
    }
    public void setLogobj(UserModel logobj) {
        this.logobj = logobj;
    }

    @Override
    public String toString() {
        return "snortStartModel{" +
                "snortMode='" + snortMode + '\'' +
                ", compArg='" + compArg + '\'' +
                ", logMode='" + logMode + '\'' +
                ", logFilePath='" + logFilePath + '\'' +
                ", inface='" + inface + '\'' +
                ", confFilePath='" + confFilePath + '\'' +
                ", logComp='" + logComp + '\'' +
                '}';
    }

    public void setInface(String inface) {
        if (!Objects.equals(inface, "")) {
            this.inface = inface;
        } else {
            this.inface = "wlp3s0";
        }
    }
    
	
    //ProcessBuilder ps = new ProcessBuilder("snort", "-A", "console", "-i", "wlp3s0", "-q", "-c", "/etc/snort/test_snort.conf");
    public String snortStarter() throws IOException, InterruptedException {
        String alert = "";

        if (Objects.equals(snortMode, "logger")) {
            logComp = "full";

        } else if (Objects.equals(snortMode, "sniffer")) {
            logComp = "";
            snifferHandle();
        } else if (Objects.equals(snortMode, "IDS")) {
            logComp = "console";

        }

        if (Objects.equals(logMode, "alerttoconsole")) {
            alert = idsHandle(false);
            return alert;
        } else if (Objects.equals(logMode, "alerttoweb")) {
            System.out.println("IDS MODE WITH ALERT TO WEB");
            alert = idsHandle(true);
            return alert;
        }
        return alert;

    }
//hell0
    
    
	private alertRepo repos;
    	
	
    //private alertModel obj;
    
    public String idsHandle(boolean webwrite) throws IOException, InterruptedException {
    	
    	
    	
        System.out.println("Staring SNORT IN IDS MODE");        
        ProcessBuilder ps = new ProcessBuilder("snort", "-A", logComp, "-i", inface, "-q", "-c", logobj.getConfFilePath());
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        int count =1;
        while ((line = in.readLine()) != null) {

            if (webwrite) {
                //pr.waitFor();
                System.out.println("ok!");
                System.out.println(line);
                String[] list1 = line.split(" ");        
                
                alertModel obj = new alertModel();
                obj.setAlertid(count);
                obj.setTime(list1[0]);
                obj.setRid(list1[3].split(":")[1]);
                obj.setMsg(list1[4]);
                obj.setPriority(""+list1[7].charAt(0));
                obj.setProtocol(list1[8].replace("{","" ).replace("}",""));              
                obj.setSrcip(list1[9].split(":")[0]);
                obj.setSrc_port(list1[9].split(":")[1]);
                obj.setFlow(list1[10]);
                obj.setDst_ip(list1[11].split(":")[0]);
                obj.setDst_port(list1[11].split(":")[1]);
                System.out.println("Object is "+obj);
                
                this.repos.save(obj);
                count++;
                
              
               
            } else {
                System.out.println(line);
            }
        }
        //pr.waitFor();
        System.out.println("ok!");
        in.close();
        return null;
    }

    public void snifferHandle() throws IOException, InterruptedException {

        ProcessBuilder ps = new ProcessBuilder("snort", "-A", logComp, "-i", inface);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        //pr.waitFor();
        System.out.println("ok!");

        in.close();
    }

	public void setRepos(alertRepo repos) {
		// TODO Auto-generated method stub
		this.repos = repos;
		
	}
}
